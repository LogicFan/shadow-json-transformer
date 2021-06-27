import org.gradle.api.publish.PublishingExtension

plugins {
    `java-gradle-plugin`
    `maven-publish`
    groovy
    id("com.gradle.plugin-publish") version "0.15.0"
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val shadowVersion = "7.0.0"

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:$shadowVersion")
    implementation("com.google.code.gson:gson:2.8.7")

    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
}

gradlePlugin {
    plugins {
        create("shadowJsonTransformer") {
            id = "shadow.json-transformer"
            displayName = "Plugin adding json transformer on shadowJar"
            description = "a plugin add json transformer on shadowJar"
            implementationClass = "com.github.logicfan.gradle.shadow.ShadowJsonTransformerPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/LogicFan/shadow-json-transformer"
    version = "1.0.0"
    vcsUrl = "https://github.com/LogicFan/shadow-json-transformer"
    tags = listOf("shadow", "json")
}

val jar by tasks.existing

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        withType(MavenPublication::class) {
            groupId = "shadow.json-transformer"
            artifactId = "shadow.json-transformer.gradle.plugin"
            version = "1.0.0"
        }
    }
}

tasks.test {
    // Use junit platform for unit tests.
    useJUnitPlatform()
}
