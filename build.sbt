ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.3.11",
  "org.typelevel" %% "cats-effect-cps" % "0.3.0",
  "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
)

scalacOptions ++= Seq(
  "-Yexplicit-nulls"
)

lazy val root = (project in file(".")).settings(
  name.withRank(KeyRanks.Invisible) := "ProjectScala3",
  idePackagePrefix.withRank(KeyRanks.Invisible) := Some("com.example")
)
