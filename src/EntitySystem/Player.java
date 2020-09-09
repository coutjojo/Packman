package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import Main.Handler;
import Tiles.Tile;

import java.awt.*;

public class Player extends Creature {
    private float SPEED = 3.0f;

    private float xMove;
    private float yMove;
    private float xMoveOLD;
    private float yMoveOLD;

    private final int lookingRIGHT = 0;
    private final int lookingLEFT = 1;
    private final int lookingDOWN = 2;
    private final int lookingUP = 3;
    private int lookingAT;

    private Handler handler;

    public Player(Handler handler,int spawnX, int spwanY) {
        super(spawnX,spwanY,null);
        this.handler = handler;
        collisionBOX = new Rectangle(width,height);
        lookingAT = lookingRIGHT; // starting view direction
    }

    @Override
    public void render(Graphics g) {
        // paint packman with his viewing direction (lookingAT)
        switch (lookingAT) {
            case lookingRIGHT:
                g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,width,height,null);
                break;
            case lookingLEFT:
                g.drawImage(Assets.packman_LEFT,(int) posX,(int) posY,width,height,null);
                break;
            case lookingUP:
                g.drawImage(Assets.packman_UP,(int) posX,(int) posY,width,height,null);
                break;
            case lookingDOWN:
                g.drawImage(Assets.packman_DOWN,(int) posX,(int) posY,width,height,null);
                break;
        }
    }

    @Override
    public void tick() {
        this.getMove();
        this.move();
    }

    public void move() {
        if (collide()) { //
            if (xMove == xMoveOLD) // canceling xMove, if collide
                xMove = 0;
            else // set xMove to xMoveOLD, if collision was not on the x-Axis
                xMove = xMoveOLD;

            if(yMove == yMoveOLD) // canceling yMove, if collide
                yMove = 0;
            else // set yMove to yMoveOLD, if collision was not on the y-Axis
                yMove = yMoveOLD;
        }

        // adjust the viewing direction
        if(xMove > 0)
            lookingAT = lookingRIGHT;
        if(xMove < 0)
            lookingAT = lookingLEFT;
        if(yMove > 0)
            lookingAT = lookingDOWN;
        if(yMove < 0)
            lookingAT = lookingUP;

        // change the position with xMove and yMove
        posX += xMove;
        posY += yMove;
    }

    private boolean collide() {
        if(xMove > 0) { // if collide when moving right
            if((posX + width + SPEED) >= (handler.getGame().getGameState().getWorld().getWitdh() * Tile.TILEWIDTH)) // leaving on the right
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile right-up
                return true;
        } else if (xMove < 0) { // if collide when moving left
            if(posX <= 0) // leaving on the left
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-up
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY + height) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile left-down
                return true;
        } else if (yMove > 0) { // if collide when moving down
            if((posY + height + SPEED) >= (handler.getGame().getGameState().getWorld().getHeight() * Tile.TILEHEIGHT)) // leaving on the bottom
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-left
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile bottom-right
                return true;
        } else if (yMove < 0) { // if collide when moving up
            if(posY <= 0) // leaving on the top
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-left
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid()) // if solid-Tile top-right
                return true;
        }
        return false;
    }

    public void getMove() {
        // set MoveOLD to the current Move
        yMoveOLD = yMove;
        xMoveOLD = xMove;
        // set Move to the new Input and reset the other Move
        if(Input.UP) {
            yMove = -SPEED;
            xMove = 0;
        }
        if (Input.DOWN) {
            yMove = SPEED;
            xMove = 0;
        }
        if (Input.RIGHT) {
            xMove = SPEED;
            yMove = 0;
        }
        if (Input.LEFT) {
            xMove = -SPEED;
            yMove = 0;
        }
    }
}
