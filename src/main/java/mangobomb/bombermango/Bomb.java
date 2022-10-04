package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class Bomb extends Component {
    public static int radius;

    public Bomb(int radius) {
        this.radius = radius;
    }

    public void explode() {
        BoundingBoxComponent box = entity.getBoundingBoxComponent();

        var list = getGameWorld()
                .getEntitiesInRange(box.range(radius, radius));
        System.out.println(list.size());
        list.stream()
                .filter(e -> e.isType(BombermanType.BRICK))
                .forEach(e -> {
                    FXGL.<HelloApplication>getAppCast().brickDestroyed(e);
                    e.removeFromWorld();
                });
        entity.removeFromWorld();
    }

}
