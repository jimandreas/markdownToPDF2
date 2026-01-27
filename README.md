# Markdown to PDF converter (Kotlin)

A command-line tool that converts Markdown files to PDF using CommonMark for parsing and iText for HTML-to-PDF rendering.

## Status

Working. The converter handles headings, emphasis/bold, ordered and unordered lists, nested lists, blockquotes, fenced and inline code, links, GFM tables, horizontal rules, and inline HTML.

A JUnit 5 test suite with 14 tests verifies all supported features through the full conversion pipeline.

Some advanced Markdown features are not yet implemented. Pull requests are welcome.

## Usage

### Build

```bash
gradlew.bat build
```

### Run

The tool reads `file.md` from the project root and writes `file.pdf`:

```bash
gradlew.bat run
```

### Test

```bash
gradlew.bat test
```

### Programmatic use

The converter exposes two functions in `com.jimandreas`:

```kotlin
// Convert markdown text to a full HTML document with CSS styling
val html: String = convertMarkdownToHtml(markdownText)

// Convert a markdown file directly to PDF
convertMarkdownFileToPdf(inputFile, outputFile)
```

## Dependencies

- [CommonMark](https://github.com/commonmark/commonmark-java) 0.27.0 — Markdown parser
- [CommonMark GFM Tables](https://github.com/commonmark/commonmark-java) 0.27.0 — GFM table extension
- [iText html2pdf](https://github.com/itext/itext-pdfhtml) 6.3.0 — HTML to PDF conversion
- Kotlin 2.0.0, Gradle 8.14

## Background

The original code was written by Grok 4 Fast. Table formatting was added through iteration. The "ultimate" Markdown test file from StackOverflow was used for validation — some edge cases remain unhandled.

## References

- https://stackoverflow.com/questions/2238012/need-a-sufficiently-long-complex-markdown-document-for-performance-testing
- https://stackoverflow.com/a/2238081/3853712
- https://daringfireball.net/projects/markdown/syntax.text
- https://daringfireball.net/projects/markdown/
