package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
    private double SPEED = 3.0f;

    private double xMove;
    private double yMove;

    private BufferedImage texture;

    public Player(float posX, float posY) {
        super(posX, posY);
        texture = Assets.packman;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture,(int) posX,(int) posY,null);
    }

    @Override
    public void tick() {
        this.move();
    }

    public void move() {
        posX += xMove;
        posY += yMove;
    }

    public void getMove() {
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
