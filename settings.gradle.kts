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
