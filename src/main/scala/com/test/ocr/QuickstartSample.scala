package com.test.ocr

// Imports the Google Cloud client library
import java.nio.file.{Files, Paths}

import com.google.cloud.vision.v1.Feature.Type
import com.google.cloud.vision.v1.{
  AnnotateImageRequest,
  Feature,
  Image,
  ImageAnnotatorClient
}
import com.google.protobuf.ByteString

import scala.collection.JavaConverters._

// [START vision_quickstart]
// Imports the Google Cloud client library

object QuickstartSample {

  def main(args: Array[String]) {
    // Instantiates a client
    val vision = ImageAnnotatorClient.create
    // The path to the image file to annotate
    val fileName =
      "/Users/shona/IdeaProjects/spark-vision-ocr/resources/wakeupcat.jpg"
    // Reads the image file into memory
    val path = Paths.get(fileName)
    val data = Files.readAllBytes(path)
    val imgBytes = ByteString.copyFrom(data)
    // Builds the image annotation request

    val img = Image.newBuilder.setContent(imgBytes).build()
    val feat = Feature.newBuilder.setType(Type.LABEL_DETECTION).build()
    val request =
      AnnotateImageRequest.newBuilder.addFeatures(feat).setImage(img).build()
    val requests = List(request).asJava
    // Performs label detection on the image file
    val response = vision.batchAnnotateImages(requests)
    val responses = response.getResponsesList.asScala

    responses.foreach { res =>
      if (res.hasError) {
        System.out.printf("Error: %s\n", res.getError.getMessage)
        return
      }
      val annotations = res.getLabelAnnotationsList.asScala
      print(annotations.size)
      annotations.foreach { annotation =>
        annotation.getAllFields.asScala.foreach {
          case (k, v) => printf("%s : %s\n", k, v.toString)
        }
      }
    }
    vision.close()
  }
}

// [END vision_quickstart]
