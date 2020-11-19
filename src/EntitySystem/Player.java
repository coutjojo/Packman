package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import Main.Handler;
import States.GameState;
import Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature {
    //Attributes for the Dots
    private int dotCounter = 0;

    public Player(Handler handler,int spawnX, int spwanY, float pSPEED) {
        super(handler, spawnX, spwanY, Tile.TILEWIDTH, Tile.TILEHEIGHT, pSPEED);
        currentLooking = lookingRIGHT; // starting view direction
    }

    @Override
    public void render(Graphics g) {
        // paint packman with his viewing direction (lookingAT)
        switch (currentLooking) {
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
        //DotCounter on Sreen
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.BOLD,36));
        g.drawString("" + this.dotCounter,(handler.getWorld().getWitdh() - 2) * Tile.TILEWIDTH,(int) (Tile.TILEHEIGHT * 0.80));
        /* //CollisionBOX showing
        {
            // collisionBox Packman
            g.setColor(Color.RED);
            g.drawRect(collisionBOX.x,collisionBOX.y,collisionBOX.width,collisionBOX.height);
            // collisionBox Dots
            for (Item d : handler.getWorld().getPowerupManager().getDots()) {
                g.setColor(Color.GREEN);
                g.drawOval(d.collisionBOX.x,d.collisionBOX.y,d.collisionBOX.width,d.collisionBOX.height);
            }
        }
        */
    }

    @Override
    public void tick() {
        this.getMove();
        this.move();
        this.win();
        this.eatDot();
        //TODO player eating ghosts
    }

    /**
     * test if Packman is above a Dot, if it is true the Dot is deleted and the dotCounter is increased
     * otherwise nothing happened
     */
    public void eatDot() {
        if (super.handler.getWorld().getPowerupManager().getItems() == null)
            return;
        Dot removedDot = null;
        for (Item d : super.handler.getWorld().getPowerupManager().getItems()) {
            if(d.getClass() == Dot.class) {
                if (this.collisionBOX.intersects(d.collisionBOX)) {
                    this.dotCounter += 1;
                    removedDot = (Dot) d;
                }
            }
        }
        handler.getWorld().getPowerupManager().removeItem(removedDot);
    }

    /**
     * test if the movement is possible
     * adjust the Looking
     * apply xMove and yMove onto posX and posY
     */
    public void move() {
        // collision
        if (super.collide(xMove, yMove) != NoCollision) { //test if collision
            if (super.xMove == super.xMoveOLD) // canceling xMove, if collide
                super.xMove = 0;
            else // set xMove to xMoveOLD, if collision was not on the x-Axis
                super.xMove = super.xMoveOLD;

            if(super.yMove == super.yMoveOLD) // canceling yMove, if collide
                super.yMove = 0;
            else // set yMove to yMoveOLD, if collision was not on the y-Axis
                super.yMove = super.yMoveOLD;
        }
        //adjust looking
        super.adjustLooking();
        // change the position with xMove and yMove
        super.posX += super.xMove;
        super.posY += super.yMove;
        super.collisionBOX.setLocation((int) posX,(int) posY);
    }

    /**
     * set MoveOLD
     * translate the input in xMove and yMove
     */
    public void getMove() {
        // set MoveOLD to the current Move
        super.yMoveOLD = super.yMove;
        super.xMoveOLD = super.xMove;
        // set Move to the new Input and reset the other Move
        if (Input.UP) {
            super.yMove = -super.SPEED;
            super.xMove = 0;
        }
        if (Input.DOWN) {
            super.yMove = super.SPEED;
            super.xMove = 0;
        }
        if (Input.RIGHT) {
            super.xMove = super.SPEED;
            super.yMove = 0;
        }
        if (Input.LEFT) {
            super.xMove = -super.SPEED;
            super.yMove = 0;
        }
    }

    public void win() {
        System.out.println(handler.getWorld().getPowerupManager().getDotCount());
        if(handler.getWorld().getPowerupManager().getDotCount() == 0) {
            System.out.println("help");
            handler.getGameState().gameOver(GameState.WIN);
        }
    }
}
