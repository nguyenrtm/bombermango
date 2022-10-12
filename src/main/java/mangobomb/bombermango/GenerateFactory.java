package mangobomb.bombermango;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import com.almasb.fxgl.texture.AnimatedTexture;
import mangobomb.bombermango.enemy.Balloom;
import mangobomb.bombermango.enemy.Doll;
import mangobomb.bombermango.enemy.Oneal;
import mangobomb.bombermango.powerup.PowerupBomb;
import mangobomb.bombermango.powerup.PowerupFlame;

import static com.almasb.fxgl.dsl.FXGL.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static mangobomb.bombermango.BombermanType.*;
import static mangobomb.bombermango.HelloApplication.SCALED_SIZE;
import static mangobomb.bombermango.enemy.Enemy.ENEMY_SPEED;

public class GenerateFactory implements EntityFactory {

    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {

        FXGL.<HelloApplication>getAppCast();
        return entityBuilder(data)
                .atAnchored(new Point2D(24, 24), new Point2D(72, 168))
                .type(PLAYER)
                .zIndex(10)
                .bbox(new HitBox(BoundingShape.circle(22)))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(SCALED_SIZE, SCALED_SIZE,Player.PLAYER_SPEED))
                .with(new AStarMoveComponent(FXGL.<HelloApplication>getAppCast().getGrid()))
                .with(new Player())
                .build();
    }

    @Spawns("EnemyBalloom,b")
    public Entity newEnemyBalloom(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/balloom_right1.png"),
                image("sprites/balloom_right2.png"),
                image("sprites/balloom_right3.png")
        ), Duration.seconds(0.5));

        return entityBuilder(data)
                .type(BALLOOM)
                .with(new Balloom())
                .bbox(new HitBox(BoundingShape.circle(22)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .view(new AnimatedTexture(channel).loop())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("EnemyDoll,d")
    public Entity newEnemyDoll(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/doll_right1.png"),
                image("sprites/doll_right2.png"),
                image("sprites/doll_right3.png")
        ), Duration.seconds(0.5));

        return entityBuilder(data)
                .type(DOLL)
                .with(new Doll())
                .bbox(new HitBox(BoundingShape.circle(23)))
                .atAnchored(new Point2D(0, 0), new Point2D(data.getX(), data.getY()))
                .view(new AnimatedTexture(channel).loop())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("EnemyOneal,o")
    public Entity newEnemyOneal(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/oneal_right1.png"),
                image("sprites/oneal_right2.png"),
                image("sprites/oneal_right3.png")
        ), Duration.seconds(0.5));

        FXGL.<HelloApplication>getAppCast();
        return entityBuilder(data)
                .type(ONEAL)
                .bbox(new HitBox(BoundingShape.circle(22)))
                .atAnchored(new Point2D(24, 24), new Point2D(data.getX()+24, data.getY()+24))
                .view(new AnimatedTexture(channel).loop())
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(SCALED_SIZE, SCALED_SIZE, ENEMY_SPEED))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new Oneal())
                .build();
    }

    @Spawns("PowerupBomb,x")
    public Entity newPowerupBomb(SpawnData data) {
        return entityBuilder(data)
                .view("sprites/powerup_bombs.png")
                .at(data.getX(), data.getY())
                .bbox(new HitBox(new Point2D(24, 24), BoundingShape.box(1, 1)))
                .with(new PowerupBomb())
                .type(POWERUP_BOMB)
                .with(new CollidableComponent(true))
                .zIndex(-5)
                .build();
    }

    @Spawns("PowerupFlame,y")
    public Entity newPowerupFlame(SpawnData data) {
        return entityBuilder(data)
                .view("sprites/powerup_flames.png")
                .at(data.getX(), data.getY())
                .bbox(new HitBox(new Point2D(24, 24), BoundingShape.box(1, 1)))
                .with(new PowerupFlame())
                .type(POWERUP_FLAME)
                .with(new CollidableComponent(true))
                .zIndex(-5)
                .build();
    }


    @Spawns("Portal,p")
    public Entity newPortal(SpawnData data) {
        return entityBuilder(data)
                .view("sprites/portal.png")
                .bbox(new HitBox(new Point2D(24, 24), BoundingShape.box(1, 1)))
                .at(data.getX(), data.getY())
                .with(new CollidableComponent(true))
                .zIndex(-5)
                .type(PORTAL)
                .with(new Portal())
                .build();
    }

    @Spawns("Brick,0")
    public Entity newBrick(SpawnData data) {
        return entityBuilder(data)
                .viewWithBBox(texture("sprites/brick.png", 48, 48))
                .with(new CollidableComponent(true))
                .zIndex(9)
                .type(BRICK)
                .build();
    }

    @Spawns("Wall,1")
    public Entity newWall(SpawnData data) {
        return entityBuilder(data)
                .viewWithBBox(texture("sprites/wall.png", 48, 48))
                .with(new CollidableComponent(true))
                .type(WALL)
                .build();
    }
    @Spawns("GreyBG,2")
    public Entity newGreyBG(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(SCALED_SIZE, SCALED_SIZE, Color.GREY))
                .type(BG)
                .build();
    }

    @Spawns("BG")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .at(0, 0)
                .view(new Rectangle(HelloApplication.SCREEN_WIDTH, HelloApplication.SCREEN_HEIGHT, Color.LIGHTGREEN))
                .zIndex(-10)
                .build();
    }

    @Spawns("Bomb")
    public Entity newBomb(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/bomb.png"),
                image("sprites/bomb_1.png"),
                image("sprites/bomb_2.png")
        ), Duration.seconds(0.5));

        return entityBuilder()
                .type(BOMB)
                .at(data.getX(), data.getY())
                .with(new Bomb(data.get("radius")))
                .bbox(new HitBox(BoundingShape.circle(22)))
                .with(new CollidableComponent(true))
                .view(new AnimatedTexture(channel).loop())
                .build();
    }

    @Spawns("BombExploded")
    public Entity newBombExploded(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/bomb_exploded.png"),
                image("sprites/bomb_exploded1.png"),
                image("sprites/bomb_exploded2.png")
        ), Duration.seconds(0.6));

        return entityBuilder(data)
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .with(new ExpireCleanComponent(Duration.seconds(0.6)))
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .build();
    }

    @Spawns("BalloomExploded")
    public Entity newBalloomExploded(SpawnData data) {
        return entityBuilder(data)
                .at(data.getX(), data.getY())
                .view(texture("sprites/balloom_dead.png"))
                .with(new ExpireCleanComponent(Duration.seconds(0.6)))
                .scale(2.8, 2.8)
                .build();
    }

    @Spawns("OnealExploded")
    public Entity newOnealExploded(SpawnData data) {
        return entityBuilder(data)
                .at(data.getX(), data.getY())
                .view(texture("sprites/oneal_dead.png"))
                .with(new ExpireCleanComponent(Duration.seconds(0.6)))
                .scale(2.8, 2.8)
                .build();
    }
    @Spawns("DollExploded")
    public Entity newDollExploded(SpawnData data) {
        return entityBuilder(data)
                .at(data.getX(), data.getY())
                .view(texture("sprites/doll_dead.png"))
                .with(new ExpireCleanComponent(Duration.seconds(0.6)))
                .scale(2.8, 2.8)
                .build();
    }

}
