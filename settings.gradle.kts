pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cats_clean_multi_module_mvvm"

include(":app")
include(":network")
include(":data")
include(":domain")
include(":data_api")
include(":domain_api")
include(":core")
include(":core_android")
include(":feature_cats_list")
include(":domain_models")
include(":network_api")
include(":feature_dialogs")
include(":feature_cats_details")
include(":feature_cats_details:navigation")
