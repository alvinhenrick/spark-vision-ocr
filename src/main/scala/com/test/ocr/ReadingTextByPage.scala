package com.test.ocr

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object ReadingTextByPage {

  def main(args: Array[String]): Unit = {
    //Loading an existing document//Loading an existing document

    val file = new File("/Users/ahenrick/Desktop/example-to-ocr/images2/18941614.pdf")
    val document = PDDocument.load(file)

    //Instantiate PDFTextStripper class
    val pdfStripper = new PDFTextStripper()

    //Retrieving text from PDF document
    for (index <- 0 until document.getNumberOfPages) {
      val tempIndex = index + 1
      pdfStripper.setStartPage(tempIndex)
      pdfStripper.setEndPage(tempIndex)
      System.out.println(tempIndex + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
      System.out.println(pdfStripper.getText(document))
    }
    //Closing the document
    document.close()
  }

}
