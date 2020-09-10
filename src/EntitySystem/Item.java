package EntitySystem;

import java.awt.*;

public abstract class Item extends Entity {
    private char itemType; // P für powerup d Für dot
    public Item(float posX, float posY, int CBwidth, int CBheight, char itemType) {
        super(posX, posY, CBwidth, CBheight);
        this.itemType = itemType;
    }
}
