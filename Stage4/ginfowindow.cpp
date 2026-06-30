#include "ginfowindow.h"
#include <QVBoxLayout>
#include <QLabel>
#include <QPixmap>
#include <QBrush>
#include <QPen>
#include <QPainter>
#include <QResizeEvent>

GInfoWindow::GInfoWindow(const QString &owner, Territory *territory, ETNube *nube, QWidget *parent)
    : QDialog(parent), dueno(owner), territory(territory), nube(nube)
{
    setupUI();
    loadGraphics();

    // Ajustar tamaño de la ventana al mapa
    if (scene && !scene->sceneRect().isEmpty()) {
        QRectF rect = scene->sceneRect();
        int width = rect.width() + 40;
        int height = rect.height() + 80;
        // Limitar tamaño máximo para no ocupar toda la pantalla
        resize(std::min(width, 1200), std::min(height, 900));

        // Si la imagen es pequeña, hacer zoom para que se vea bien
        if (rect.width() < 500 || rect.height() < 400) {
            qreal scaleX = 500.0 / rect.width();
            qreal scaleY = 400.0 / rect.height();
            qreal scale = std::min(scaleX, scaleY);
            graphicsView->setTransform(QTransform::fromScale(scale, scale));
            graphicsView->centerOn(rect.center());
        } else {
            // Ajustar al tamaño de la ventana
            QTimer::singleShot(100, this, &GInfoWindow::fitToView);
        }
    }

    updateTimer = new QTimer(this);
    connect(updateTimer, &QTimer::timeout, this, &GInfoWindow::updateInfo);
    updateTimer->start(1000);

    setAttribute(Qt::WA_DeleteOnClose);
}

GInfoWindow::~GInfoWindow()
{
    if (updateTimer) {
        updateTimer->stop();
    }
}

void GInfoWindow::fitToView()
{
    if (scene && !scene->sceneRect().isEmpty()) {
        graphicsView->fitInView(scene->sceneRect(), Qt::KeepAspectRatio);
        isFitted = true;
    }
}

void GInfoWindow::setupUI()
{
    setWindowTitle("GFindMy - " + dueno);
    setMinimumSize(500, 400);

    QVBoxLayout *layout = new QVBoxLayout(this);
    layout->setContentsMargins(10, 10, 10, 10);

    QLabel *title = new QLabel("Dispositivos de " + dueno + " (vista gráfica)", this);
    title->setAlignment(Qt::AlignCenter);
    title->setStyleSheet("font-size: 14px; font-weight: bold; padding: 5px; background-color: #f0f0f0; border-radius: 5px;");
    layout->addWidget(title);

    // Instrucciones
    QLabel *info = new QLabel("Usa la rueda del mouse para hacer zoom | Arrastra para mover el mapa", this);
    info->setAlignment(Qt::AlignCenter);
    info->setStyleSheet("font-size: 10px; color: #666; padding: 2px;");
    layout->addWidget(info);

    graphicsView = new QGraphicsView(this);
    graphicsView->setRenderHint(QPainter::Antialiasing);
    graphicsView->setDragMode(QGraphicsView::ScrollHandDrag);
    graphicsView->setStyleSheet("border: 2px solid #666; background-color: white;");
    graphicsView->setTransformationAnchor(QGraphicsView::AnchorUnderMouse);
    graphicsView->setResizeAnchor(QGraphicsView::AnchorUnderMouse);
    layout->addWidget(graphicsView);

    scene = new QGraphicsScene(this);
    graphicsView->setScene(scene);
}

void GInfoWindow::loadGraphics()
{
    // 1. Limpiar escena
    scene->clear();
    deviceItems.clear();
    labelItems.clear();
    isFitted = false;

    // 2. Agregar la imagen de fondo
    if (territory) {
        QString imagePath = QString::fromStdString(territory->getImagePath());
        backgroundImage.load(imagePath);

        if (backgroundImage.isNull()) {
            // Buscar en el directorio actual
            backgroundImage.load("Placeres.jpg");
        }

        if (!backgroundImage.isNull()) {
            QGraphicsPixmapItem *bgItem = scene->addPixmap(backgroundImage);
            scene->setSceneRect(backgroundImage.rect());

            // Ajustar la vista
            if (!isFitted) {
                graphicsView->setTransform(QTransform::fromScale(1, 1));
                fitToView();
            }
        } else {
            // Imagen por defecto si no se encuentra
            scene->setSceneRect(0, 0, 800, 600);
            scene->addRect(0, 0, 800, 600, QPen(Qt::black), QBrush(Qt::lightGray));
            scene->addText("No se encontró la imagen de fondo\nUsando mapa genérico")
                ->setPos(300, 280);
        }
    }

    // 3. Obtener los dispositivos del dueño desde la nube
    std::vector<ETNube::EquipoInfo> equipos = nube->getEquiposByOwner(dueno.toStdString());

    // 4. Dibujar cada dispositivo en el mapa
    for (const auto &info : equipos) {
        QString nombre = QString::fromStdString(info.equipo_name);

        // Círculo más grande y visible
        QGraphicsEllipseItem *item = new QGraphicsEllipseItem(-10, -10, 20, 20);

        QColor color;
        if (nombre == "Celular") {
            color = Qt::blue;
        } else if (nombre == "Tablet") {
            color = Qt::darkGreen;
        } else {
            color = Qt::red;
        }

        item->setBrush(QBrush(color));
        item->setPen(QPen(Qt::white, 2));
        item->setPos(info.x, info.y);
        item->setZValue(10);
        scene->addItem(item);

        // Etiqueta con el nombre (más grande)
        QGraphicsTextItem *label = new QGraphicsTextItem(nombre);
        label->setDefaultTextColor(Qt::black);
        label->setPos(info.x + 14, info.y - 10);
        label->setScale(0.9);
        label->setZValue(11);

        // Fondo blanco para el texto (más legible)
        QGraphicsRectItem *bgText = new QGraphicsRectItem(label->boundingRect());
        bgText->setBrush(QBrush(Qt::white));
        bgText->setPen(QPen(Qt::NoPen));
        bgText->setPos(info.x + 12, info.y - 12);
        bgText->setZValue(9);
        scene->addItem(bgText);

        scene->addItem(label);

        // Guardar para actualizar después
        deviceItems[nombre] = item;
        labelItems[nombre] = label;
    }
}

void GInfoWindow::updateInfo()
{
    // Actualizar posiciones de los dispositivos en el mapa
    std::vector<ETNube::EquipoInfo> equipos = nube->getEquiposByOwner(dueno.toStdString());

    for (const auto &info : equipos) {
        QString name = QString::fromStdString(info.equipo_name);
        if (deviceItems.contains(name)) {
            deviceItems[name]->setPos(info.x, info.y);
        }
        if (labelItems.contains(name)) {
            labelItems[name]->setPos(info.x + 14, info.y - 10);
        }
    }

    // Redibujar
    graphicsView->viewport()->update();
}

void GInfoWindow::wheelEvent(QWheelEvent *event)
{
    // Zoom con la rueda del mouse
    qreal factor = 1.1;
    if (event->angleDelta().y() < 0) {
        factor = 1.0 / factor;
    }
    graphicsView->scale(factor, factor);
    event->accept();
}

void GInfoWindow::resizeEvent(QResizeEvent *event)
{
    Q_UNUSED(event);
    // Cuando la ventana cambia de tamaño, reajustar si es necesario
    if (scene && !scene->sceneRect().isEmpty()) {
        // No ajustar automáticamente para permitir zoom manual
    }
}