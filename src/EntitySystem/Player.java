package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import Main.Handler;

import java.awt.*;

public class Player extends Creature {
    private float SPEED = 3.0f;

    private float xMove;
    private float yMove;

    private Handler handler;

    public Player(float posX, float posY, Handler handler) {
        super(posX, posY);
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        if (xMove > 1 && yMove == 0)
            g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,null);
        else if (xMove < -1 && yMove == 0)
            g.drawImage(Assets.packman_LEFT,(int) posX,(int) posY,null);
        else if (xMove == 0 && yMove > 1)
            g.drawImage(Assets.packman_DOWN,(int) posX,(int) posY,null);
        else if (xMove == 0 && yMove < -1)
            g.drawImage(Assets.packman_UP,(int) posX,(int) posY,null);
        else
            g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,null);
    }

    @Override
    public void tick() {
        this.getMove();
        this.move();
    }

    public void move() {
        if(!collide()) {
            posX += xMove;
            posY += yMove;
        }
    }

    private boolean collide() {

        //if(handler.getGame().getGameState().)

        return false;
    }

    public void getMove() {
        xMove = 0;
        yMove = 0;
        if(Input.UP)
            yMove = -SPEED;
        if (Input.DOWN)
            yMove = SPEED;
        if (Input.RIGHT)
            xMove = SPEED;
        if (Input.LEFT)
            xMove = -SPEED;
    }

}
