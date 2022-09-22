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

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;
import static mangobomb.bombermango.BombermanType.WALL;

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

    @Spawns("Wall")
    public Entity newWall(SpawnData data) {
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
                .view(new Rectangle(HelloApplication.SCREEN_WIDTH, HelloApplication.SCREEN_HEIGHT, Color.GREEN))
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

}
