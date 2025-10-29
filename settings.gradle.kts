
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven { url = uri("https://raw.githubusercontent.com/LoserHasToFall98/tcmap/master/") }
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://raw.githubusercontent.com/LoserHasToFall98/tcmap/master/") }
        maven { url = uri("https://raw.githubusercontent.com/LoserHasToFall98/tcmap/master/") }
    }
}


rootProject.name = "ICLICKIPAY"
include(":app")
include(":core:ui")
include(":core:design")
include(":core:data")
include(":core:domain")
include(":core:di")
include(":uber")
include(":ibank")
include(":chat")
include(":tinder")
include(":doctor")
include(":eat")
include(":pet")
include(":hotel")
include(":learn")
include(":handyman")
include(":mechanic")
include(":pcrepair")
include(":laundry")
include(":delivery")
include(":babysitter")
include(":houseclean")
include(":home")
include(":auth")
