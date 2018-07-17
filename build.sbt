organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.12.4"

lazy val `lagom-client-demo` = (project in file("."))
  .settings(name := "lagom-client-demo")
  .aggregate(`httpbin-api`, `lagom-client-main`)


lazy val `httpbin-api` = (project in file("httpbin-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lagomJavadslJackson,
      "org.projectlombok" % "lombok" % "1.18.0" % "provided"
    ),
    javacOptions in(Compile, compile) ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-parameters")

  )


lazy val `lagom-client-main` = (project in file("lagom-client-main"))
  .settings(
    libraryDependencies ++= Seq(
      "com.lightbend.lagom" %% "lagom-javadsl-integration-client" % "1.5.0-SNAPSHOT"
    ),
    javacOptions in(Compile, compile) ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-parameters")

  ).dependsOn(`httpbin-api`)
