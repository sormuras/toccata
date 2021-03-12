import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.External;
import com.github.sormuras.bach.ProjectInfo.Tweak;
import com.github.sormuras.bach.project.JavaStyle;

@ProjectInfo(
    name = "toccata",
    version = "1-ea",
    format = JavaStyle.GOOGLE,
    requires = {"com.almasb.fxgl.all", "jdk.crypto.ec"},
    lookupExternal =
        @External(module = "kotlin.stdlib", via = "org.jetbrains.kotlin:kotlin-stdlib:1.4.30"),
    tweaks = {
      @Tweak(
          tool = "jar(com.github.sormuras.toccata)",
          option = "-C",
          value = {"com.github.sormuras.toccata", "."}),
      @Tweak(
          tool = "jar(com.github.sormuras.toccata.expansion)",
          option = "-C",
          value = {"com.github.sormuras.toccata.expansion", "."}),
    })
module bach.info {
  requires com.github.sormuras.bach;

  provides com.github.sormuras.bach.Bach.Provider with
      bach.info.Builder;
}
