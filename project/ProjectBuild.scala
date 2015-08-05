import sbt._
import sbt.Keys._
import play.sbt.PlayScala
import play.sbt.Play.autoImport._
import Dependencies._

object ProjectBuild extends Build {

  val defaultScalaVersion = "2.11.6"

  var buildVersion = "0.1"

  val buildOrganization = "play-advances-seed"

  def commonSettings: Seq[Setting[_]] = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := defaultScalaVersion,
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
    .settings(libraryDependencies ++= docsProcessingDeps)
    .dependsOn(secondProject)

  lazy val secondProject = buildProject("Second-Project", "project2")

  lazy val server = buildProject("application", "application")
    .settings(libraryDependencies ++= serverDeps)
    .settings(PlayScala.projectSettings)
    .dependsOn(firstProject,secondProject)
    .enablePlugins(PlayScala)

  lazy val Root = Project(
    "Root",
    file("."))
    .settings(commonSettings: _*)
    .settings(
      run in Compile <<= (run in Compile in server)
     )
    .aggregate(server, firstProject)

}