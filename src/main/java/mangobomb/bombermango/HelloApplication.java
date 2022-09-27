package mangobomb.bombermango;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.Input;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    public static final int WIDTH = 16;
    public static final int HEIGHT = 12;
    public static final int SCALED_SIZE = 48;
    public static final int SCREEN_WIDTH = SCALED_SIZE * WIDTH;
    public static final int SCREEN_HEIGHT = SCALED_SIZE * HEIGHT;
    public static final int ZOOM_RATIO = 3;
    public static final int DEFAULT_SIZE = 16;

    public static Entity player;
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
        FXGL.getGameWorld().spawn("BG", 0, SCALED_SIZE*2);
        Level level1 = getAssetLoader().loadLevel("test.txt", new TextLevelLoader(48, 48, ' '));
        FXGL.getGameWorld().setLevel(level1);
        player = FXGL.getGameWorld().spawn("Player", 0, SCALED_SIZE*2);
        Viewport viewport = getGameScene().getViewport();
        viewport.bindToEntity(player, 200, 100);
        viewport.setBounds(0, 0, SCALED_SIZE * 21, SCALED_SIZE * 13);
    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(InputHandler.moveUp, KeyCode.W);
        input.addAction(InputHandler.moveRight, KeyCode.D);
        input.addAction(InputHandler.moveLeft, KeyCode.A);
        input.addAction(InputHandler.moveDown, KeyCode.S);
        input.addAction(InputHandler.implantBomb, KeyCode.F);
    }

    public static void main(String[] args) {
        launch(args);
    }
}