package EntitySystem;

import Main.Handler;
import Tiles.Tile;

public abstract class Creature extends Entity{
    protected int width, height;
    // Attributes for the Movement
    protected static float SPEED;
    protected float xMove; // +/- SPEED
    protected float yMove; // +/- SPEED
    protected float xMoveOLD; // +/- SPEED
    protected float yMoveOLD; // +/- SPEED
    // Attributes for the looking
    protected int currentLooking;
    protected int currentLookingBack;
    protected final int lookingUP = 0;
    protected final int lookingRIGHT = 1;
    protected final int lookingDOWN = 2;
    protected final int lookingLEFT = 3;
    // collision returns
    public static int NoCollision = 0;
    public static int TopCollision = 1;
    public static int DownCollision = 2;
    public static int LeftCollision = 3;
    public static int RightCollision = 4;
    // extras
    protected boolean alive;
    protected boolean killedCreature;


    public Creature(Handler handler, float posX, float posY, int CBwidth, int CBheight, float speed){
        super(handler,posX,posY,(int) (CBwidth - SPEED),(int) (CBheight - SPEED));
        this.SPEED = speed;
        this.width = (int) (Tile.TILEWIDTH - SPEED);
        this.height = (int) (Tile.TILEHEIGHT - SPEED);
        this.alive = true;
        this.killedCreature = false;
    }

    /**
     * Test if Packman is colliding in the next move
     * @return true if Packman collide, otherwise false
     */
    protected int collide(float XMOVE, float YMOVE) {
        if(XMOVE > 0) { // if collide when moving right
            if((posX + width + SPEED) >= (handler.getGame().getGameState().getWorld().getWitdh() * Tile.TILEWIDTH)) // leaving on the right
                return RightCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
        } else if (XMOVE < 0) { // if collide when moving left
            if(posX <= 0) // leaving on the left
                return LeftCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-up
                return LeftCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-down
                return LeftCollision;
        } else if (YMOVE > 0) { // if collide when moving down
            if((posY + height + SPEED) >= (handler.getGame().getGameState().getWorld().getHeight() * Tile.TILEHEIGHT)) // leaving on the bottom
                return DownCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-left
                return DownCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-right
                return DownCollision;
        } else if (YMOVE < 0) { // if collide when moving up
            if(posY <= 0) // leaving on the top
                return TopCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-left
                return TopCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-right
                return TopCollision;
        }
        return NoCollision;
    }

    /**
     * adjust the looking of the entity
     */
    protected void adjustLooking() {
        // adjust the viewing direction
        if(yMove < 0) { // Up
            currentLooking = lookingUP;
            currentLookingBack = lookingDOWN;
        }
        if(xMove > 0) { // Right
            currentLooking = lookingRIGHT;
            currentLookingBack = lookingLEFT;
        }
        if(yMove > 0) { // Down
            currentLooking = lookingDOWN;
            currentLookingBack = lookingUP;
        }
        if(xMove < 0) { // Left
            currentLooking = lookingLEFT;
            currentLookingBack = lookingRIGHT;
        }
    }

    /**
     * is called when the creature is died
     */
    protected void die(Creature killer) {
        this.alive = false;
        this.killedCreature = true;
    }
}
