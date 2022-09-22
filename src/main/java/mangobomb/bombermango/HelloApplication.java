package mangobomb.bombermango;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

import java.io.IOException;

public class HelloApplication extends GameApplication {
    public static final int WIDTH = 19;
    public static final int HEIGHT = 13;
    public static final int SCALED_SIZE = 48;
    public static final int SCREEN_WIDTH = SCALED_SIZE * WIDTH;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * HEIGHT;
    public static final int ZOOM_RATIO = 3;
    public static final int DEFAULT_SIZE = 16;

    public Entity player;
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(SCREEN_WIDTH);
        gameSettings.setHeight(SCREEN_HEIGHT);
        gameSettings.setTitle("Bomberman!");
    }

    @Override
    protected void initGame() {
        super.initGame();
        FXGL.getGameWorld().addEntityFactory(new GenerateFactory());
        FXGL.getGameWorld().spawn("BG");
        player = FXGL.getGameWorld().spawn("Player",SCALED_SIZE*4, SCALED_SIZE*5);
        for (int j = SCALED_SIZE*2; j < SCREEN_HEIGHT; j += SCALED_SIZE) {
            if (j == SCALED_SIZE*2 || j == SCREEN_HEIGHT - SCALED_SIZE) {
                for (int i = 0; i < SCREEN_WIDTH; i += SCALED_SIZE) {
                    FXGL.getGameWorld().spawn("Wall", i, j);
                }
            } else {
                if ((j / SCALED_SIZE) % 2 == 0) {
                    for (int i = 0; i < SCREEN_WIDTH; i += SCALED_SIZE * 2) {
                        FXGL.getGameWorld().spawn("Wall", i, j);
                    }
                } else {
                    FXGL.getGameWorld().spawn("Wall", 0, j);
                    FXGL.getGameWorld().spawn("Wall", SCREEN_WIDTH - SCALED_SIZE, j);
                }
            }
        }
        FXGL.getGameWorld().spawn("Bomb", SCALED_SIZE*2, SCALED_SIZE*9);
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5);
        });

        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5);
        });
    }

    @Override
    protected void initUI() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}