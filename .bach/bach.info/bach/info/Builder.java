package bach.info;

import com.github.sormuras.bach.*;
import com.github.sormuras.bach.lookup.GitHubReleasesModuleLookup;
import com.github.sormuras.bach.lookup.JavaFXModuleLookup;
import com.github.sormuras.bach.lookup.ToolProvidersModuleLookup;
import java.nio.file.Files;

public class Builder extends Bach {
  public static void main(String... args) {
    Bach.main(args);
  }

  public static Provider<Builder> provider() {
    return Builder::new;
  }

  private Builder(Options options) {
    super(options);
  }

  @Override
  public Libraries computeProjectLibraries(ProjectInfo info) {
    return super.computeProjectLibraries(info)
        .withModuleLookup(new JavaFXModuleLookup("16-ea+6"))
        .withModuleLookup(new GitHubReleasesModuleLookup(this))
        .withModuleLookup(new ToolProvidersModuleLookup(this, base().externals()));
  }

  @Override
  public void buildProjectMainSpace() throws Exception {
    var module = "com.github.sormuras.toccata";
    var destination = base().workspace("classes");
    var modules = base().workspace("modules");

    run(
        Command.javac()
            .add("--module", module)
            .add("--module-version", "0")
            .add("--module-source-path", ".")
            .add("--module-path", Bach.EXTERNALS)
            .add("-encoding", "UTF-8")
            .add("-g")
            .add("-parameters")
            .add("-Xlint")
            .add("-d", destination));

    Files.createDirectories(modules);
    var file = modules.resolve(computeMainJarFileName(module));
    run(
        Command.jar()
            .add("--verbose")
            .add("--create")
            .add("--file", file)
            .add("--main-class", module + ".Toccata")
            .add("-C", destination.resolve(module), "."));

    run(
        Command.jlink()
            .add("--launcher", "toccata=" + module)
            .add("--add-modules", module)
            .add("--module-path", ".bach/workspace/modules;.bach/external-modules")
            .add("--output", ".bach/workspace/image"));
  }
}
