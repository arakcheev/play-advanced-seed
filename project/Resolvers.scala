import sbt._

object Resolvers{
  val typesafeReleases = "Typesafe Releases Repository" at "https://repo.typesafe.com/typesafe/releases/"
  val typesafeSnapshots = "Typesafe Snapshots Repository" at "https://repo.typesafe.com/typesafe/snapshots/"

  val scoverage = Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)
}