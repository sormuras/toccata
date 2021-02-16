package com.github.sormuras.toccata;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ToccataEntityFactory implements EntityFactory {

  @Spawns("enemy")
  public Entity newEnemy(SpawnData data) {
    return FXGL.entityBuilder(data)
        .view("stone.png")
        .with(new ProjectileComponent(new Point2D(1, 0), FXGL.random(1, 300)))
        .build();
  }

  @Spawns("ally")
  public Entity newAlly(SpawnData data) {
    return FXGL.entityBuilder(data)
        .view(new Rectangle(40, 40, Color.GREEN))
        .with(
            new ProjectileComponent(
                new Point2D(FXGL.random(-1d, 1d), FXGL.random(-1d, 1d)), FXGL.random(50, 200)))
        .build();
  }
}
