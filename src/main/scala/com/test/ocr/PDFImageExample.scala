package com.test.ocr

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument

import scala.collection.JavaConversions._

object PDFImageExample {

  def main(args: Array[String]): Unit = {
    val file = new File("/Users/ahenrick/Desktop/example-to-ocr/images2/18941614.pdf")
    val document = PDDocument.load(file)
    val pages = document.getPages
    for (page <- pages) {
      val pdResources = page.getResources
      val cosNames = pdResources.getXObjectNames
      for (cosName <- cosNames) {
        println(pdResources.isImageXObject(cosName))
      }
    }
  }

}
