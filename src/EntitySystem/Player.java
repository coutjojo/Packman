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

    private Handler handler;

    public Player(Handler handler) {
        //super(handler.getGame().getGameState().getWorld().getSpawnX(), handler.getGame().getGameState().getWorld().getSpawnY());
        super(50,50);
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        if (xMove > 1 && yMove == 0)
            g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,null);
        else if (xMove < -1 && yMove == 0)
            g.drawImage(Assets.packman_LEFT,(int) posX,(int) posY,null);
        else if (xMove == 0 && yMove > 1)
            g.drawImage(Assets.packman_DOWN,(int) posX,(int) posY,null);
        else if (xMove == 0 && yMove < -1)
            g.drawImage(Assets.packman_UP,(int) posX,(int) posY,null);
        else
            g.drawImage(Assets.packman_RIGHT,(int) posX,(int) posY,null);
    }

    @Override
    public void tick() {
        this.getMove();
        this.move();
    }

    public void move() {
        if (collide()) {
            xMove = xMoveOLD;
            yMove = yMoveOLD;
        }
        posX += xMove;
        posY += yMove;
    }

    private boolean collide() {
        if(xMove > 1) {
            int x = (int) ((posX + Tile.TILEWIDTH + SPEED) / Tile.TILEWIDTH);
            int y = (int) (posY / Tile.TILEHEIGHT);
            // dd|| handler.getGame().getGameState().getWorld().getTile(x,y).isSolid()
            if(x > (handler.getGame().getGameState().getWorld().getWitdh()-1 * Tile.TILEWIDTH))
                return true;
        } else if (xMove < -1) {
            int x = (int) ((posX - SPEED) / Tile.TILEWIDTH);
            int y = (int) (posY / Tile.TILEHEIGHT);
            // || handler.getGame().getGameState().getWorld().getTile(x,y).isSolid()
            if(x < 0)
                return true;
        } else if (yMove < 1) {
            int x = (int) (posX / Tile.TILEWIDTH);
            int y = (int) ((posY + SPEED) / Tile.TILEHEIGHT);
            // || handler.getGame().getGameState().getWorld().getTile(x,y).isSolid()
            if(y < 0)
                return true;
        } else if (yMove < -1) {
            int x = (int) (posX / Tile.TILEWIDTH);
            int y = (int) ((posY - SPEED) / Tile.TILEHEIGHT);
            // ||handler.getGame().getGameState().getWorld().getTile(x,y).isSolid()
            if(y > (handler.getGame().getGameState().getWorld().getHeight()-1 * 50))
                return true;
        }
        return false;
    }

    public void getMove() {
        xMoveOLD = xMove;
        xMove = 0;
        yMoveOLD = yMove;
        yMove = 0;
        if(Input.UP)
            yMove = -SPEED;
        if (Input.DOWN)
            yMove = SPEED;
        if (Input.RIGHT)
            xMove = SPEED;
        if (Input.LEFT)
            xMove = -SPEED;
    }

}
