package EntitySystem;

import java.awt.*;

public abstract class Entity {
    protected float posX,posY;
    protected int CBwidth,CBheight;
    protected Rectangle collisionBOX;

    public Entity(float posX, float posY, int CBwidth, int CBheight) {
        this.posX = posX;
        this.posY = posY;
        this.CBwidth = CBwidth;
        this.CBheight = CBheight;
        this.collisionBOX = new Rectangle((int) posX,(int) posY,CBwidth,CBheight);
    }

    public abstract void render (Graphics g);


    public abstract void tick();
}
