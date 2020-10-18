package EntitySystem;

import Main.Handler;

import java.awt.*;

public class Dot extends Item{

    public Dot(Handler handler, float posX, float posY, char itemType) {
        super(handler, posX, posY,5,5, itemType);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval( (int) posX, (int) posY,5,5);
    }

    @Override
    public void tick() {

    }
}
