pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/")
		maven("https://server.bbkr.space/artifactory/libs-release/")
		maven("https://maven.quiltmc.org/repository/release")
		mavenCentral()
		gradlePluginPortal()
	}

    plugins {
        id("fabric-loom") version(extra["loom_version"] as String)
        id("io.github.p03w.machete") version(extra["machete_version"] as String)
    }
}

rootProject.name = "Create Fabric Addon Template"
