package bach.info;

import com.github.sormuras.bach.Bach;
import com.github.sormuras.bach.Options;
import com.github.sormuras.bach.ProjectInfo;
import com.github.sormuras.bach.project.Libraries;
import com.github.sormuras.bach.project.Settings;

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
    return super.computeProjectLibraries(info, settings).with(new FXGLModuleLookup(this, "11.14"));
  }
}
