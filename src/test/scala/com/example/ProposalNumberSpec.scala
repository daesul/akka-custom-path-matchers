package com.example

import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.example.ProposalNumber.proposalNumber
import org.scalatest.{Matchers, WordSpec}

class ProposalNumberSpec extends WordSpec with Matchers with ScalatestRouteTest {
  val route =
    path(proposalNumber) { proposalNumber: ProposalNumber =>
      complete(OK -> proposalNumber.toString)
    }

  val pathParamIs = afterWord("path parameter is")

  "ProposalNumberMatcher" should {

    "match the path parameter and complete correctly" when pathParamIs {

      "/20170020439" in {
        Get("/20170020439") ~> route ~> check {
          handled should equal(true)
          status should equal(OK)
          responseAs[String] should equal(ProposalNumber(2017, 20439).toString)
        }
      }

      "/20171000000" in {
        Get("/20171000000") ~> route ~> check {
          handled should equal(true)
          status should equal(OK)
          responseAs[String] should equal(ProposalNumber(2017, 1000000).toString)
        }
      }
    }

    "not handle" when pathParamIs {

      "/20170020439000000" in {
        Get("/20170020439000000") ~> route ~> check {
          handled should equal(false)
        }
      }

      "/2017" in {
        Get("/2017") ~> route ~> check {
          handled should equal(false)
        }
      }

      "/2017test" in {
        Get("/2017test") ~> route ~> check {
          handled should equal(false)
        }
      }

      "/test" in {
        Get("/test") ~> route ~> check {
          handled should equal(false)
        }
      }
    }
  }


}
