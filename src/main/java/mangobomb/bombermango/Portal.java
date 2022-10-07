package mangobomb.bombermango;


import com.almasb.fxgl.entity.component.Component;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;

public class Portal extends Component {

    public Portal() {
        onCollisionBegin(BombermanType.PORTAL, BombermanType.PLAYER,
                (po, pl) -> pl.getComponent(Player.class).pass_level = true);
    }

}
