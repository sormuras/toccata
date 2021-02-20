package com.github.sormuras.toccata.expansion;

import com.almasb.fxgl.dsl.FXGL;
import com.github.sormuras.toccata.Toccata;
import com.github.sormuras.toccata.ToccataPlugin;
import javafx.scene.text.Text;

public class ExpansionToccataPlugin implements ToccataPlugin {
  @Override
  public void initGame(Toccata toccata) {
    var ball = url("textures/ball.png");
    FXGL.entityBuilder()
        .at(100, 100)
        .view(new Text("ExpansionEntity"))
        .view(FXGL.getAssetLoader().loadTexture(ball))
        .buildAndAttach();
  }
}
