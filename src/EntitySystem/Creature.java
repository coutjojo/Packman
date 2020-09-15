package EntitySystem;

import Main.Handler;
import Tiles.Tile;

public abstract class Creature extends Entity{
    protected int width = 47, height = 47;
    // Attributes for the Movement
    protected static float SPEED;
    protected float xMove;
    protected float yMove;
    protected float xMoveOLD;
    protected float yMoveOLD;
    // collision returns
    public static int NoCollision = 0;
    public static int TopCollision = 1;
    public static int DownCollision = 2;
    public static int LeftCollision = 3;
    public static int RightCollision = 4;

    public Creature(Handler handler, float posX, float posY, int CBwidth, int CBheight, float speed){
        super(handler,posX,posY,(int) (CBwidth - SPEED),(int) (CBheight - SPEED));
        this.SPEED = speed;
    }

    /**
     * Test if Packman is colliding in the next move
     * @return true if Packman collide, otherwise false
     */
    protected int collide() {
        if(xMove > 0) { // if collide when moving right
            if((posX + width + SPEED) >= (handler.getGame().getGameState().getWorld().getWitdh() * Tile.TILEWIDTH)) // leaving on the right
                return RightCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return RightCollision;
        } else if (xMove < 0) { // if collide when moving left
            if(posX <= 0) // leaving on the left
                return LeftCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-up
                return LeftCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-down
                return LeftCollision;
        } else if (yMove > 0) { // if collide when moving down
            if((posY + height + SPEED) >= (handler.getGame().getGameState().getWorld().getHeight() * Tile.TILEHEIGHT)) // leaving on the bottom
                return DownCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-left
                return DownCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-right
                return DownCollision;
        } else if (yMove < 0) { // if collide when moving up
            if(posY <= 0) // leaving on the top
                return TopCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-left
                return TopCollision;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-right
                return TopCollision;
        }
        return NoCollision;
    }
}
