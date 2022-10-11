package mangobomb.bombermango;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.awt.*;
import java.util.List;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGL.*;
import static mangobomb.bombermango.BombermanType.*;

public class HelloApplication extends GameApplication {
    public static final int WIDTH = 21;
    public static final int HEIGHT = 13;
    public static final int SCALED_SIZE = 48;
    public static final int SCREEN_WIDTH = SCALED_SIZE * WIDTH;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * HEIGHT;
    public static final int ZOOM_RATIO = 3;
    public static final int DEFAULT_SIZE = 16;
    public static int level_number = 1;
    public static int max_level = 3;
    public static int balloom_number;

    public static Entity player;
    public static Entity portal_img;
    public static Entity balloom_ent;
    public static Player playerComponent;
    public static Portal portal;
    public static Balloom balloom;
    public static GameWorld gameworld;
    private static AStarGrid grid;
    public static AStarGrid getGrid() {
        return grid;
    }

    @Override
    public void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Bomberman");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);

    }

    @Override
    protected void initGame() {
        gameworld = getGameWorld();
        gameworld.addEntityFactory(new GenerateFactory());

        loadNewLevel();
    }

    public static void setLevel(int level_number) {
        Level level = getAssetLoader().loadLevel("level" + level_number + ".txt", new TextLevelLoader(48, 48, ' '));
        gameworld.setLevel(level);

        spawn("BG");

        grid = AStarGrid.fromWorld(gameworld, 21, 13, 48, 48, type -> {
            if (type.equals(WALL) || type.equals(BRICK) || type.equals(BG))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });

        player = spawn("Player");
        playerComponent = player.getComponent(Player.class);

        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(player, 200, 100);
        viewport.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        if (level_number == 1) {
            portal_img = spawn("Portal", 18 * SCALED_SIZE, 7 * SCALED_SIZE);
            portal = portal_img.getComponent(Portal.class);

            balloom_ent = spawn("EnemyBalloom", 288,144);
            balloom_ent = spawn("EnemyBalloom", 48, 336);
            balloom_ent = spawn("EnemyBalloom", 8*SCALED_SIZE, 11*SCALED_SIZE);
            balloom_number = 3;
        } else if (level_number == 2) {
            portal_img = spawn("Portal", 19 * SCALED_SIZE, 7 * SCALED_SIZE);
            portal = portal_img.getComponent(Portal.class);
        } else if (level_number == 3) {
            portal_img = spawn("Portal", 19 * SCALED_SIZE, 3 * SCALED_SIZE);
            portal = portal_img.getComponent(Portal.class);
        }
    }

    public void loadNewLevel() {
        if (level_number > 3) {
            showMessage("Congratulations! You won the game", () -> getGameController().gotoMainMenu());
        } else {
            System.out.println(level_number);
            setLevel(level_number);
        }
    }

    public void onUpdate(double tpf) {
        if (Player.life == 0) {
            showMessage("GAME OVER");
        }

        if (Player.pass_level == true) {
            level_number++;
            Player.pass_level = false;
            loadNewLevel();
        }
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(InputHandler.moveUp, KeyCode.W);
        input.addAction(InputHandler.moveRight, KeyCode.D);
        input.addAction(InputHandler.moveLeft, KeyCode.A);
        input.addAction(InputHandler.moveDown, KeyCode.S);
        input.addAction(InputHandler.implantBomb, KeyCode.F);
        input.addAction(InputHandler.PlayMusic, KeyCode.M);

    }

    public void brickDestroyed(Entity brick) {
        int cellX = (int)((brick.getX()) / SCALED_SIZE);
        int cellY = (int)((brick.getY()) / SCALED_SIZE);

        grid.get(cellX, cellY).setState(CellState.WALKABLE);
    }

    public void balloomDestroyed(Entity balloom) {
        balloom_number--;
    }

    public static void main(String[] args) {
        launch(args);
    }
}