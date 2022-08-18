plugins {
    java
    signing
    `maven-publish`
    id("com.github.johnrengelman.shadow").version("7.1.0")
    id("io.papermc.paperweight.userdev").version("1.3.3")
}

group = "ru.ckateptb"
version = "1.1.2"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://ci.ender.zone/plugin/repository/everything/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven {
        url = uri("https://repo.minebench.de/")
        content {
            includeGroup("de.themoep")
        }
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
        content {
            includeModule("net.wesjd", "anvilgui")
        }
    }
}

dependencies {
    implementation("net.wesjd:anvilgui:1.5.3-SNAPSHOT")
    implementation("xyz.upperlevel.spigot.book:spigot-book-api:1.6")
    implementation("de.themoep:minedown:1.7.1-SNAPSHOT")
    implementation("com.zaxxer:HikariCP:3.4.2")
    implementation("com.j256.ormlite:ormlite-jdbc:6.0")
    implementation("xyz.xenondevs:particle:1.7")
//    implementation("org.jooq:joor:0.9.13")
    // high performance, near optimal caching library https://github.com/ben-manes/caffeine
    implementation("com.github.ben-manes.caffeine", "caffeine", "3.0.5") {
        exclude(module = "checker-qual")
    }
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
    compileOnly("dev.jorel.CommandAPI:commandapi-core:6.4.0")
    compileOnly("org.projectlombok", "lombok", "1.18.22")
    annotationProcessor("org.projectlombok", "lombok", "1.18.22")
}

tasks {
    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.${archiveExtension.getOrElse("jar")}")
        dependencies {
            relocate("net.wesjd.anvilgui", "ru.ckateptb.tablecloth.gui.anvil")
            relocate("xyz.upperlevel.spigot.book", "ru.ckateptb.tablecloth.gui.book")
            relocate("de.themoep.minedown", "ru.ckateptb.tablecloth.minedown")
            relocate("com.j256.ormlite", "ru.ckateptb.tablecloth.storage.ormlite")
            relocate("com.zaxxer.hikari", "ru.ckateptb.tablecloth.storage.hikari")
            relocate("xyz.xenondevs.particle", "ru.ckateptb.tablecloth.particle")
            relocate("org.joor", "ru.ckateptb.tablecloth.reflection")
            relocate("com.github.benmanes.caffeine.cache", "ru.ckateptb.tablecloth.cache")
        }
    }
    build {
        dependsOn(reobfJar, shadowJar)
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    named<Copy>("processResources") {
        filesMatching("plugin.yml") {
            expand("projectVersion" to project.version)
        }
        from("LICENSE") {
            rename { "${project.name.toUpperCase()}_${it}" }
        }
    }
}

publishing {
    publications {
        publications.create<MavenPublication>("maven") {
            artifacts {
                artifact(tasks.shadowJar) {
                    classifier = ""
                }
                artifact(tasks["sourcesJar"])
            }
        }
    }
}