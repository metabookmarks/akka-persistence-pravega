// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `akka-persistence-pravega` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.logback,
        library.pravega,
        library.scalaCheck % Test,
        library.scalaTest      % Test
      ) ++ library.akka
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val pravega        = "0.3.2"
      val scalaCheck     = "1.14.0"
      val scalaTest      = "3.0.5"
      val logback        = "1.1.3"
      val akka           = "2.5.17"
    }
    val logback = "ch.qos.logback"       % "logback-classic"             % Version.logback         % "test"
    val akka = Seq("com.typesafe.akka"   %% "akka-testkit"                % Version.akka            % "test",
    "com.typesafe.akka"   %% "akka-persistence"            % Version.akka            % "compile",
    "com.typesafe.akka"   %% "akka-persistence-tck"        % Version.akka            % "test")
    val pravega    = "io.pravega" % "pravega-client" % Version.pravega
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"    %% "scalatest"      % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.7",
    organization := "default",
    organizationName := "chelebithil",
    startYear := Some(2018),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-Ywarn-unused-import"
    ),
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
    testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
