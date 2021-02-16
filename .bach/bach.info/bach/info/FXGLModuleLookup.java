package bach.info;

import com.github.sormuras.bach.lookup.Maven;
import com.github.sormuras.bach.lookup.ModuleLookup;
import java.util.Optional;

public record FXGLModuleLookup(String version) implements ModuleLookup {

  private static final String MODULE_PREFIX = "com.almasb.fxgl";
  private static final String MAVEN_GROUP = "com.github.almasb";

  @Override
  public Optional<String> lookupUri(String module) {
    if (!module.startsWith(MODULE_PREFIX)) return Optional.empty();
    if (module.equals("com.almasb.fxgl.all")) return via("fxgl");
    return via("fxgl-" + module.substring(MODULE_PREFIX.length() + 1));
  }

  private Optional<String> via(String artifact) {
    return Optional.of(Maven.central(MAVEN_GROUP, artifact, version));
  }
}
