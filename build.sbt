import org.scalajs.linker.interface.ModuleSplitStyle

import scala.sys.process.Process

lazy val root = project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
    .settings(
      name                            := "slides",
      scalaVersion                    := "3.4.2",
      scalacOptions ++= Seq("-encoding", "utf-8", "-deprecation", "-feature"),
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig ~= {
          _.withModuleKind(ModuleKind.ESModule)
              .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("slides")))
      },
      stFlavour := Flavour.Normal,
      libraryDependencies ++= Seq(
        "org.scala-js"       %%% "scalajs-dom" % "2.4.0",
        "com.raquo"          %%% "laminar"     % "16.0.0",
        "com.raquo"          %%% "waypoint"    % "7.0.0",
        "io.frontroute"      %%% "frontroute"  % "0.18.1",
        "io.laminext"        %%% "fetch"       % "0.16.1",
        "io.laminext"        %%% "websocket"   % "0.16.1",
        "io.github.iltotore" %%% "iron"        % "2.6.0",
        "org.typelevel"      %%% "literally"   % "1.2.0"
      ),
      externalNpm := {
          Process("npm", baseDirectory.value).!
          baseDirectory.value
      },
    )
