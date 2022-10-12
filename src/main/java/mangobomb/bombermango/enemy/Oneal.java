package mangobomb.bombermango.enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import mangobomb.bombermango.BombermanType;
import mangobomb.bombermango.Player;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;

import static com.almasb.fxgl.dsl.FXGL.onCollision;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static mangobomb.bombermango.HelloApplication.SCALED_SIZE;

public class Oneal extends Enemy {
    public double lastX = 0;
    public double lastY = 0;
    public CellMoveComponent cell;
    public AStarMoveComponent astar;
    public Oneal() {
        super();

        onCollisionBegin(BombermanType.ONEAL, BombermanType.BRICK,
                (o, br) -> o.getComponent(Oneal.class).turn());

        onCollisionBegin(BombermanType.ONEAL, BombermanType.WALL,
                (o, wa) -> o.getComponent(Oneal.class).turn());

        onCollision(BombermanType.ONEAL, BombermanType.PLAYER,
                (o, pl) -> pl.getComponent(Player.class).die());
    }

    @Override
    public void onUpdate(double tpf) {
        if (isPlayerDetected()) {
            astar.resume();
            detectedMove();
        } else {
            astar.pause();
            undetectedMove(tpf);
        }
    }

    protected void undetectedMove(double tpf) {
        double x = entity.getX() - lastX;
        double y = entity.getY() - lastX;
        lastX = entity.getX();
        lastY = entity.getY();
        if (x == 0 && y == 0) {
            turn();
        }
        entity.setScaleUniform(0.9);
        entity.translateX(x_speed * tpf);
        entity.translateY(y_speed * tpf);
    }

    private void detectedMove() {
        Entity player = FXGL.getGameWorld().getSingleton(BombermanType.PLAYER);
        int x = (int) (player.getX() / SCALED_SIZE);
        int y = (int) (player.getY() / SCALED_SIZE);
        astar.moveToCell(x, y);
    }
}
