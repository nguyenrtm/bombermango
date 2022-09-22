package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.collections.WeakListChangeListener;
import javafx.geometry.Point2D;
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
                .at(300, 100)
                .view(new AnimatedTexture(channel).loop())
                .scale(2,2)
                .build();
    }

    @Spawns("Wall")
    public Entity newWall(SpawnData data) {
        return entityBuilder()
                .at(200, 200)
                .view("sprites/wall.png")
                .type(WALL)
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
                .at(300, 300)
                .view(new AnimatedTexture(channel).loop())
                .scale(2,2)
                .buildAndAttach();
    }

}
