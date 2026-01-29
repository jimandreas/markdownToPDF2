@file:Suppress("UnusedVariable")

package com.jimandreas

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class MarkdownToPdfTest {

    private fun convertAndExtractText(markdown: String): String {
        val inputFile = File.createTempFile("test", ".md")
        val outputFile = File.createTempFile("test", ".pdf")
        try {
            inputFile.writeText(markdown)
            convertMarkdownFileToPdf(inputFile, outputFile)
            PdfDocument(PdfReader(outputFile.absolutePath)).use { pdf ->
                val sb = StringBuilder()
                for (i in 1..pdf.numberOfPages) {
                    sb.append(PdfTextExtractor.getTextFromPage(pdf.getPage(i)))
                }
                return sb.toString()
            }
        } finally {
            inputFile.delete()
            outputFile.delete()
        }
    }

    @Test
    fun testHeadings() {
        val md = "# H1\n## H2\n### H3\n#### H4\n##### H5\n###### H6"
        val text = convertAndExtractText(md)
        for (i in 1..6) assertTrue(text.contains("H$i"), "Missing H$i")
    }

    @Test
    fun testEmphasisAndBold() {
        val text = convertAndExtractText("*italic* **bold**")
        assertTrue(text.contains("italic"))
        assertTrue(text.contains("bold"))
    }

    @Test
    fun testOrderedAndUnorderedLists() {
        val md = "- apple\n- banana\n\n1. first\n2. second"
        val text = convertAndExtractText(md)
        for (w in listOf("apple", "banana", "first", "second"))
            assertTrue(text.contains(w), "Missing $w")
    }

    @Test
    fun testNestedLists() {
        val md = "- parent\n  - child\n    - grandchild"
        val text = convertAndExtractText(md)
        for (w in listOf("parent", "child", "grandchild"))
            assertTrue(text.contains(w), "Missing $w")
    }

    @Test
    fun testBlockquotes() {
        val text = convertAndExtractText("> quote text here")
        assertTrue(text.contains("quote text here"))
    }

    @Test
    fun testFencedCodeBlock() {
        val md = "```\nval x = 42\n```"
        val text = convertAndExtractText(md)
        assertTrue(text.contains("val x = 42"))
    }

    @Test
    fun testInlineCode() {
        val text = convertAndExtractText("Use `codeSnippet` here")
        assertTrue(text.contains("codeSnippet"))
    }

    @Test
    fun testLinks() {
        val text = convertAndExtractText("[ClickHere](https://example.com)")
        assertTrue(text.contains("ClickHere"))
    }

    @Test
    fun testGfmTables() {
        val md = """
            | Name  | Age |
            |-------|-----|
            | Alice | 30  |
            | Bob   | 25  |
        """.trimIndent()
        val text = convertAndExtractText(md)
        for (w in listOf("Name", "Age", "Alice", "30", "Bob", "25"))
            assertTrue(text.contains(w), "Missing $w")
    }

    @Test
    fun testHorizontalRule() {
        val md = "above\n\n---\n\nbelow"
        val text = convertAndExtractText(md)
        assertTrue(text.contains("above"))
        assertTrue(text.contains("below"))
    }

    @Test
    fun testInlineHtml() {
        val text = convertAndExtractText("<b>boldhtml</b>")
        assertTrue(text.contains("boldhtml"))
    }

    @Test
    fun testIntegration() {
        val md = """
            # Title

            Some **bold** and *italic* text.

            - item1
            - item2

            > a quote

            | Col1 | Col2 |
            |------|------|
            | A    | B    |

            ```
            code block
            ```
        """.trimIndent()
        val text = convertAndExtractText(md)
        for (w in listOf("Title", "bold", "italic", "item1", "item2", "a quote", "Col1", "A", "B", "code block"))
            assertTrue(text.contains(w), "Missing '$w'")
    }

    @Test
    fun testEmptyInput() {
        val text = convertAndExtractText("")
        // Just verify no crash and PDF was created
        assertTrue(true)
    }

    @Test
    fun testHtmlStage() {
        val html = convertMarkdownToHtml("# Hello")
        assertTrue(html.contains("<h1>Hello</h1>"), "Missing h1 tag")
        assertTrue(html.contains("<style>"), "Missing style tag")
    }
}
