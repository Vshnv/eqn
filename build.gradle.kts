plugins {
    java
}

group = "io.github.vshnv"
version = "1.0"

repositories {
    maven("https://redempt.dev")
    mavenCentral()
}

dependencies {
    implementation("com.github.Redempt:Crunch:1.1.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
tasks.withType(JavaCompile::class) {
    options.compilerArgs.add("--enable-preview")
}