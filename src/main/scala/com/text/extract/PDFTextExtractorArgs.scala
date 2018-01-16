package com.text.extract

import java.util.Calendar

case class PDFTextExtractorArgs(input: String = "",
                                output: String = "",
                                sparkConf: Map[String, String] = Map.empty,
                                day: Calendar = Calendar.getInstance())
  extends Serializable
