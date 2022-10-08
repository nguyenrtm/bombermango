package mangobomb.bombermango;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;

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

    public static Entity player;
    public static Entity portal_img;
    public static Entity balloom_ent;
    public static Player playerComponent;
    public static Portal portal;
    public static Balloom balloom;
    public static Vector<Balloom> balloomlist = new Vector<Balloom>();
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

        Level level = getAssetLoader().loadLevel("test.txt", new TextLevelLoader(48, 48, ' '));
        gameworld.setLevel(level);

        spawn("BG");


        grid = AStarGrid.fromWorld(gameworld, 21, 13, 48, 48, type -> {
            if (type.equals(WALL) || type.equals(BRICK) || type.equals(BG))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });

        portal_img = spawn("Portal", 18 * SCALED_SIZE, 7 * SCALED_SIZE);
        portal = portal_img.getComponent(Portal.class);

        player = spawn("Player");
        playerComponent = player.getComponent(Player.class);

        balloom_ent = spawn("EnemyBalloom", 288,144);
        balloom_ent = spawn("EnemyBalloom", 48, 336);
        balloom_ent = spawn("EnemyBalloom", 8*SCALED_SIZE, 11*SCALED_SIZE);
        balloomlist.add(balloom_ent.getComponent(Balloom.class));

        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(player, 200, 100);
        viewport.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void onUpdate(double tpf) {
        if (Player.life == 0) {
            showMessage("GAME OVER");
        }

        if (Player.pass_level == true) {
            showMessage("CONGRATULATIONS!");
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

    public static void main(String[] args) {
        launch(args);
    }
}