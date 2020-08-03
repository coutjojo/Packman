package EntitySystem;

import java.awt.*;

public abstract class Entity {
    private double posX,posY;

    public Entity(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
    }
    public abstract void render (Graphics g);


    public abstract void tick();
}
