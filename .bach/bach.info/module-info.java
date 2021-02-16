import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.ProjectInfo.External;

@ProjectInfo(
    name = "toccata",
    version = "1-ea",
    requires = {"com.almasb.fxgl.all", "jdk.crypto.ec"},
    lookup = @External(module = "kotlin.stdlib", via = "org.jetbrains.kotlin:kotlin-stdlib:1.4.30"))
module bach.info {
  requires com.github.sormuras.bach;

  provides com.github.sormuras.bach.Bach.Provider with
      bach.info.Builder;
}
