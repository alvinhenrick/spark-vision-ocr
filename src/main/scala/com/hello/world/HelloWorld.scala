package com.hello.world

// import required spark classes
import org.apache.spark.{SparkConf, SparkContext}

// define main method (Spark entry point)
object HelloWorld {
  def main(args: Array[String]) {

    // initialise spark context
    val conf = new SparkConf().setAppName("HelloWorld")
    val sc = new SparkContext(conf)

    // do stuff
    println("************")
    println("************")
    println("Hello, world!")
    println("************")
    println("************")

    // terminate spark context
    sc.stop()

  }
}
