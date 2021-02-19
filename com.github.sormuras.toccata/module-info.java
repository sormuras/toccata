module com.github.sormuras.toccata {
  requires com.almasb.fxgl.all;
  requires jdk.crypto.ec; // https://github.com/AlmasB/FXGL/issues/910

  exports com.github.sormuras.toccata to
      com.almasb.fxgl.core;

  opens com.github.sormuras.toccata.assets.textures to
      com.almasb.fxgl.all;
}
