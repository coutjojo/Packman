package EntitySystem;

import java.awt.*;

public abstract class Entity {
    protected float posX,posY;
    protected Rectangle collisionBOX;

    public Entity(float posX, float posY, Rectangle collisionBOX) {
        this.posX = posX;
        this.posY = posY;
        this.collisionBOX = collisionBOX;
    }

    public abstract void render (Graphics g);


    public abstract void tick();
}
