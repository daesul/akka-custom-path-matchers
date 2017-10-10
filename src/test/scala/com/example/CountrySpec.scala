package com.example

import akka.http.scaladsl.server.Rejection
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server.Directives._
import com.example.Country.country
import org.scalatest.{Matchers, WordSpec}

class CountrySpec extends WordSpec with Matchers with ScalatestRouteTest {

  val route =
    path(country) { country =>
      complete(OK -> country.toString)
    }


  val pathParamIs = afterWord("path parameter is")

  "CountryMatcher" should {

    "match the path parameter and complete correctly" when pathParamIs {

      "/de" in {
        Get("/de") ~> route ~> check {
          handled should equal (true)
          status should equal(OK)
          responseAs[String] should equal("De")
        }
      }


      "/fr" in {
        Get("/fr") ~> route ~> check {
          handled should equal(true)
          status should equal(OK)
          responseAs[String] should equal("Fr")
        }
      }

        "/gb" in {
        Get("/gb") ~> route ~> check {
          handled should equal (true)
          status should equal(OK)
          responseAs[String] should equal("Gb")
        }
      }

      "/us" in {
        Get("/us") ~> route ~> check {
          handled should equal (true)
          status should equal(OK)
          responseAs[String] should equal("Us")
        }
      }
    }

    "not handle" when pathParamIs {

      "/De" in {
        Get("/De") ~> route ~> check {
          handled should equal (false)
        }
      }

      "/DE" in {
        Get("/DE") ~> route ~> check {
          handled should equal (false)
        }
      }

      "/Test" in {
        Get("/Test") ~> route ~> check {
          handled should equal (false)
        }
      }
    }
  }
}
