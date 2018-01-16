package com.text.extract

import java.util.Calendar

object PDFTextExtractorArgsParser extends Serializable {

  def parseArgs(args: Array[String],
                headText: String,
                headVersion: String): scala.Option[PDFTextExtractorArgs] = {

    val parser = new scopt.OptionParser[PDFTextExtractorArgs]("Arguments") {
      head(headText, headVersion)
      opt[String]("input").action { (x, c) =>
        c.copy(input = x)
      } required() valueName "<input>" text "s3 location of input data bucket"

      opt[String]("output").action { (x, c) =>
        c.copy(output = x)
      } required() valueName "<output>" text "s3 location of output bucket"

      opt[Map[String, String]]("spark-conf").action { (x, c) =>
        c.copy(sparkConf = x)
      } optional() valueName "<spark-conf>" text "we can pass spark configuration here if needed"

      opt[Calendar]("day").action { (x, c) =>
        c.copy(day = x)
      } optional() valueName "<day>" text "day the process is run"
    }
    parser.parse(args, PDFTextExtractorArgs())
  }
}
