plugins {
    kotlin("jvm") version "2.0.0"  // Use the latest stable Kotlin version
    application
}

group = "com.jimandreas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))  // Standard Kotlin stdlib

    // CommonMark for Markdown parsing and HTML rendering
    implementation("org.commonmark:commonmark:0.27.0")

    // iText pdfHTML for converting HTML to PDF (includes kernel dependencies)
    implementation("com.itextpdf:html2pdf:6.3.0")

    // SLF4J simple binding to quiet the warning
    implementation("org.slf4j:slf4j-simple:2.0.17")

    // GFM Tables extension for CommonMark
    implementation("org.commonmark:commonmark-ext-gfm-tables:0.27.0")
}

//application {
//    mainClass.set("com.example.MainKt")  // Adjust to your main class file name
//}
/*
plugins {
    kotlin("jvm") version "2.2.20"
}

group = "com.jimandreas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}*/
