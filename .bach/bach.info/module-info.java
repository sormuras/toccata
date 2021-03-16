import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.External;
import com.github.sormuras.bach.ProjectInfo.Externals;
import com.github.sormuras.bach.ProjectInfo.Externals.Name;
import com.github.sormuras.bach.ProjectInfo.Tools;
import com.github.sormuras.bach.ProjectInfo.Tweak;
import com.github.sormuras.bach.project.JavaStyle;

@ProjectInfo(
    name = "toccata",
    version = "1-ea",
    format = JavaStyle.GOOGLE,
    includeSourceFilesIntoModules = true,
    tools = @Tools(skip = "javadoc"),
    tweaks =
        @Tweak(
            tool = "jlink",
            option = "--launcher",
            value = "toccata=com.github.sormuras.toccata/com.github.sormuras.toccata.Toccata"),
    // <editor-fold desc="Libraries - External Modules">
    lookupExternal = {
      // Jackson
      @External(
          module = "com.fasterxml.jackson.annotation",
          via = "com.fasterxml.jackson.core:jackson-annotations:2.12.1"),
      @External(
          module = "com.fasterxml.jackson.core",
          via = "com.fasterxml.jackson.core:jackson-core:2.12.1"),
      @External(
          module = "com.fasterxml.jackson.databind",
          via = "com.fasterxml.jackson.core:jackson-databind:2.12.1"),
      // Gluon Attach
      @External(module = "com.gluonhq.attach.audio", via = "com.gluonhq.attach:audio:4.0.10"),
      @External(
          module = "com.gluonhq.attach.lifecycle",
          via = "com.gluonhq.attach:lifecycle:4.0.10"),
      @External(module = "com.gluonhq.attach.storage", via = "com.gluonhq.attach:storage:4.0.10"),
      @External(module = "com.gluonhq.attach.util", via = "com.gluonhq.attach:util:4.0.10"),
      // Kotlin
      @External(module = "kotlin.stdlib", via = "org.jetbrains.kotlin:kotlin-stdlib:1.4.30"),
    },
    lookupExternals = @Externals(name = Name.JAVAFX, version = "16")
    // </editor-fold>
    )
module bach.info {
  requires com.github.sormuras.bach;

  provides com.github.sormuras.bach.Bach.Provider with
      bach.info.Builder;
}
