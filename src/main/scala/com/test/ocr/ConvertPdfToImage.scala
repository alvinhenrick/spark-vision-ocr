package com.test.ocr


import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.{ImageType, PDFRenderer}
import org.apache.pdfbox.tools.imageio.ImageIOUtil

import scala.collection.JavaConversions._


object ConvertPdfToImage {
  def main(args: Array[String]): Unit = {

    val document = PDDocument.load(new File(""))
    val pdfRenderer = new PDFRenderer(document)
    var pageCounter = 0
    for (page <- document.getPages) {
      // note that the page number parameter is zero based
      val bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB)
      // suffix in filename will be used as the file format
      pageCounter = pageCounter + 1
      ImageIOUtil.writeImage(bim, "temp" + "-" + pageCounter + ".png", 300)
    }
  }
}