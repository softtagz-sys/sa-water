plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "kdg.be"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA and Hibernate
	implementation("org.springframework.boot:spring-boot-starter-validation") // Validation
	implementation("org.springframework.boot:spring-boot-starter-web") // Web and RESTful services
	implementation("org.springframework.boot:spring-boot-starter-actuator") // Actuator for monitoring

	// Database
	implementation("org.postgresql:postgresql:42.2.20") // PostgreSQL driver
	implementation("org.hibernate:hibernate-core:6.5.2.Final") // Hibernate ORM

	// OpenAPI Documentation
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0") // OpenAPI and Swagger UI

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools") // DevTools for live reload

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Testing with Spring Boot
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA testing
	testImplementation("org.testcontainers:junit-jupiter:1.17.3") // Testcontainers for JUnit
	testImplementation("org.testcontainers:postgresql:1.17.3") // Testcontainers for PostgreSQL
	testRuntimeOnly("org.junit.platform:junit-platform-launcher") // JUnit Platform Launcher
	testImplementation ("org.springframework.security:spring-security-test") // Spring Security testing

	// Lombok
	compileOnly("org.projectlombok:lombok") // Lombok for reducing boilerplate code
	annotationProcessor("org.projectlombok:lombok") // Lombok annotation processor

	// Security
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client") // OAuth2 client
	implementation("org.springframework.boot:spring-boot-starter-security") // Spring Security
	implementation("org.springframework.security:spring-security-oauth2-jose") // JOSE for OAuth2
}

tasks.withType<Test> {
	useJUnitPlatform()
}
