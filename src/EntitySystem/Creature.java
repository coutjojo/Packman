package EntitySystem;

import java.awt.*;

public abstract class Creature extends Entity{
    protected int width = 47, height = 47;
    public Creature(float posX, float posY, int CBwidth, int CBheight){
        super(posX,posY,CBwidth,CBheight);
    }
}
