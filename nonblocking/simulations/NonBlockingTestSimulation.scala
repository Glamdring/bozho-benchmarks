package basic

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.HeaderNames._
import scala.concurrent.duration._

class NonBlockingTestSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://54.225.157.124:9966")
		.acceptCharsetHeader("ISO-8859-1,utf-8;q=0.7,*;q=0.7")
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
		.disableFollowRedirect

	val headers_1 = Map(
		"Keep-Alive" -> "115")

	val scn = scenario("Simple")
		.repeat(15) {
			exec(
				http("request_1")
					.get("/read")
					.headers(headers_1)
				)
		}
		.pause(0 milliseconds, 100 milliseconds)

	setUp(scn.inject(rampUsers(3500) over (30 seconds)))
		.protocols(httpProtocol)
}
