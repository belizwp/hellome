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

packageBin in Universal := {
  val originalFileName = (packageBin in Universal).value
  val (base, ext) = originalFileName.baseAndExt
  val newFileName = file(originalFileName.getParent) / (base + "_dist." + ext)
  val extractedFiles = IO.unzip(originalFileName, file(originalFileName.getParent))
  val mappings: Set[(File, String)] = extractedFiles.map(f => (f, f.getAbsolutePath.substring(originalFileName.getParent.length + base.length + 2)))
  val binFiles = mappings.filter { case (_, path) => path.startsWith("bin/") }
  for (f <- binFiles) f._1.setExecutable(true)
  com.typesafe.sbt.packager.universal.ZipHelper.zip(mappings, newFileName)
  IO.move(newFileName, originalFileName)
  IO.delete(file(originalFileName.getParent + "/" + originalFileName.base))
  originalFileName
}

lazy val root = (project in file(".")).enablePlugins(PlayScala)
