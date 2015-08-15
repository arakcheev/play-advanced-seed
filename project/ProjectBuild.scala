
import Dependencies._
import com.typesafe.sbt.web._
import play.sbt.PlayScala
import sbt.Keys._
import sbt._


import com.typesafe.sbt.web.Import._
import com.typesafe.sbt.web.Import.WebKeys._
import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import com.typesafe.sbt.rjs.Import.RjsKeys
import com.typesafe.sbt.rjs.Import._
import com.typesafe.sbt.digest.Import._
import com.typesafe.sbt.gzip.Import._

object ProjectBuild extends Build {

  val defaultScalaVersion = "2.11.6"

  var buildVersion = "1.1"

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
    .settings(libraryDependencies ++= webJarDependesies)

  lazy val server = buildProject("application", "application")
    .settings(libraryDependencies ++= applicationDeps)
    .settings(PlayScala.projectSettings)
    .enablePlugins(PlayScala, SbtWeb)
    .dependsOn(firstProject, secondProject, ui)
    .settings(
      RjsKeys.generateSourceMaps := false,
      RjsKeys.paths ++= Map("jsRoutes" ->("/jsRoutes", "empty:")),
      RjsKeys.baseUrl := webModulesLib.value + "/" + ui.id.toLowerCase,
      JsEngineKeys.engineType := JsEngineKeys.EngineType.Node,
      JsEngineKeys.parallelism := 8,
      pipelineStages := Seq(rjs, digest, gzip)
    )

  lazy val Root: Project = Project(
    "Root",
    file("."))
    .enablePlugins(PlayScala, SbtWeb)
    .settings(commonSettings: _*)
    .settings(
      run in Compile <<= run in Compile in server,
      test := allTestTask.value,
      karma in Test := runOrExit(useDefaults = true)
    )
    .aggregate(server, firstProject, secondProject)


  publishArtifact in Test := false

  parallelExecution in Test := false

  lazy val karma = taskKey[Int]("Run UI test via Karma")

  def runOrExit(useDefaults: Boolean = false): Int = {
    if (useDefaults) {
      s"${karmaCommand.getOrElse("karma")} start ui/src/test/karma.conf.js $karmaArgs" !
    } else {
      karmaCommand.fold(0) { command =>
        s"$command start ui/src/test/karma.conf.js $karmaArgs" !
      }
    }
  }

  def allTestTask: Def.Initialize[Task[Unit]] =
    Def.taskDyn {
      val exitCode = runOrExit()
      if (exitCode == 0) {
        Def.task {
          (test in Test).value
        }
      } else {
        throw new IllegalStateException("UI tests fails")
      }
    }

  /**
   * Path to karma, for example
   *
   * sbt test -Dkarma=/usr/bin/karma.
   */
  def karmaCommand = sys.props.get("karma")

  /**
   * Karma options
   *
   * ex:  sbt karma -Dkarma=./node_modules/karma/bin/karma -Dkarma-opt="--log-level=debug"
   */
  def karmaArgs = sys.props.get("karma-opt").getOrElse("--single-run")

}