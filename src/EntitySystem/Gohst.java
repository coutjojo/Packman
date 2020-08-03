package EntitySystem;

import java.awt.*;

public class Gohst extends Entity {
    private char color;
    private String identifier;
    private int SPEED = 5;
    //////////////////////////////////
    public Gohst(char color,String name) {
        this.identifier = name;
        this.color = color;
    }

    public void render(Graphics g) {

    }
    public void tick() {

    }
}
