package com.common

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.model.{ListObjectsV2Request, ListObjectsV2Result}
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}

import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

class S3Util(s3Url: S3Url) {


  val s3Client: AmazonS3 = {
    AmazonS3ClientBuilder.standard()
      .withCredentials(new ProfileCredentialsProvider("proddl"))
      .withRegion(Regions.US_EAST_1).build()
  }

  def isFolderExists: Boolean = {
    s3Client.doesObjectExist(s3Url.bucket, s3Url.prefix)
  }

  def listFiles(): List[String] = {

    val listObjectsRequest = new ListObjectsV2Request
    listObjectsRequest.setBucketName(s3Url.bucket)
    listObjectsRequest.setPrefix(s3Url.prefix)

    val keys = ArrayBuffer[String]()

    var result: ListObjectsV2Result = null

    do {
      result = s3Client.listObjectsV2(listObjectsRequest)
      for (objectSummary <- result.getObjectSummaries) {
        keys.append(objectSummary.getKey)
      }
      System.out.println("Next Continuation Token : " + result.getNextContinuationToken)
      listObjectsRequest.setContinuationToken(result.getNextContinuationToken)
    } while (result.isTruncated)

    keys.filter(s => s.matches("^.*[.].*$")).toList
  }
}
