package mangobomb.bombermango.enemy;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import mangobomb.bombermango.BombermanType;

import java.awt.*;
import java.util.List;

public class Enemy extends Component {
    public static int ENEMY_SPEED = 80;
    public int detect = 200;

    public double x_speed = ENEMY_SPEED;
    public double y_speed = 0;

    private enum Direction {
        UP, LEFT, DOWN, RIGHT
    }

    public void setTurn(Direction turn) {
        switch(turn) {
            case LEFT -> {
                entity.translateX(5);
                x_speed = 0;
                y_speed = getRandom();
            }
            case RIGHT -> {
                entity.translateX(-5);
                x_speed = 0;
                y_speed = getRandom();
            }
            case UP -> {
                entity.translateY(5);
                y_speed = 0;
                x_speed = getRandom();
            }
            case DOWN -> {
                entity.translateY(-5);
                y_speed = 0;
                x_speed = getRandom();
            }
        }
    }

    protected double getRandom() {
        return Math.random() > 0.5 ? ENEMY_SPEED : -ENEMY_SPEED;
    }

    public void onUpdate(double tpf) {
        entity.setScaleUniform(0.9);
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
            if (entity.isType(BombermanType.PLAYER)) {
                return true;
            }
        }

        return false;
    }

}
