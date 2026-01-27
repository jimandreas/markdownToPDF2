# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Kotlin command-line tool that converts Markdown files to PDF. Uses CommonMark for Markdown parsing (with GFM table support) and iText for HTML-to-PDF conversion.

## Build & Run Commands

```bash
# Build (Windows - this is a Windows project)
gradlew.bat build

# Run the converter (reads file.md, outputs file.pdf)
gradlew.bat run
```

No test suite exists. The project is validated manually using test markdown files (file.md, file-ReplicaIsland.md, file-simpletable.md, file-markdown-megatest.md).

## Architecture

Single-file application: `src/main/kotlin/Main.kt` (package `com.jimandreas`)

Processing pipeline:
1. Read input `.md` file
2. Parse with CommonMark + GFM tables extension
3. Render to HTML
4. Wrap HTML with CSS styling (Arial font, table borders)
5. Convert HTML to PDF via iText `HtmlConverter`

## Key Dependencies

- `org.commonmark:commonmark:0.27.0` — Markdown parser
- `org.commonmark:commonmark-ext-gfm-tables:0.27.0` — GFM table extension
- `com.itextpdf:html2pdf:6.3.0` — HTML to PDF conversion
- Build: Gradle 8.14 with Kotlin DSL, Kotlin 2.0.0
