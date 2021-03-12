package bach.info;

import com.github.sormuras.bach.Bach;
import com.github.sormuras.bach.Command;
import com.github.sormuras.bach.Options;
import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.lookup.JavaFXModuleLookup;
import com.github.sormuras.bach.lookup.ModuleLookup;
import com.github.sormuras.bach.project.Libraries;
import com.github.sormuras.bach.project.Settings;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
  public Libraries computeProjectLibraries(ProjectInfo info, Settings settings) {
    var jacksonVersion = "2.12.1";
    var attachVersion = "4.0.10";
    return super.computeProjectLibraries(info, settings)
        .with(new JavaFXModuleLookup("16"))
        .with(new FXGLModuleLookup(this, "11.14"))
        .with(
            ModuleLookup.external("com.fasterxml.jackson.annotation")
                .via("com.fasterxml.jackson.core:jackson-annotations:" + jacksonVersion))
        .with(
            ModuleLookup.external("com.fasterxml.jackson.core")
                .via("com.fasterxml.jackson.core:jackson-core:" + jacksonVersion))
        .with(
            ModuleLookup.external("com.fasterxml.jackson.databind")
                .via("com.fasterxml.jackson.core:jackson-databind:" + jacksonVersion))
        .with(
            ModuleLookup.external("com.gluonhq.attach.audio")
                .via("com.gluonhq.attach:audio:" + attachVersion))
        .with(
            ModuleLookup.external("com.gluonhq.attach.lifecycle")
                .via("com.gluonhq.attach:lifecycle:" + attachVersion))
        .with(
            ModuleLookup.external("com.gluonhq.attach.storage")
                .via("com.gluonhq.attach:storage:" + attachVersion))
        .with(
            ModuleLookup.external("com.gluonhq.attach.util")
                .via("com.gluonhq.attach:util:" + attachVersion));
  }

  @Override
  public void buildProjectMainSpace() throws Exception {

    super.buildProjectMainSpace();

    var toccata = "com.github.sormuras.toccata";
    var expansion = "com.github.sormuras.toccata.expansion";
    var paths = List.of(folders().workspace("modules"), folders().externalModules());
    var image = deleteDirectories(folders().workspace("image"));
    run(
        Command.jlink()
            .add("--launcher", "toccata=" + toccata)
            .add("--add-modules", toccata)
            .add("--add-modules", expansion)
            .add("--module-path", paths)
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
              .add("--dest", folders().workspace("package")));
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
