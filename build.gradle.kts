plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.edu.pe"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // Spring Boot Starter WebFlux (para programación reactiva)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    
    // Spring Boot DevTools (solo en tiempo de desarrollo)
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    
    // Spring Boot Starter Test (para pruebas)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    
    // Reactor Test (para pruebas con Reactor)
    testImplementation("io.projectreactor:reactor-test")
    
    // Spring Boot Starter Data R2DBC (para acceso reactivo a bases de datos)
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    
    // Driver PostgreSQL para R2DBC
    runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.12.RELEASE") 
    
    // Dependencia adicional para Jasync R2DBC MySQL (si alguna vez decides utilizarlo)
    // implementation("com.github.jasync-sql:jasync-r2dbc-mysql:2.1.16")
    
    implementation("org.apache.commons:commons-csv:1.9.0") 
  //  implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
    
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
