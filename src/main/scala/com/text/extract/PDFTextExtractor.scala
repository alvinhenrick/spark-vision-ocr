package com.text.extract

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object PDFTextExtractor {

  /**
    * Main method
    *
    * @param args
    */
  def main(args: Array[String]) {
    PDFTextExtractorArgsParser.parseArgs(args, "PDFTextExtractor", "1.0") match {
      case Some(config) => runJob(config)
      case None =>
      //argument bad ,error message will have been displayed
    }
  }

  /**
    * Initiates the Spark Session
    *
    * @param cmdArgs parsed command line arguments
    */
  def runJob(cmdArgs: PDFTextExtractorArgs): Unit = {

    val sparkConf = if (cmdArgs.sparkConf.isEmpty) new SparkConf() else new SparkConf().setAll(cmdArgs.sparkConf)

    val sparkSession: SparkSession =
      SparkSession.builder().config(sparkConf).getOrCreate()


  }
}
