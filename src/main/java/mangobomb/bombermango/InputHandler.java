package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import static mangobomb.bombermango.HelloApplication.SCALED_SIZE;
import static mangobomb.bombermango.HelloApplication.player;

public class InputHandler {

    static UserAction moveUp = new UserAction("moveUp") {
        @Override
        protected void onAction() {
            // action just started (key has just been pressed), play swinging animation
            player.translateY(-5);
        }
    };

    static UserAction moveDown = new UserAction("moveDown") {
        @Override
        protected void onAction() {
            // action just started (key has just been pressed), play swinging animation
            player.translateY(5);
        }
    };

    static UserAction moveRight = new UserAction("moveRight") {
        @Override
        protected void onAction() {
            // action just started (key has just been pressed), play swinging animation
            player.translateX(5);
        }
    };

    static UserAction moveLeft = new UserAction("moveLeft") {
        @Override
        protected void onAction() {
            // action just started (key has just been pressed), play swinging animation
            player.translateX(-5);
        }
    };

    static UserAction implantBomb = new UserAction("implantBomb") {
        @Override
        protected void onActionBegin() {
            // action just started (key has just been pressed), play swinging animation
            FXGL.getGameWorld().spawn("Bomb", player.getX(), player.getY());
        }
    };
}