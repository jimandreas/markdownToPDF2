package com.jimandreas

import com.itextpdf.html2pdf.HtmlConverter
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import java.io.File

fun main() {
    val inputFile = File("file.md")
    if (!inputFile.exists()) {
        println("file.md not found")
        return
    }

//    val markdown = inputFile.readText()
//
//    val parser = Parser.builder().build()
//    val document = parser.parse(markdown)
//    val renderer = HtmlRenderer.builder().escapeHtml(true).build()
//    var html = renderer.render(document)

    val markdown = inputFile.readText()
    val parser = Parser.builder()
        .extensions(listOf(TablesExtension.create()))  // Enable tables parsing
        .build()
    val document = parser.parse(markdown)
    val renderer = HtmlRenderer.builder()
        .extensions(listOf(TablesExtension.create()))  // Enable tables rendering to HTML
        .escapeHtml(true)
        .build()
    var html = renderer.render(document)

    // Wrap in full HTML with Arial font and basic table styling
    html = """
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
            $html
        </body>
        </html>
    """.trimIndent().toString()

    val outputFile = File("file.pdf")
    val outputStream = outputFile.outputStream()
    HtmlConverter.convertToPdf(html, outputStream)  // Uses OutputStream overload


    println("Converted file.md to file.pdf")
}