import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  kotlin("jvm")
  kotlin("plugin.serialization") version "1.5.0"
  id("org.jetbrains.compose")
}

group = "ninja.b1t"
version = "1.0-SNAPSHOT"


repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  google()
}

val ktor_version: String by project
val logback_version: String by project


dependencies {
  // Note, if you develop a library, you should use compose.desktop.common.
  // compose.desktop.currentOs should be used in launcher-sourceSet
  // (in a separate module for demo project and in testMain).
  // With compose.desktop.common you will also lose @Preview functionality
  implementation(compose.desktop.currentOs)
  implementation("org.jetbrains.compose.material3:material3-desktop:1.2.1")
  implementation(compose.desktop.uiTestJUnit4)
  implementation(compose.desktop.currentOs)
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

  // ktor
  implementation("io.ktor:ktor-client-core:$ktor_version")
  implementation("io.ktor:ktor-client-cio:$ktor_version")
  implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

  // logging
  implementation("ch.qos.logback:logback-classic:$logback_version")
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "UI"
      packageVersion = "1.0.0"
    }
  }
}
