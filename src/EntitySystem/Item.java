package EntitySystem;

import java.awt.*;

public abstract class Item extends Entity {
    private char itemType; // P für powerup d Für dot
    public Item(float posX, float posY, Rectangle collisionBOX, char itemType) {
        super(posX, posY, collisionBOX);
        this.itemType = itemType;
    }
}
