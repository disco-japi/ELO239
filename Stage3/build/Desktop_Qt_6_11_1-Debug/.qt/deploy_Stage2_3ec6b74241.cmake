include("/home/discojapi/Proyectos/ELO239/Stage3/build/Desktop_Qt_6_11_1-Debug/.qt/QtDeploySupport.cmake")
include("${CMAKE_CURRENT_LIST_DIR}/Stage2-plugins.cmake" OPTIONAL)
set(__QT_DEPLOY_I18N_CATALOGS "qtbase")

qt6_deploy_runtime_dependencies(
    EXECUTABLE "/home/discojapi/Proyectos/ELO239/Stage3/build/Desktop_Qt_6_11_1-Debug/Stage2"
    GENERATE_QT_CONF
)
