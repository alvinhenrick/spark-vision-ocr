package com.test.ocr

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object ReadingText {

  def main(args: Array[String]): Unit = {

    //Loading an existing document//Loading an existing document

    val file = new File("/Users/ahenrick/Desktop/example-to-ocr/images2/18941614.pdf")
    val document = PDDocument.load(file)

    //Instantiate PDFTextStripper class
    val pdfStripper = new PDFTextStripper()

    //Retrieving text from PDF document
    val text = pdfStripper.getText(document)
    System.out.println(text)

    //Closing the document
    document.close()
  }

}
