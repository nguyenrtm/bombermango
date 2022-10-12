package mangobomb.bombermango;

import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static mangobomb.bombermango.HelloApplication.*;

public class Player extends Component {
    public CellMoveComponent cell;
    public AStarMoveComponent astar;
    public static int life = 1;
    public static int PLAYER_SPEED = 200;

    public static int maxBombs = 1;
    private int bombsPlaced = 0;
    public static boolean pass_level = false;
    public static int bomb_set_radius = 24;

    public void increaseMaxBombs() {
        maxBombs++;
        inc("bomb_number", 1);
    }

    public void increaseSpeed() { PLAYER_SPEED += 100; }
    public static void increaseRadius() {
        bomb_set_radius += 24;
        inc("bomb_radius", 1);
    }

    public void placeBomb() {
        if (bombsPlaced == maxBombs) {
            return;
        }

        bombsPlaced++;

        Entity bomb = spawn("Bomb", new SpawnData(cell.getCellX() * 48, cell.getCellY() * 48).put("radius", bomb_set_radius));

        getGameTimer().runOnceAfter(() -> {
            bomb.getComponent(Bomb.class).explode();
            bombsPlaced--;
        }, Duration.seconds(2));
    }

    private AnimatedTexture texture;
    private AnimationChannel PlayerLeft, PlayerRight, PlayerIdle;

    public Player() {

    }

    @Override
    public void onAdded() {
        PlayerRight = new AnimationChannel(List.of(
                FXGL.image("sprites/player_right.png", 70, 48),
                FXGL.image("sprites/player_right_1.png", 70, 48),
                FXGL.image("sprites/player_right_2.png", 70, 48)
        ), Duration.seconds(0.25));
        PlayerIdle = new AnimationChannel(List.of(
                FXGL.image("sprites/player_down.png", 70, 48),
                FXGL.image("sprites/player_down_1.png", 70, 48),
                FXGL.image("sprites/player_down_2.png", 70, 48)
        ), Duration.seconds(0.25));
        PlayerLeft = new AnimationChannel(List.of(
                FXGL.image("sprites/player_left.png", 70, 48),
                FXGL.image("sprites/player_left_1.png", 70, 48),
                FXGL.image("sprites/player_left_2.png", 70, 48)
        ), Duration.seconds(0.25));

        texture = new AnimatedTexture(PlayerLeft);
        texture.loop();
        entity.getViewComponent().addChild(texture);
        entity.setScaleOrigin(new Point2D(24,24));
        entity.setScaleX(1);
        entity.setScaleY(1);
    }

    @Override
    public void onUpdate(double tpf) {
        if (cell.isMovingLeft() || cell.isMovingRight()) {
            if (texture.getAnimationChannel() != PlayerLeft) {
                texture.loopAnimationChannel(PlayerLeft);
            }
        } else {
            if (texture.getAnimationChannel() != PlayerIdle) {
                texture.loopAnimationChannel(PlayerIdle);
            }
        }

    }

    public void moveRight() {
        getEntity().setScaleX(-1);
        astar.moveToRightCell();
    }

    public void moveLeft() {
        getEntity().setScaleX(1);
        astar.moveToLeftCell();
    }

    public void moveUp() {
        astar.moveToUpCell();
    }

    public void moveDown() {
        astar.moveToDownCell();
    }

    String gameSong = new String("gameaudio.wav");
    Music gameMusic = FXGL.getAssetLoader().loadMusic(gameSong);
    boolean playing = false;

    public void music() {
        if (!playing) {
            FXGL.getAudioPlayer().playMusic(gameMusic);
            playing = true;
        } else {
            FXGL.getAudioPlayer().stopMusic(gameMusic);
            playing = false;
        }

    }

    public void checkPassLevel() {
        if ((balloom_number == 0) && (oneal_number == 0) && (doll_number == 0)) {
            pass_level = true;
        }
    }

    public void die() {
        life--;
    }
}
