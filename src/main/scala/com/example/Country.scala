package com.example

import akka.http.scaladsl.server.{PathMatcher, PathMatcher1}

sealed trait Country {
  val asLower = this.toString.toLowerCase
}
case object De extends Country
case object Fr extends Country
case object Gb extends Country
case object Us extends Country

object Country {

  def apply(from: String): Option[Country] = from match {
    case De.asLower => Some(De)
    case Fr.asLower => Some(Fr)
    case Gb.asLower => Some(Gb)
    case Us.asLower => Some(Us)
    case _ => None
  }

  val CountryMatcher: PathMatcher1[Country] = {
    PathMatcher("[a-z]{2}".r) flatMap { path =>
      Country(path)
    }
  }
}
