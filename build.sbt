name := "spark-vision-ocr"

version := "1.0"

scalaVersion := "2.11.12"
val sparkVersion = "2.2.0"

scalacOptions := Seq("-unchecked", "-deprecation")

// remove version-specific scala dirs
crossPaths := false

// suppress unnecessary java directories
unmanagedSourceDirectories in Compile ~= {
  _.filter(_.exists)
}
unmanagedSourceDirectories in Test ~= {
  _.filter(_.exists)
}

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.2.0" % Provided,
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-hive" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided,
  "com.holdenkarau" %% "spark-testing-base" % "2.2.0_0.7.4" % Test,
  "com.google.cloud" % "google-cloud-vision" % "1.12.0",
  "com.google.auth" % "google-auth-library-oauth2-http" % "0.9.0",
  "com.google.guava" % "guava" % "23.0",
  "org.apache.pdfbox" % "pdfbox" % "2.0.8",
  "org.apache.pdfbox" % "pdfbox-tools" % "2.0.8",
  "com.github.scopt" %% "scopt" % "3.3.0",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.221"
)


