package com.github.sormuras.toccata;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;

public class ToccataEntityFactory implements EntityFactory {

  private static final String prefix = "/com/github/sormuras/toccata/assets/textures/";

  @Spawns("enemy")
  public Entity newEnemy(SpawnData data) {

    return FXGL.entityBuilder(data)
        .view(prefix + "rocket.png")
        .with(
            new ProjectileComponent(
                new Point2D(FXGL.random(-1d, 1d), FXGL.random(-1d, 1d)), FXGL.random(50, 200)))
        .build();
  }

  @Spawns("ally")
  public Entity newAlly(SpawnData data) {
    return FXGL.entityBuilder(data)
        .view(prefix + "points.png")
        .with(
            new ProjectileComponent(
                new Point2D(FXGL.random(-1d, 1d), FXGL.random(-1d, 1d)), FXGL.random(50, 200)))
        .build();
  }
}
