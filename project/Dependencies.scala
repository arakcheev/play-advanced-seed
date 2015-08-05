import sbt._

object Dependencies {

  lazy val projectOneDeps: Seq[ModuleID] = Seq(
//    Deps here
  )

  lazy val applicationDeps: Seq[ModuleID] = Seq(
//    Deps here
  )

  lazy val webJarDependesies = Seq(
    "org.webjars" % "requirejs" % "2.1.16",
    "org.webjars" % "underscorejs" % "1.7.0",
    "org.webjars" % "jquery" % "1.11.1",
    "org.webjars" % "bootstrap" % "3.3.2" exclude("org.webjars", "jquery"),
    "org.webjars" % "angularjs" % "1.3.14" exclude("org.webjars", "jquery"),
    "org.webjars" % "angular-ui-bootstrap" % "0.12.1-1"
  )
}