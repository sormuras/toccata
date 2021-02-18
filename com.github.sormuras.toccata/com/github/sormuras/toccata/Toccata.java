package com.github.sormuras.toccata;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Toccata extends GameApplication {

  public static void main(String... args) {
    launch(args);
  }

  @Override
  protected void initSettings(GameSettings settings) {}

  @Override
  protected void initInput() {
    FXGL.onKeyDown(KeyCode.F, () -> FXGL.getNotificationService().pushNotification("Hello world!"));
    FXGL.onKeyDown(KeyCode.DOWN, () -> FXGL.getNotificationService().pushNotification("Points -1!"));
    FXGL.onKeyDown(KeyCode.UP, () -> FXGL.getNotificationService().pushNotification("Points +1!"));
    FXGL.onKey(KeyCode.D, () -> {
      player.translateX(5); // move right 5 pixels
    });

    FXGL.onKey(KeyCode.A, () -> {
      player.translateX(-5); // move left 5 pixels
    });

    FXGL.onKey(KeyCode.W, () -> {
      player.translateY(-5); // move up 5 pixels
    });

    FXGL.onKey(KeyCode.S, () -> {
      player.translateY(5); // move down 5 pixels
    });
  }
  private Entity player;

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

    player = FXGL.entityBuilder()
            .at(300, 300)
            .view(new Rectangle(25, 25, Color.BLUE))
            .buildAndAttach();

  }
}
