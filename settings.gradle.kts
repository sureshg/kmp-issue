pluginManagement { includeBuild("gradle/build-logic") }

plugins { id("settings.repos") }

rootProject.name = "kmp-issue"

include(":shared")

include(":backend:jvm")

include(":web")

val nativeBuild: String? by settings

if (nativeBuild.toBoolean()) {
  include(":backend:native")
}
