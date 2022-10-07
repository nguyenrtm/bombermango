package mangobomb.bombermango;

import com.almasb.fxgl.texture.AnimationChannel;
import mangobomb.bombermango.BombermanType;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

public class Balloom extends Enemy {
    public Balloom() {
        super();

        onCollisionBegin(BombermanType.BALLOOM, BombermanType.BOMB,
                (b, bo) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(BombermanType.BALLOOM, BombermanType.BRICK,
                (b, br) -> b.getComponent(Balloom.class).turn());

        onCollisionBegin(BombermanType.BALLOOM, BombermanType.WALL,
                (b, wa) -> b.getComponent(Balloom.class).turn());

        onCollision(BombermanType.BALLOOM, BombermanType.PLAYER,
                (b, pl) -> pl.getComponent(Player.class).die());

    }

}
