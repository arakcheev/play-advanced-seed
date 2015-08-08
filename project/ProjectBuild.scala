
import Dependencies._
import com.typesafe.sbt.web._
import play.sbt.PlayScala
import sbt.Keys._
import sbt._

object ProjectBuild extends Build {

  val defaultScalaVersion = "2.11.6"

  var buildVersion = "0.1"

  val buildOrganization = "play-advances-seed"

  def commonSettings: Seq[Setting[_]] = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := defaultScalaVersion,
    libraryDependencies ++= commonDeps,
    resolvers += Resolvers.typesafeReleases,
    resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases" // specs2 depends on scalaz-stream
  )

  def sharedSettings: Seq[Setting[_]] = Seq(
    scalacOptions ++= Seq("-encoding", "UTF-8", "-Xlint", "-deprecation", "-unchecked", "-feature")
  )

  def buildProject(name: String, dir: String): Project = {
    Project(name, file(dir))
      .settings(sharedSettings: _*)
      .settings(commonSettings: _*)
  }

  lazy val firstProject = buildProject("First-Project", "project1")
    .settings(libraryDependencies ++= projectOneDeps)
    .dependsOn(secondProject)

  lazy val secondProject = buildProject("Second-Project", "project2")

  lazy val ui = buildProject("Client-Side", "ui")
    .enablePlugins(SbtWeb)
    .settings(libraryDependencies ++= webJarDependesies).addSbtFiles(file("build.sbt"))

  lazy val server = buildProject("application", "application")
    .settings(libraryDependencies ++= applicationDeps)
    .settings(PlayScala.projectSettings)
    .enablePlugins(PlayScala, SbtWeb)
    .dependsOn(firstProject, secondProject, ui)

  lazy val Root: Project = Project(
    "Root",
    file("."))
    .settings(commonSettings: _*)
    .settings(
      run in Compile <<= run in Compile in server,
      karma in Test := {
        "karma start ui/src/test/karma.conf.js" !
      },
      test := Def.taskDyn {
        val exitCode = (karma in Test).value
        if (exitCode == 0) {
          Def.task {
            (test in Test).value
          }
        }
        else throw new IllegalStateException("UI tests fails")
      }.value
    )
    .aggregate(server, firstProject, secondProject, ui)

  publishArtifact in Test := false

  parallelExecution in Test := false

  lazy val karma = taskKey[Int]("Run UI test via Karma")

}