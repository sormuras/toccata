package com.github.sormuras.toccata;

import java.net.URL;

public interface ToccataPlugin {

  void initGame(Toccata toccata);

  default String getUrlPrefixForAssets() {
    return '/' + getClass().getModule().getName().replace('.', '/') + "/assets/";
  }

  default URL url(String name) {
    return getClass().getResource(getUrlPrefixForAssets() + name);
  }
}
