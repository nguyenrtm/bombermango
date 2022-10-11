package mangobomb.bombermango;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;

import static mangobomb.bombermango.HelloApplication.*;

public class InputHandler {

    static UserAction moveUp = new UserAction("moveUp") {
        @Override
        protected void onAction() {
            HelloApplication.playerComponent.moveUp();
        }
    };

    static UserAction moveDown = new UserAction("moveDown") {
        @Override
        protected void onAction() {
            HelloApplication.playerComponent.moveDown();
        }
    };

    static UserAction moveRight = new UserAction("moveRight") {
        @Override
        protected void onAction() {
            HelloApplication.playerComponent.moveRight();
        }
    };

    static UserAction moveLeft = new UserAction("moveLeft") {
        @Override
        protected void onAction() {
            HelloApplication.playerComponent.moveLeft();
        }
    };

    static UserAction implantBomb = new UserAction("implantBomb") {
        @Override
        protected void onActionBegin() {
            HelloApplication.playerComponent.placeBomb();
        }
    };

    static  UserAction PlayMusic = new UserAction("PlayMusic") {
        @Override
        protected void onAction() {
            HelloApplication.playerComponent.music();
//            Music gamemusic = getAssetLoader().loadMusic("gameaudio.wav");
//            getAudioPlayer().playMusic(gamemusic);
            //FXGL.play("gameaudio.wav");


        }
    };
}