package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import Main.Handler;
import Tiles.Tile;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Creature {
    private float SPEED = 3.0f;

    private float xMove;
    private float yMove;
    private float xMoveOLD;
    private float yMoveOLD;

    private final int lookingUP = 0;
    private final int lookingRIGHT = 1;
    private final int lookintDOWN = 2;
    private final int lookintLEFT = 3;
    private int lookingAT = lookingRIGHT;

    private Handler handler;

    public Player(Handler handler) {
        //super(handler.getGame().getGameState().getWorld().getSpawnX(), handler.getGame().getGameState().getWorld().getSpawnY());
        super(50,50, new Rectangle(50,50));
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        switch (lookingAT) {
            case lookingRIGHT:
                g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,width,height,null);
                break;
            case lookintLEFT:
                g.drawImage(Assets.packman_LEFT,(int) posX,(int) posY,width,height,null);
                break;
            case lookingUP:
                g.drawImage(Assets.packman_UP,(int) posX,(int) posY,width,height,null);
                break;
            case lookintDOWN:
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
        if (collide()) {
            if (xMove == xMoveOLD)
                xMove = 0;
            else
                xMove = xMoveOLD;
            if(yMove == yMoveOLD)
                yMove = 0;
            else
                yMove = yMoveOLD;
        }
        if(xMove > 0)
            lookingAT = lookingRIGHT;
        if(xMove < 0)
            lookingAT = lookintLEFT;
        if(yMove > 0)
            lookingAT = lookintDOWN;
        if(yMove < 0)
            lookingAT = lookingUP;
        posX += xMove;
        posY += yMove;
    }

    private boolean collide() {
        if(xMove > 1) {
            if((posX + width + SPEED) > (handler.getGame().getGameState().getWorld().getWitdh() * Tile.TILEWIDTH))
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY) / Tile.TILEHEIGHT))).isSolid())
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width + SPEED) / Tile.TILEWIDTH),   (int) ((posY + height) / Tile.TILEHEIGHT))).isSolid())
                return true;
        } else if (xMove < -1) {
            if(posX < 0)
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY) / Tile.TILEHEIGHT))).isSolid())
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX - SPEED) / Tile.TILEWIDTH),           (int)((posY + height) / Tile.TILEHEIGHT))).isSolid())
                return true;
        } else if (yMove > 1) {
            if((posY + height + SPEED) > (handler.getGame().getGameState().getWorld().getHeight() * Tile.TILEHEIGHT))
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid())
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY + height + SPEED) / Tile.TILEHEIGHT))).isSolid())
                return true;
        } else if (yMove < -1) {
            if(posY < 0)
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX) / Tile.TILEWIDTH),                   (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid())
                return true;
            else if((handler.getGame().getGameState().getWorld().getTile((int)((posX + width) / Tile.TILEWIDTH),           (int)((posY - SPEED) / Tile.TILEHEIGHT))).isSolid())
                return true;
        }
        return false;
    }

    public void getMove() {
        yMoveOLD = yMove;
        xMoveOLD = xMove;
        xMove = 0;
        yMove = 0;
        if(Input.UP) {
            yMove = -SPEED;
        }
        if (Input.DOWN) {
            yMove = SPEED;
        }
        if (Input.RIGHT) {
            xMove = SPEED;
        }
        if (Input.LEFT) {
            xMove = -SPEED;
        }
    }

}
