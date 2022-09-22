package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.List;
import com.almasb.fxgl.texture.AnimatedTexture;
import static com.almasb.fxgl.dsl.FXGL.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class GenerateFactory implements EntityFactory {
    @Spawns("Rocket")
    public Entity newRocket(SpawnData data){
        return FXGL.entityBuilder(data)
                .at(100,100)
                .view(new Rectangle(100,100))
                .with(new ProjectileComponent(new Point2D(1,0), 100))
                .build();
    }

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
                .scale(5,5)
                .build();
    }

}
