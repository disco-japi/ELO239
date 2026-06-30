include("/home/discojapi/Proyectos/ELO239/Stage3/.qt/QtDeploySupport.cmake")
include("${CMAKE_CURRENT_LIST_DIR}/Stage2-plugins.cmake" OPTIONAL)
set(__QT_DEPLOY_I18N_CATALOGS "qtbase")

qt6_deploy_runtime_dependencies(
    EXECUTABLE "/home/discojapi/Proyectos/ELO239/Stage3/Stage2"
    GENERATE_QT_CONF
)
