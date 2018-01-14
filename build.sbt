name := "spark-vision-ocr"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0" % "provided"
libraryDependencies += "com.google.cloud" % "google-cloud-vision" % "1.12.0"
libraryDependencies += "com.google.auth" % "google-auth-library-oauth2-http" % "0.9.0"
libraryDependencies += "com.google.guava" % "guava" % "23.0"
libraryDependencies += "org.apache.pdfbox" % "pdfbox" % "2.0.8"

