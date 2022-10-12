package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static mangobomb.bombermango.HelloApplication.player_score;

public class Bomb extends Component {
    public static int radius;

    public Bomb(int radius) {
        this.radius = radius;
    }

    public void explode() {

        spawn("BombExploded", entity.getX(), entity.getY());

        BoundingBoxComponent box = entity.getBoundingBoxComponent();

        getGameWorld()
                .getEntitiesInRange(box.range(radius, radius))
                .stream()
                .filter(e -> e.isType(BombermanType.BALLOOM) || e.isType(BombermanType.ONEAL)
                        || e.isType(BombermanType.BRICK) || e.isType(BombermanType.PLAYER)
                || e.isType(BombermanType.DOLL))
                .forEach(e -> {
                    FXGL.<HelloApplication>getAppCast().brickDestroyed(e);
                    if (e.isType(BombermanType.BALLOOM)) {
                        FXGL.<HelloApplication>getAppCast().balloomDestroyed(e);
                        player_score += 100;
                        spawn("BalloomExploded", e.getX(), e.getY());
                        inc("score", 100);
                    }
                    if (e.isType(BombermanType.DOLL)) {
                        FXGL.<HelloApplication>getAppCast().dollDestroyed(e);
                        player_score += 150;
                        spawn("DollExploded", e.getX(), e.getY());
                        inc("score",150);
                    }
                    if (e.isType(BombermanType.ONEAL)) {
                        FXGL.<HelloApplication>getAppCast().onealDestroyed(e);
                        player_score += 200;
                        spawn("OnealExploded", e.getX(), e.getY());
                        inc("score", 200);
                    }
                    if (e.isType(BombermanType.PLAYER)) {
                        e.getComponent(Player.class).die();
                    }
                    e.removeFromWorld();
                });
        entity.removeFromWorld();
    }

}
