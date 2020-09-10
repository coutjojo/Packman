package EntitySystem;

import java.awt.*;

public class Dot extends Item{

    public Dot(float posX, float posY, char itemType) {
        super(posX, posY,5,5, itemType);
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
