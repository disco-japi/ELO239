#include "widget.h"
#include <QApplication>
#include <QFileDialog>
#include <QMessageBox>
#include <QString>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    QString fileName = QFileDialog::getOpenFileName(nullptr,
                                                    "Seleccionar archivo de configuración",
                                                    "",
                                                    "Text Files (*.txt);;All Files (*)");

    if (fileName.isEmpty()) {
        QMessageBox::warning(nullptr, "Error", "No se seleccionó ningún archivo. El programa se cerrará.");
        return 0;
    }

    Widget w(fileName);
    w.setWindowTitle("ELO239 - Stage 4: Simulador EloTelTags"); // Cambiar título
    w.show();

    return a.exec();
}