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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mangobomb.bombermango.enemy.Balloom;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;
import static mangobomb.bombermango.BombermanType.*;

public class HelloApplication extends GameApplication {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    public static final int SCALED_SIZE = 48;
    public static final int SCREEN_WIDTH = SCALED_SIZE * WIDTH;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * HEIGHT;
    public static final int ZOOM_RATIO = 3;
    public static final int DEFAULT_SIZE = 16;
    public static int player_score = 0;
    public static int level_number = 1;
    public static int max_level = 2;
    public static int playtime = 150;
    public static int balloom_number;
    public static int oneal_number;
    public static int doll_number;

    public static Entity player;
    public static Entity portal_img;
    public static Entity balloom_ent;
    public static Entity oneal_ent;
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
        settings.setHeight(SCREEN_HEIGHT);
        settings.setTitle("Bomberman");
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);

    }

    @Override
    protected void initGame() {
        gameworld = getGameWorld();
        gameworld.addEntityFactory(new GenerateFactory());
        countdown();

        loadNewLevel();
    }

    public static void setLevel(int level_number) {
        Level level = getAssetLoader().loadLevel("level" + level_number + ".txt", new TextLevelLoader(48, 48, ' '));
        gameworld.setLevel(level);
        set("time", 150);
        set("bomb_radius", 1);
        set("bomb_number", 1);
        Player.maxBombs = 1;
        Player.bomb_set_radius = 24;
        playtime = 150;

        spawn("BG");

        grid = AStarGrid.fromWorld(gameworld, 31, 14, 48, 48, type -> {
            if (type.equals(WALL) || type.equals(BRICK) || type.equals(BG))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });

        set("grid", grid);

        player = spawn("Player");
        playerComponent = player.getComponent(Player.class);


        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(player, 300, 100);
        viewport.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);


        if (level_number == 1) {
            set("level", 1);
            portal_img = spawn("Portal", 24 * SCALED_SIZE, 4 * SCALED_SIZE);
            portal = portal_img.getComponent(Portal.class);
            balloom_number = 3;
            oneal_number = 2;
            doll_number = 1;
            spawn("PowerupBomb", 3 * SCALED_SIZE, 4 * SCALED_SIZE);
            spawn("PowerupBomb", 7 * SCALED_SIZE, 4 * SCALED_SIZE);
            spawn("PowerupFlame", 1 * SCALED_SIZE, 6 * SCALED_SIZE);
            spawn("PowerupBomb", 12 * SCALED_SIZE, 12 * SCALED_SIZE);
        } else if (level_number == 2) {
            set("level", level_number);
            portal_img = spawn("Portal", 19 * SCALED_SIZE, 7 * SCALED_SIZE);
            portal = portal_img.getComponent(Portal.class);
            oneal_number = 2;
            balloom_number = 4;
            doll_number = 2;
            spawn("PowerupBomb", 10 * SCALED_SIZE, 2 * SCALED_SIZE);
            spawn("PowerupBomb", 9 * SCALED_SIZE, 8 * SCALED_SIZE);
            spawn("PowerupFlame", 22 * SCALED_SIZE, 12 * SCALED_SIZE);
            spawn("PowerupBomb", 28 * SCALED_SIZE, 8 * SCALED_SIZE);
        }
    }
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", player_score);
        vars.put("level", level_number);
        vars.put("bomb_number", 1);
        vars.put("bomb_radius", 1);
        vars.put("time", 150);
    }
    private void countdown() {
        run(() -> {
            inc("time", -1);
            playtime--;
            }, Duration.seconds(1));
    }


    public void loadNewLevel() {
        if (level_number > max_level) {
            level_number = 1;
            player_score = 0;
            Player.maxBombs = 1;
            Player.bomb_set_radius = 24;
            set("level", 1);
            set("score", 0);
            set("bomb_number", 1);
            set("time", 150);
            showMessage("Congratulations! You won the game", () -> getGameController().gotoMainMenu());
        } else {
            setLevel(level_number);
        }
    }

    public void onUpdate(double tpf) {
        if (playtime == 0) {
            Player.life--;
        }
        if (Player.life == 0) {
            Player.life++;
            level_number = 1;
            player_score = 0;
            Player.maxBombs = 1;
            Player.bomb_set_radius = 24;
            set("level", 1);
            set("score", 0);
            set("bomb_number", 1);
            set("time", 150);
            showMessage("GAME OVER! YOU LOST", () -> getGameController().gotoMainMenu());
        }

        if (Player.pass_level == true) {
            level_number++;
            Player.pass_level = false;
            loadNewLevel();
        }
    }

    @Override
    protected void initUI() {
        Text textLevelText = addText("LEVEL: ", 10, 30);
        Text textLevel = addVarText("level", 80, 30);

        Text textScoreText = addText("SCORE: ", 110, 30);
        Text textScore = addVarText("score", 190, 30);

        Text textBombNumberText = addText("BOMBS: " ,250, 30);
        Text textBombNumber = addVarText("bomb_number", 330, 30);

        Text textDestroyText = addText("BOMB RADIUS: ", 380, 30);
        Text textDestroy = addVarText("bomb_radius", 530, 30);

        Text textTimeText = addText("TIME LEFT: ", 590, 30);
        Text textTime = addVarText("time", 700, 30);

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

    public void onealDestroyed(Entity oneal) {
        oneal_number--;
    }

    public void dollDestroyed(Entity doll) {doll_number--;}

    public static void main(String[] args) {
        launch(args);
    }
}