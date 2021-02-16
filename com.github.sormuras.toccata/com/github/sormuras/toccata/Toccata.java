package com.github.sormuras.toccata;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Toccata extends GameApplication {

  public static void main(String... args) {
    launch(args);
  }

  @Override
  protected void initSettings(GameSettings settings) {}

  @Override
  protected void initInput() {
    FXGL.onKeyDown(KeyCode.F, () -> FXGL.getNotificationService().pushNotification("Hello world"));
  }

  @Override
  protected void initGame() {
    FXGL.getGameWorld().addEntityFactory(new ToccataEntityFactory());

    var bounds = new Rectangle2D(0, 0, FXGL.getAppWidth(), FXGL.getAppHeight());
    FXGL.run(
        () -> {
          FXGL.spawn("ally", FXGLMath.randomPoint(bounds));
          FXGL.spawn("enemy", FXGLMath.randomPoint(bounds));
        },
        Duration.seconds(0.5));
  }
}
