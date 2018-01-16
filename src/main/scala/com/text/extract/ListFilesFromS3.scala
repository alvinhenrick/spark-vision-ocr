package com.text.extract

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.{ListObjectsV2Request, ListObjectsV2Result}

import scala.collection.JavaConversions._


object ListFilesFromS3 {

  def main(args: Array[String]): Unit = {
    //val s3Client = AmazonS3ClientBuilder.defaultClient
    val s3Client = AmazonS3ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider("temp")).withRegion(Regions.US_EAST_1).build()
    val listObjectsRequest = new ListObjectsV2Request
    listObjectsRequest.setBucketName("bucket_text")
    listObjectsRequest.setPrefix("test")
    var result: ListObjectsV2Result = null
    do {
      result = s3Client.listObjectsV2(listObjectsRequest)
      for (objectSummary <- result.getObjectSummaries) {
        System.out.println(" - " + objectSummary.getKey + "  " + "(size = " + objectSummary.getSize + ")")
      }
      System.out.println("Next Continuation Token : " + result.getNextContinuationToken)
      listObjectsRequest.setContinuationToken(result.getNextContinuationToken)
    } while (result.isTruncated)
  }

}
