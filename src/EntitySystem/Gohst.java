package EntitySystem;

import java.awt.*;

public class Gohst extends Creature {
    private char color;
    private String identifier;
    private int SPEED = 5;

    //////////////////////////////////
    public Gohst(char color,String name,float posX, float posY) {
        super(posX,posY,0,0);
        this.identifier = name;
        this.color = color;
    }

    public void render(Graphics g) {

    }
    public void tick() {

    }
}
