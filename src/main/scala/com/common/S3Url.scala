package com.common

class S3Url(url: String) {

  val (bucket, prefix) = url.drop(5).split("/").toList match {
    case head :: Nil => (head, "")
    case head :: tail => (head, tail.mkString("/"))
    case _ => sys.error(s"unrecognized s3 url: $url")
  }

  def /(subPath: String) = {
    val newKey = if (prefix == "") subPath else prefix + "/" + subPath
    new S3Url(s"s3://$bucket/$newKey")
  }

  override def toString = s"s3://$bucket/$prefix"
}