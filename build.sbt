organization := "africa.gasy"

name := "gasy-fanorona-scala"

description := "Gasy Fanorona"

version := "1.0-SNAPSHOT"

lazy val root = project in file(".")

scalaVersion := "2.13.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % Test

scalacOptions ++= Seq(
    "-unchecked"
  , "-deprecation"
  , "-feature"
  , "-language:postfixOps"
  , "-language:reflectiveCalls"
)



