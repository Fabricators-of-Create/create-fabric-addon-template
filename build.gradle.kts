@file:Suppress("UnstableApiUsage")

plugins {
    id("fabric-loom")
    id("io.github.p03w.machete")
    id("maven-publish")
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

base.archivesName = "archives_base_name"()
group = "maven_group"()

val buildNumber: String = System.getenv("GITHUB_RUN_NUMBER").let { if (it.isNullOrBlank()) "" else "-build.$it" }
version = "${"mod_version"()}+mc${"minecraft_version"()}${buildNumber}"

repositories {
    maven("https://maven.shedaniel.me/") // Cloth Config, REI
    maven("https://maven.blamejared.com/") // JEI
    maven("https://maven.parchmentmc.org") // Parchment mappings
    maven("https://maven.quiltmc.org/repository/release") // Quilt Mappings
    maven("https://api.modrinth.com/maven") // LazyDFU
    maven("https://maven.terraformersmc.com/releases/") // Mod Menu
    maven("https://mvn.devos.one/snapshots/") // Create, Porting Lib, Forge Tags, Milk Lib, Registrate
    maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") // Forge Config API Port
    maven("https://maven.jamieswhiteshirt.com/libs-release") // Reach Entity Attributes
    maven("https://jitpack.io/") // Mixin Extras, Fabric ASM
    maven("https://maven.tterrag.com/") // Flywheel
}

configurations.configureEach {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:${"fabric_loader_version"()}")
    }
}

dependencies {
    // Setup
    minecraft("com.mojang:minecraft:${"minecraft_version"()}")
    mappings(loom.layered {
        mappings("org.quiltmc:quilt-mappings:${"minecraft_version"()}+build.${"qm_version"()}:intermediary-v2")
        parchment("org.parchmentmc.data:parchment-${"minecraft_version"()}:${"parchment_version"()}@zip")
        officialMojangMappings { nameSyntheticMembers = false }
    })
    modImplementation("net.fabricmc:fabric-loader:${"fabric_loader_version"()}")

    // dependencies
    modImplementation("net.fabricmc.fabric-api:fabric-api:${"fabric_api_version"()}")

    // Create - dependencies are added transitively
    modImplementation("com.simibubi.create:create-fabric-${"minecraft_version"()}:${"create_version"()}")

    // Development QOL
    modLocalRuntime("maven.modrinth:lazydfu:${"lazydfu_version"()}")
    modLocalRuntime("com.terraformersmc:modmenu:${"modmenu_version"()}")

    // Recipe Viewers - Create Fabric supports JEI, REI, and EMI.
    // See root gradle.properties to choose which to use at runtime.
    when ("recipe_viewer"().lowercase()) {
        "jei" -> modLocalRuntime("mezz.jei:jei-${"minecraft_version"()}-fabric:${"jei_version"()}")
        "rei" -> modLocalRuntime("me.shedaniel:RoughlyEnoughItems-fabric:${"rei_version"()}")
        "emi" -> modLocalRuntime("dev.emi:emi:${"emi_version"()}")
        "disabled" -> {}
        else -> println("Unknown recipe viewer specified: ${"recipe_viewer"()}. Must be JEI, REI, EMI, or disabled.")
    }

    // if you would like to add integration with them, uncomment them here.
//    modCompileOnly("mezz.jei:jei-${"minecraft_version"()}-fabric:${"jei_fabric_version"()}")
//    modCompileOnly("mezz.jei:jei-${"minecraft_version"()}-common:${"jei_fabric_version"()}")
//    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:${"rei_version"()}")
//    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:${"rei_version"()}")
//    modCompileOnly("dev.emi:emi:${"emi_version"()}")
}

tasks.processResources {
    val properties = mapOf(
        "version" to project.version,
        "fabric_loader_version" to "fabric_loader_version"(),
        "fabric_api_version" to "fabric_api_version"(),
        "minecraft_version" to "minecraft_version"(),
        "create_version" to "create_version"(),
    )

    inputs.properties(properties)

    filesMatching("fabric.mod.json") {
        expand(properties)
    }
}

machete {
    png.enabled = false
    xml.enabled = false
    json.apply {
        enabled = true
        extraFileExtensions.add("mcmeta")
    }
}

tasks.jar {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${base.archivesName}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mod") {
            from(components["java"])
            artifact(tasks.jar)
            artifact(tasks["sourcesJar"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}

operator fun String.invoke(): String = rootProject.ext[this] as? String
    ?: error("Property $this is not defined")
