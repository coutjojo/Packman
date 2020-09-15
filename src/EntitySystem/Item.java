package EntitySystem;

import Main.Handler;

import java.awt.*;

public abstract class Item extends Entity {
    private char itemType; // P für powerup d Für dot
    public Item(Handler handler, float posX, float posY, int CBwidth, int CBheight, char itemType) {
        super(handler, posX, posY, CBwidth, CBheight);
        this.itemType = itemType;
    }
}
