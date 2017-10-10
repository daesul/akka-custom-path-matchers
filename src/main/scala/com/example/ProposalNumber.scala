package com.example


import akka.http.scaladsl.server.{PathMatcher, PathMatcher1}


final case class ProposalNumber(year: Int, number: Int)

object ProposalNumber {

  def apply(from: String): Option[ProposalNumber] = {
    val year = from.substring(0, 4)
    val number = from.substring(4)
    Some(ProposalNumber(year.toInt, number.toInt))
  }

  val proposalNumberMatcher: PathMatcher1[ProposalNumber] = {
    PathMatcher("[0-9]{4}[0-9]{7}".r) flatMap { path =>
      ProposalNumber(path)
    }
  }
}
