package com.github.sormuras.toccata;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import java.net.URL;
import javafx.geometry.Point2D;

public class ToccataEntityFactory implements EntityFactory {

  @Spawns("enemy")
  public Entity newEnemy(SpawnData data) {
    var rocket = url("textures/rocket.png");
    return FXGL.entityBuilder(data)
        .view(FXGL.getAssetLoader().loadTexture(rocket))
        .with(
            new ProjectileComponent(
                new Point2D(FXGL.random(-1d, 1d), FXGL.random(-1d, 1d)), FXGL.random(50, 200)))
        .build();
  }

  @Spawns("ally")
  public Entity newAlly(SpawnData data) {
    var points = url("textures/points.png");
    return FXGL.entityBuilder(data)
        .view(FXGL.getAssetLoader().loadTexture(points))
        .with(
            new ProjectileComponent(
                new Point2D(FXGL.random(-1d, 1d), FXGL.random(-1d, 1d)), FXGL.random(50, 200)))
        .build();
  }

  String getUrlPrefixForAssets() {
    return '/' + getClass().getModule().getName().replace('.', '/') + "/assets/";
  }

  URL url(String name) {
    return getClass().getResource(getUrlPrefixForAssets() + name);
  }
}
