lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := "todd-norris",
    version := "2.7.x",
    scalaVersion := "2.13.0",
    libraryDependencies ++= Seq(
      guice,
      javaJpa,
      "com.h2database" % "h2" % "1.4.199",
      "org.hibernate" % "hibernate-core" % "5.4.2.Final",
      "io.dropwizard.metrics" % "metrics-core" % "3.2.6",
      "com.palominolabs.http" % "url-builder" % "1.1.0",
      "net.jodah" % "failsafe" % "1.0.5",
    ),
    PlayKeys.externalizeResources := false,
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v")),
    javacOptions ++= Seq(
      "-Xlint:unchecked",
      "-Xlint:deprecation"
    )
  )
