package EntitySystem;

import java.awt.*;

public abstract class Creature extends Entity{
    protected int width = 45, height = 45;
    public Creature(float posX, float posY, Rectangle collisionBOX){
        super( posX, posY, collisionBOX);
    }
}
