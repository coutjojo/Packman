package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;

import java.awt.*;

public class Ghost extends Creature {
    private String identifier;
    private int SPEED = 5;

    //////////////////////////////////
    public Ghost(Handler handler, float posX, float posY) {
        super(handler,posX,posY,0,0,3.0f);
        //this.identifier = name;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.ghost1,(int) posX,(int) posY,null);
    }

    public void tick() {
        move();
        if(this.collisionBOX.intersects(handler.getGame().getGameState().getPlayer().collisionBOX))
            eatPlayer();
    }

    public void startMove() {
        xMove += SPEED;
    }

    public void move() {
        if (super.collide() == TopCollision || super.collide() == DownCollision) { // test if top Collision or left Collision
            double r = Math.random();
            yMove = 0;
            if (r >= 0.5)
                xMove = SPEED;
            else
                xMove = -SPEED;
        } else if (super.collide() == LeftCollision || super.collide() == RightCollision) { // test if left Collision right Collision
            double r = Math.random();
            xMove = 0;
            if (r >= 0.5)
                yMove = SPEED;
            else
                yMove = -SPEED;
        }

        // change the position with xMove and yMove
        super.posX += super.xMove;
        super.posY += super.yMove;
        super.collisionBOX.setLocation((int) posX, (int) posY);
    }

    public void eatPlayer() {

    }

    public void killedByPlayer() {

    }
}
