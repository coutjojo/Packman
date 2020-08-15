package EntitySystem;

import java.awt.*;

public abstract class Item extends Entity {
    public Item(float posX, float posY, Rectangle collisionBOX) {
        super(posX, posY, collisionBOX);
    }
}
