name := "hellome"
version := "1.0"

scalaVersion := "2.13.1"
scalacOptions := Seq("-feature", "-deprecation")
ThisBuild / turbo := true

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc, ehcache, guice, ws
)

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.197" % Test
  , "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
)

excludeDependencies ++= Seq(
  ExclusionRule("com.typesafe.play", "play-java")
  , ExclusionRule("com.typesafe.play", "play-json")
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
