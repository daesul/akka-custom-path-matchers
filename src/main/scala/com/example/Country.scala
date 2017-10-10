package com.example

import akka.http.scaladsl.server.{PathMatcher, PathMatcher1}

sealed trait Country
case object De extends Country
case object Fr extends Country
case object Gb extends Country
case object Us extends Country

object Country {

  def apply(from: String): Option[Country] = from match {
    case "de" => Some(De)
    case "fr" => Some(Fr)
    case "gb" => Some(Gb)
    case "us" => Some(Us)
    case _ => None
  }

  val country: PathMatcher1[Country] = {
    PathMatcher("[a-z]{2}".r) flatMap { path =>
      Country(path)
    }
  }
}
