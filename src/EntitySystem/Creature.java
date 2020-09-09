package EntitySystem;

import java.awt.*;

public abstract class Creature extends Entity{
    protected int width = 47, height = 47;
    public Creature(float posX, float posY, Rectangle collisionBOX){
        super(posX,posY,collisionBOX);
        if (collisionBOX != null)
            collisionBOX.setLocation((int) (posX + (width / 2)),(int) (posY + (height / 2)));
    }
}
