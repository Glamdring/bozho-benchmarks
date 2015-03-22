organization  := "com.test"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo"         at "http://repo.spray.io/",
  "sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo"      at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can"         % "1.3.1",
  "io.spray"            %   "spray-routing"     % "1.3.1",
  "org.slf4j"           %   "slf4j-simple"      % "1.7.2",
  "com.typesafe.akka" 	%%  "akka-actor" 		% "2.3.9"
)

seq(Revolver.settings: _*)
