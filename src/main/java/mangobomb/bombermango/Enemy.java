package mangobomb.bombermango;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;

import java.awt.*;
import java.util.List;

public class Enemy extends Component {
    public static int ENEMY_SPEED = 80;
    public int detect = 100;

    public double x_speed = ENEMY_SPEED;
    public double y_speed = 0;

    private enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

    public void setTurn(Direction turn) {
        switch(turn) {
            case LEFT -> {
                entity.translateX(3);
                x_speed = ENEMY_SPEED;
            }
            case RIGHT -> {
                entity.translateX(-3);
                x_speed = -ENEMY_SPEED;
            }
            case UP -> {
                entity.translateY(3);
                y_speed = 0;
            }
            case DOWN -> {
                entity.translateY(-3);
                y_speed = 0;
            }
        }
    }

    public void onUpdate(double tpf) {
        entity.setScaleUniform(0.99);
        entity.translateX(x_speed * tpf);
        entity.translateY(y_speed * tpf);

    }

    public void turn() {
        if (x_speed < 0) setTurn(Direction.LEFT);
        else if (x_speed > 0) setTurn(Direction.RIGHT);
        else if (y_speed < 0) setTurn(Direction.UP);
        else if (y_speed > 0) setTurn(Direction.DOWN);
    }

    public boolean isPlayerDetected() {
        BoundingBoxComponent box = entity.getBoundingBoxComponent();
        List<Entity> list = FXGL.getGameWorld().getEntitiesInRange(box.range(detect, detect));
        for (Entity entity : list) {
            if (entity.isType(BombermanType.BOMB)) {
                return false;
            }
        }

        for (Entity entity : list) {
            if (entity.isType(BombermanType.PLAYER)) {
                return true;
            }
        }

        return false;
    }

}
