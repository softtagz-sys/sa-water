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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate:hibernate-core:6.5.2.Final")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.postgresql:postgresql:42.2.20")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testImplementation("org.testcontainers:junit-jupiter:1.17.3")
	testImplementation("org.testcontainers:postgresql:1.17.3")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
