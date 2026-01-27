package com.jimandreas

import com.itextpdf.html2pdf.HtmlConverter
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import java.io.File

fun convertMarkdownToHtml(markdown: String): String {
    val extensions = listOf(TablesExtension.create())
    val parser = Parser.builder()
        .extensions(extensions)
        .build()
    val document = parser.parse(markdown)
    val renderer = HtmlRenderer.builder()
        .extensions(extensions)
        .escapeHtml(false)
        .build()
    val bodyHtml = renderer.render(document)

    return """
        <html>
        <head>
            <style>
                body { font-family: Arial, sans-serif; }
                h1, h2, h3, h4, h5, h6 { font-family: Arial, sans-serif; }
                table { border-collapse: collapse; width: 100%; }
                th, td { border: 1px solid black; padding: 8px; text-align: left; }
                th { background-color: #f2f2f2; }
                ul, ol { font-family: Arial, sans-serif; }
            </style>
        </head>
        <body>
            $bodyHtml
        </body>
        </html>
    """.trimIndent()
}

fun convertMarkdownFileToPdf(inputFile: File, outputFile: File) {
    val markdown = inputFile.readText()
    val html = convertMarkdownToHtml(markdown)
    outputFile.outputStream().use { outputStream ->
        HtmlConverter.convertToPdf(html, outputStream)
    }
}

fun main() {
    val inputFile = File("file.md")
    if (!inputFile.exists()) {
        println("file.md not found")
        return
    }
    convertMarkdownFileToPdf(inputFile, File("file.pdf"))
    println("Converted file.md to file.pdf")
}