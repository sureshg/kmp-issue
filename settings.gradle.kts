pluginManagement {
  repositories {
    // https://github.com/gradle/gradle/issues/16654
    mavenCentral()
    gradlePluginPortal()
  }
  includeBuild("gradle/build-logic")
}

plugins { id("settings.repos") }

rootProject.name = "kmp-issue"

include(":shared")

include(":backend:jvm")

include(":web")

val nativeBuild: String? by settings

if (nativeBuild.toBoolean()) {
  include(":backend:native")
}
