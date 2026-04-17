plugins {
    id("java")
    id("org.springframework.boot") version("4.0.5")
}

dependencies {
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))

    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly("org.hsqldb:hsqldb")
}
