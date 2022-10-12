package mangobomb.bombermango.powerup;

import com.almasb.fxgl.entity.component.Component;
import mangobomb.bombermango.Bomb;
import mangobomb.bombermango.BombermanType;
import mangobomb.bombermango.Player;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.inc;
import static mangobomb.bombermango.HelloApplication.player;

public class PowerupBomb extends Component {
    public PowerupBomb() {
        onCollisionBegin(BombermanType.POWERUP_BOMB, BombermanType.PLAYER,
                (po, pl) -> {
                    po.removeFromWorld();
                    pl.getComponent(Player.class).increaseMaxBombs();
                    inc("score",100);
                });
    }
}
