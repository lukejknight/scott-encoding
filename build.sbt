import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.github",
      scalaVersion := "2.12.6",
      version      := "1.0.0"
    )),
    name := "scott-encoding",
    libraryDependencies ++= Seq(
      scalaTest % Test
    )
  )
