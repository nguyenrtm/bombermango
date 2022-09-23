package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.AnimationChannel;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_GREENPeer;
import javafx.collections.WeakListChangeListener;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.List;
import com.almasb.fxgl.texture.AnimatedTexture;
import static com.almasb.fxgl.dsl.FXGL.*;
import mangobomb.bombermango.BombermanType;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static mangobomb.bombermango.BombermanType.*;

public class GenerateFactory implements EntityFactory {

    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/player_left.png"),
                image("sprites/player_left_1.png"),
                image("sprites/player_left_2.png")
        ), Duration.seconds(0.5));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("EnemyBalloom")
    public Entity newEnemyBalloom(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/balloom_left1.png"),
                image("sprites/balloom_left2.png"),
                image("sprites/balloom_left3.png")
        ), Duration.seconds(0.5));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("EnemyMinvo")
    public Entity newEnemyMinvo(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/minvo_left1.png"),
                image("sprites/minvo_left2.png"),
                image("sprites/minvo_left3.png")
        ), Duration.seconds(0.5));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("PowerupPass")
    public Entity newPowerupPass(SpawnData data) {
        return entityBuilder()
                .view("sprites/powerup_bombpass.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }
    @Spawns("PowerupBombs")
    public Entity newPowerupBombs(SpawnData data) {
        return entityBuilder()
                .view("sprites/powerup_bombs.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }

    @Spawns("PowerupDetonator")
    public Entity newPowerupDetonator(SpawnData data) {
        return entityBuilder()
                .view("sprites/powerup_detonator.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }

    @Spawns("PowerupFlamepass")
    public Entity newPowerupFlamepass(SpawnData data) {
        return entityBuilder()
                .view("sprites/powerup_flamepass.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }

    @Spawns("PowerupSpeed")
    public Entity newPowerupSpeed(SpawnData data) {
        return entityBuilder()
                .view("sprites/powerup_speed.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }

    @Spawns("Portal")
    public Entity newPortal(SpawnData data) {
        return entityBuilder()
                .view("sprites/portal.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(POWERUP)
                .build();
    }

    @Spawns("Brick")
    public Entity newBrick(@NotNull SpawnData data) {
        return entityBuilder()
                .view("sprites/brick.png")
                .at(data.getX() + 48, data.getY() + 48)
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(BRICK)
                .build();
    }

    @Spawns("Wall")
    public Entity newWall(@NotNull SpawnData data) {
        return entityBuilder()
                .view("sprites/wall.png")
                .at(data.getX(), data.getY())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .type(WALL)
                .build();
    }

    @Spawns("BG")
    public Entity newBG(SpawnData data) {
        return entityBuilder()
                .view(new Rectangle(HelloApplication.SCREEN_WIDTH, HelloApplication.SCREEN_HEIGHT - HelloApplication.SCALED_SIZE*2, Color.GREEN))
                .at(data.getX(), data.getY())
                .build();
    }

    @Spawns("GreyBG")
    public Entity newGreyBG(SpawnData data) {
        return entityBuilder()
                .view(new Rectangle(HelloApplication.SCREEN_WIDTH, HelloApplication.SCALED_SIZE*2, Color.GREY))
                .at(data.getX(), data.getY())
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
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("BombExploded")
    public Entity newBombExploded(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/bomb_exploded.png"),
                image("sprites/bomb_exploded1.png"),
                image("sprites/bomb_exploded2.png"),
                image("sprites/bomb_exploded1.png"),
                image("sprites/bomb_exploded.png")
        ), Duration.seconds(0.8));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("HorizontalLeft")
    public Entity newHorizontalLeft(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/explosion_horizontal_left_last.png"),
                image("sprites/explosion_horizontal_left_last1.png"),
                image("sprites/explosion_horizontal_left_last2.png"),
                image("sprites/explosion_horizontal_left_last1.png"),
                image("sprites/explosion_horizontal_left_last.png")
        ), Duration.seconds(0.8));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("HorizontalRight")
    public Entity newHorizontalRight(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/explosion_horizontal_right_last.png"),
                image("sprites/explosion_horizontal_right_last1.png"),
                image("sprites/explosion_horizontal_right_last2.png"),
                image("sprites/explosion_horizontal_right_last1.png"),
                image("sprites/explosion_horizontal_right_last.png")
        ), Duration.seconds(0.8));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("VerticalTop")
    public Entity newVerticalTop(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/explosion_vertical_top_last.png"),
                image("sprites/explosion_vertical_top_last1.png"),
                image("sprites/explosion_vertical_top_last2.png"),
                image("sprites/explosion_vertical_top_last1.png"),
                image("sprites/explosion_vertical_top_last.png")
        ), Duration.seconds(0.8));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

    @Spawns("VerticalDown")
    public Entity newVerticalDown(SpawnData data) {
        var channel = new AnimationChannel(List.of(
                image("sprites/explosion_vertical_down_last.png"),
                image("sprites/explosion_vertical_down_last1.png"),
                image("sprites/explosion_vertical_down_last2.png"),
                image("sprites/explosion_vertical_down_last1.png"),
                image("sprites/explosion_vertical_down_last.png")
        ), Duration.seconds(0.8));

        return entityBuilder()
                .at(data.getX(), data.getY())
                .view(new AnimatedTexture(channel).loop())
                .scale(HelloApplication.ZOOM_RATIO, HelloApplication.ZOOM_RATIO)
                .buildAndAttach();
    }

}
