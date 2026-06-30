include("/home/discojapi/Descargas/Stage3/build/Desktop_Qt_6_11_1-Debug/.qt/QtDeploySupport.cmake")
include("${CMAKE_CURRENT_LIST_DIR}/Stage4-plugins.cmake" OPTIONAL)
set(__QT_DEPLOY_I18N_CATALOGS "qtbase")

qt6_deploy_runtime_dependencies(
    EXECUTABLE "/home/discojapi/Descargas/Stage3/build/Desktop_Qt_6_11_1-Debug/Stage4"
    GENERATE_QT_CONF
)
