To add the CommonMark (for Markdown parsing) and iText pdfHTML (for HTML-to-PDF conversion) libraries to your Kotlin project in IntelliJ IDEA, the recommended approach is to use Gradle (Kotlin DSL) as your build system. This handles dependency resolution automatically from Maven Central. Manual JAR downloads are possible but not ideal for maintainability.

### Prerequisites
- Ensure your project is a Gradle-based Kotlin project. If starting from scratch:
    1. Open IntelliJ IDEA.
    2. Select **File > New > Project**.
    3. Choose **Gradle** on the left, then **Kotlin** as the language.
    4. Fill in project details (e.g., Group: `com.example`, Artifact: `markdown-to-pdf`, Kotlin version: latest stable like 2.0.0).
    5. Finish and let IntelliJ set up the project structure.
- If you already have a single-file Kotlin project (like the one from before), add a `build.gradle.kts` file to the root directory with the content below.

### Step 1: Configure `build.gradle.kts`
Replace or create `build.gradle.kts` in your project's root with this minimal setup (adjust versions as needed):

```kotlin
plugins {
    kotlin("jvm") version "2.0.0"  // Use the latest stable Kotlin version
    application
}

group = "com.example"
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
}

application {
    mainClass.set("com.example.MainKt")  // Adjust to your main class file name
}
```

- **Why these versions?**
    - CommonMark 0.27.0 is the latest release as of October 2025.
    - iText pdfHTML 6.3.0 is the latest release.
- The `html2pdf` artifact pulls in required iText modules like `kernel` automatically, so no extra lines are needed for `PdfDocument` or `PdfWriter`.

### Step 2: Sync the Project
1. Save the `build.gradle.kts` file.
2. IntelliJ will prompt you to **Import Changes** or **Sync Now** (a Gradle elephant icon appears in the toolbar). Click it.
    - If no prompt: Go to **View > Tool Windows > Gradle**, right-click your project, and select **Reload Gradle Project**.
3. Wait for dependencies to download (visible in the **Build** tool window). You'll see the libraries appear under **External Libraries** in the Project view.

### Step 3: Verify and Run
1. Place your Kotlin source file (e.g., `Main.kt` from the previous example) in `src/main/kotlin/`.
2. Ensure imports work (IntelliJ will highlight errors if not).
3. Run the program: Right-click the file > **Run 'MainKt'** (or use the green play button).
    - If issues arise (e.g., missing Kotlin plugin), install it via **File > Settings > Plugins > Search "Kotlin"**.

### Alternative: Adding via Maven (if preferred)
If your project uses Maven instead:
- Edit `pom.xml` under `<dependencies>`:
  ```xml
  <dependency>
      <groupId>org.commonmark</groupId>
      <artifactId>commonmark</artifactId>
      <version>0.27.0</version>
  </dependency>
  <dependency>
      <groupId>com.itextpdf</groupId>
      <artifactId>html2pdf</artifactId>
      <version>6.3.0</version>
  </dependency>
  ```
- Right-click `pom.xml` > **Maven > Reload project**.

### Troubleshooting
- **Sync fails?** Check internet/firewall (downloads from Maven Central). Or run `./gradlew build` in the terminal.
- **Version conflicts?** Use the latest from [Maven Repository](https://mvnrepository.com) and regenerate.
- **No build file?** For a quick test without Gradle: **File > Project Structure > Modules > Dependencies > + > JARs or directories**, then download JARs manually from the Maven links above—but switch to Gradle for production.

This keeps things simple while supporting your Markdown-to-PDF code. If your setup differs (e.g., multi-module), provide more details!