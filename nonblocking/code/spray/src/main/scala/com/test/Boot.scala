package com.test

import akka.actor.{Props, ActorSystem}
import spray.routing._
import spray.http.MediaTypes._
import scala.io.Source

object Boot extends App with SimpleRoutingApp {

  implicit val system = ActorSystem("demo")

  startServer(interface = "0.0.0.0", port = 9966) {
    path("read") {
      get {
	    respondWithMediaType(`text/html`) {
		  val source = Source.fromFile("/tmp/largefile")
		  val content = source.mkString
		  source.close()
		  complete(content)
		}
	  }
    }
  }
}