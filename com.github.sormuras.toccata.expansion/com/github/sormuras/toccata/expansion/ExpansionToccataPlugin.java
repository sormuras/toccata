package com.github.sormuras.toccata.expansion;

import com.almasb.fxgl.dsl.FXGL;
import com.github.sormuras.toccata.Toccata;
import com.github.sormuras.toccata.ToccataPlugin;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ExpansionToccataPlugin implements ToccataPlugin {
  @Override
  public void initGame(Toccata toccata) {
    FXGL.entityBuilder()
        .at(100, 100)
        .view(new Text("ExpansionEntity"))
        .view(new Circle(25, 25, 25, Color.MAGENTA))
        .view("ball.png")
        .buildAndAttach();
  }
}
