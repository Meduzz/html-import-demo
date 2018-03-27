name := "html-import-demo"

version := "0.1"

scalaVersion := "2.12.5"

resolvers += "se.chimps.cameltow" at "https://yamr.kodiak.se/maven"

libraryDependencies ++= Seq(
	"se.chimps.cameltow" %% "cameltow" % "2.0-beta14",
	"se.chimps.bitzness" %% "bitzness-mini" % "20180210"
)