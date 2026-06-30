include("/home/parg/Downloads/ELO239/Stage3/build/Clone_of_Desktop_Qt_6_11_1_Debug/.qt/QtDeploySupport.cmake")
include("${CMAKE_CURRENT_LIST_DIR}/Stage3-plugins.cmake" OPTIONAL)
set(__QT_DEPLOY_I18N_CATALOGS "qtbase")

qt6_deploy_runtime_dependencies(
    EXECUTABLE "/home/parg/Downloads/ELO239/Stage3/build/Clone_of_Desktop_Qt_6_11_1_Debug/Stage3"
    GENERATE_QT_CONF
)
