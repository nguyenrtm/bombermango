package mangobomb.bombermango;


import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static mangobomb.bombermango.HelloApplication.player;

public class Portal extends Component {

    public Portal() {
        onCollisionBegin(BombermanType.PORTAL, BombermanType.PLAYER,
                (po, pl) -> player.getComponent(Player.class).checkPassLevel());
    }

}
