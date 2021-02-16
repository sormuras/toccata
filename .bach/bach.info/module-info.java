@com.github.sormuras.bach.ProjectInfo(
    name = "toccata",
    version = "1-ea",
    requires = "com.almasb.fxgl.all")
module bach.info {
  requires com.github.sormuras.bach;

  provides com.github.sormuras.bach.Bach.Provider with
      bach.info.Builder;
}
