package bach.info;

import com.github.sormuras.bach.Bach;
import com.github.sormuras.bach.Command;
import com.github.sormuras.bach.Libraries;
import com.github.sormuras.bach.Options;
import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.lookup.JavaFXModuleLookup;
import java.io.File;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    var jacksonVersion = "2.12.1";
    var attachVersion = "4.0.10";
    return super.computeProjectLibraries(info)
        .withModuleLookup(new JavaFXModuleLookup("16-ea+7"))
        .withModuleLookup(new FXGLModuleLookup(this, "dev-SNAPSHOT"))
        .withModuleLookup(
            Libraries.lookup("com.fasterxml.jackson.annotation")
                .via("com.fasterxml.jackson.core:jackson-annotations:" + jacksonVersion))
        .withModuleLookup(
            Libraries.lookup("com.fasterxml.jackson.core")
                .via("com.fasterxml.jackson.core:jackson-core:" + jacksonVersion))
        .withModuleLookup(
            Libraries.lookup("com.fasterxml.jackson.databind")
                .via("com.fasterxml.jackson.core:jackson-databind:" + jacksonVersion))
        .withModuleLookup(
            Libraries.lookup("com.gluonhq.attach.audio")
                .via("com.gluonhq.attach:audio:" + attachVersion))
        .withModuleLookup(
            Libraries.lookup("com.gluonhq.attach.lifecycle")
                .via("com.gluonhq.attach:lifecycle:" + attachVersion))
        .withModuleLookup(
            Libraries.lookup("com.gluonhq.attach.storage")
                .via("com.gluonhq.attach:storage:" + attachVersion))
        .withModuleLookup(
            Libraries.lookup("com.gluonhq.attach.util")
                .via("com.gluonhq.attach:util:" + attachVersion));
  }

  @Override
  public void buildProjectMainSpace() throws Exception {
    var toccata = "com.github.sormuras.toccata";
    var expansion = "com.github.sormuras.toccata.expansion";
    var destination = base().workspace("classes");
    var modules = base().workspace("modules");

    run(
        Command.javac()
            .add("--module", toccata + "," + expansion)
            .add("--module-version", "0")
            .add("--module-source-path", ".")
            .add("--module-path", Bach.EXTERNALS)
            .add("-encoding", "UTF-8")
            .add("-g")
            .add("-parameters")
            .add("-Xlint")
            .add("-d", destination));

    Files.createDirectories(modules);
    run(
        Command.jar()
            .add("--verbose")
            .add("--create")
            .add("--file", modules.resolve(computeMainJarFileName(toccata)))
            .add("--main-class", toccata + ".Toccata")
            .add("-C", destination.resolve(toccata), ".")
            .add("-C", toccata, ".") // assets and sources
        );
    run(
        Command.jar()
            .add("--verbose")
            .add("--create")
            .add("--file", modules.resolve(computeMainJarFileName(expansion)))
            .add("-C", destination.resolve(expansion), ".")
            .add("-C", expansion, ".") // assets and sources
        );

    var path = String.join(File.pathSeparator, modules.toString(), base().externals().toString());
    var image = deleteDirectories(base().workspace("image"));
    run(
        Command.jlink()
            .add("--launcher", "toccata=" + toccata)
            .add("--add-modules", toccata)
            .add("--add-modules", expansion)
            .add("--module-path", path)
            .add("--output", image));

    if (Boolean.getBoolean("jpackage"))
      run(
          Command.of("jpackage")
              .add("--verbose")
              .add("--name", "Toccata")
              .add("--description", "Tower Crush Cannon Tavern")
              .add("--vendor", "Christian Stein")
              .add("--runtime-image", image)
              .add("--module", toccata)
              .add("--dest", base().workspace("package")));
  }

  static Path deleteDirectories(Path directory) {
    try {
      Files.deleteIfExists(directory);
      return directory;
    } catch (DirectoryNotEmptyException ignored) {
      // fall through
    } catch (Exception e) {
      throw new RuntimeException("Delete directories failed: " + directory, e);
    }
    try (var stream = Files.walk(directory)) {
      var selected = stream.sorted((p, q) -> -p.compareTo(q));
      for (var path : selected.toArray(Path[]::new)) Files.deleteIfExists(path);
    } catch (Exception e) {
      throw new RuntimeException("Delete directories failed: " + directory, e);
    }
    return directory;
  }
}
