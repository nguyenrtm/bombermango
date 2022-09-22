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
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(700);
        gameSettings.setHeight(700);
        gameSettings.setTitle("Bomberman!");
    }

    @Override
    protected void initGame() {
        super.initGame();
        FXGL.getGameWorld().addEntityFactory(new GenerateFactory());
        FXGL.getGameWorld().spawn("Player");
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up 1") {
            @Override
            protected void onAction() {
                System.out.println("Up");
                FXGL.getGameWorld().spawn("Rocket");
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Left 1") {
            @Override
            protected void onAction() {
                System.out.println("Left");
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Down 1") {
            @Override
            protected void onAction() {
                System.out.println("Down");
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Right 1") {
            @Override
            protected void onAction() {
                System.out.println("Right");
            }
        }, KeyCode.D);
    }

    public static void main(String[] args) {
        launch(args);
    }
}