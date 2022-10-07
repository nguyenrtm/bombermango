package mangobomb.bombermango;

import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Entity;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

public class Player extends Component {
    public CellMoveComponent cell;
    public AStarMoveComponent astar;
    public static int life = 1;

    private int maxBombs = 1;
    private int bombsPlaced = 0;
    public static boolean pass_level = false;

    public void increaseMaxBombs() {
        maxBombs++;
    }

    public void placeBomb() {
        if (bombsPlaced == maxBombs) {
            return;
        }

        bombsPlaced++;

        Entity bomb = spawn("Bomb", new SpawnData(cell.getCellX() * 48, cell.getCellY() * 48).put("radius", HelloApplication.SCALED_SIZE / 2));

        getGameTimer().runOnceAfter(() -> {
            bomb.getComponent(Bomb.class).explode();
            bombsPlaced--;
        }, Duration.seconds(2));
    }

    public void moveRight() {
        astar.moveToRightCell();
    }

    public void moveLeft() {
        astar.moveToLeftCell();
    }

    public void moveUp() {
        astar.moveToUpCell();
    }

    public void moveDown() {
        astar.moveToDownCell();
    }

    public void die() {
        life--;
        System.out.println(life);
    }
}
