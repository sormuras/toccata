package com.github.sormuras.toccata.expansion;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.github.sormuras.toccata.Toccata;
import com.github.sormuras.toccata.ToccataPlugin;
import javafx.scene.text.Text;

public class ExpansionToccataPlugin implements ToccataPlugin {
  @Override
  public void initGame(Toccata toccata) {
    var ball = "/com/github/sormuras/toccata/expansion/assets/textures/ball.png";
    FXGL.entityBuilder()
        .at(100, 100)
        .view(new Text("ExpansionEntity"))
        .view(new Texture(FXGL.getAssetLoader().loadImage(getClass().getResource(ball))))
        .buildAndAttach();
  }
}
