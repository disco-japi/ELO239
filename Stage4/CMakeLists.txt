cmake_minimum_required(VERSION 3.19)
project(Stage4 LANGUAGES CXX)  # Cambiar a Stage4

find_package(Qt6 6.5 REQUIRED COMPONENTS Core Widgets)

qt_standard_project_setup()

qt_add_executable(Stage4  # Cambiar a Stage4
    WIN32 MACOSX_BUNDLE
    main.cpp
    widget.cpp widget.h widget.ui
    equipo.h equipo.cpp
    cellular.h cellular.cpp
    territory.h territory.cpp
    cellularview.h cellularview.cpp
    territoryview.h territoryview.cpp
    elotelTag.h elotelTag.cpp
    elotelTagView.h elotelTagView.cpp
    tablet.h tablet.cpp
    tabletView.h tabletView.cpp
    etnube.h etnube.cpp
    radarCircle.h radarCircle.cpp
    view.h view.cpp
    infowindow.h infowindow.cpp
)

target_link_libraries(Stage4  # Cambiar a Stage4
    PRIVATE
        Qt::Core
        Qt::Widgets
)

install(TARGETS Stage4  # Cambiar a Stage4
    BUNDLE  DESTINATION .
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
)

qt_generate_deploy_app_script(
    TARGET Stage4  # Cambiar a Stage4
    OUTPUT_SCRIPT deploy_script
    NO_UNSUPPORTED_PLATFORM_ERROR
)
install(SCRIPT ${deploy_script})