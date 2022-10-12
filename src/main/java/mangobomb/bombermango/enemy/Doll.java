package mangobomb.bombermango.enemy;

import mangobomb.bombermango.BombermanType;
import mangobomb.bombermango.Player;

import static com.almasb.fxgl.dsl.FXGL.onCollision;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;

public class Doll extends Enemy {
    public Doll() {
        super();
        onCollisionBegin(BombermanType.DOLL, BombermanType.BOMB,
                (d, bo) -> d.getComponent(Doll.class).turn());

        onCollisionBegin(BombermanType.DOLL, BombermanType.BRICK,
                (d, br) -> d.getComponent(Doll.class).turn());

        onCollisionBegin(BombermanType.DOLL, BombermanType.WALL,
                (d, wa) -> d.getComponent(Doll.class).turn());

        onCollision(BombermanType.DOLL, BombermanType.PLAYER,
                (d, pl) -> pl.getComponent(Player.class).die());
    }

    @Override
    protected double getRandom() {
        return Math.random() > 0.5 ? ENEMY_SPEED*1.2 : -ENEMY_SPEED*1.2;
    }
}
