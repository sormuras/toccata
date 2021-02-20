module com.github.sormuras.toccata.expansion {
  requires com.almasb.fxgl.all;
  requires com.github.sormuras.toccata;

  opens com.github.sormuras.toccata.expansion.assets.textures to
      com.almasb.fxgl.all,
      com.github.sormuras.toccata;

  provides com.github.sormuras.toccata.ToccataPlugin with
      com.github.sormuras.toccata.expansion.ExpansionToccataPlugin;
}
