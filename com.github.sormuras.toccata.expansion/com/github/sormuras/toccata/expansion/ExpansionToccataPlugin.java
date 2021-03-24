package com.github.sormuras.toccata.expansion;

import com.almasb.fxgl.dsl.FXGL;
import com.github.sormuras.toccata.Main;
import com.github.sormuras.toccata.ToccataPlugin;
import javafx.scene.text.Text;

public class ExpansionToccataPlugin implements ToccataPlugin {
  @Override
  public void initGame(Main toccata) {
    var ball = url("textures/ball.png");
    FXGL.entityBuilder()
        .at(100, 100)
        .view(new Text("ExpansionEntity"))
        .view(FXGL.getAssetLoader().loadTexture(ball))
        .buildAndAttach();
  }
}
