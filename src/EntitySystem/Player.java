package EntitySystem;

import ImageLoad.Assets;
import Input.Input;
import Main.Handler;
import Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature {
    // Attributes for the looking
    private final int lookingRIGHT = 0;
    private final int lookingLEFT = 1;
    private final int lookingDOWN = 2;
    private final int lookingUP = 3;
    private int lookingAT;
    //Attributes for the Dots
    private int dotCounter = 0;
    private ArrayList<Item> removedDots;

    public Player(Handler handler,int spawnX, int spwanY) {
        super(handler, spawnX, spwanY, Tile.TILEWIDTH, Tile.TILEHEIGHT, 3.0f);
        lookingAT = lookingRIGHT; // starting view direction
        removedDots = new ArrayList<Item>();
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
        this.eatDot();
    }

    /**
     * test if Packman is above a Dot, if it is true the Dot is deleted and the dotCounter is increased
     * otherwise nothing happened
     */
    public void eatDot() {
        if (super.handler.getWorld().getPowerupManager().getItems() == null)
            return;
        for (Item d : super.handler.getWorld().getPowerupManager().getItems()) {
            if (this.collisionBOX.intersects(d.collisionBOX)) {
                this.dotCounter += 1;
                removedDots.add(d);
                super.handler.getWorld().getPowerupManager().getEmptyPlaces().add(d);
            }
        }
        for (Item d : removedDots) {
            super.handler.getWorld().getPowerupManager().getItems().remove(d);
        }
        removedDots.clear();
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
}
