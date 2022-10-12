package mangobomb.bombermango.enemy;

import mangobomb.bombermango.BombermanType;
import mangobomb.bombermango.Player;

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
