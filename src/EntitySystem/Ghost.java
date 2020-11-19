package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;
import States.GameState;
import Tiles.Tile;

import java.awt.*;
import java.util.Random;

public class Ghost extends Creature {
    // Attributes for moving
    private enum Edirections {
        Up, Right, Down, Left
    }
    private Edirections direction;

    //CONTROL VARIABLES
//    int u = 0, r = 0, d = 0, l = 0; // u=0,r=1,d=2,l=3

    //////////////////////////////////
    public Ghost(Handler handler, float posX, float posY, float pSPEED) {
        super(handler, posX, posY, Tile.TILEWIDTH, Tile.TILEHEIGHT, pSPEED);
        direction = Edirections.Down;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.ghost1, (int) posX, (int) posY, width, height, null);
        /*
        { //collisionBOX
            g.setColor(Color.MAGENTA);
            g.fillRect(collisionBOX.x, collisionBOX.y, collisionBOX.width, collisionBOX.height);
        }
        */
    }

    public void tick() {
        setMove();
        move();
        if (this.collisionBOX.intersects(handler.getGame().getGameState().getPlayer().collisionBOX))
            eatPlayer();
    }

    /**
     * Translate a direction as int, in a direction from {@link Edirections}
     * @param dir = direction as int
     * @return = direction as {@link Edirections}
     */
    private Edirections dirInEdirection(int dir) {
        if (dir == 0) {
            return Edirections.Up;
        } else if (dir == 1) {
            return Edirections.Right;
        } else if (dir == 2) {
            return Edirections.Down;
        } else if (dir == 3) {
            return Edirections.Left;
        } else {
            // print to the console if direction is invalid and the program is closed
            System.out.println("[ERROR]" + "Not possible _dir_: " + dir);
            System.exit(1);
        }
        return null;
    }

    /**
     * generating a random direction based on the forerunner direction
     */
    private int generatingDirection(int maxDirections, int forerunnerDirection) {
//        System.out.println("max directions: " + maxDirections);
        int dir = (new Random().nextInt(maxDirections) - 1);
//        System.out.println("next direction: " + dir);
//        System.out.println("before direction: " + forerunnerDirection);
//        System.out.println("new direction: " + (forerunnerDirection + dir));
        dir = forerunnerDirection + dir;
//        System.out.println("normalized direction: " + dir);
        if(dir == -1) {
            dir = 3;
        }
        if(dir == 4) {
            dir = 0;
        }
//        System.out.println("final direction: " + dir);
        return dir;
    }

    /**
     * set direction to the next direction from the path-Array
     */
    public void setDirection(boolean motionZero) {
        if(motionZero) {
//            System.out.println("Collide in the direction.");
            int collide = 0;
            boolean[] freeSpaces = new boolean[4];
            if(collide(0,-SPEED) == NoCollision) { // Up
                collide++;
                freeSpaces[0] = true;
            }
            if(collide(SPEED,0) == NoCollision) { // Right
                collide++;
                freeSpaces[1] = true;
            }
            if(collide(0,SPEED) == NoCollision) { // Down
                collide++;
                freeSpaces[2] = true;
            }
            if(collide(-SPEED,0) == NoCollision) { // Left
                collide++;
                freeSpaces[3] = true;
            }
/*
            { // print the freeSpaces-Array to the console
                System.out.print("freeSpaces-Array: ");
                for (boolean fs : freeSpaces) {
                    System.out.print(fs + " | ");
                }
                System.out.println();
            }
*/
            // remove current direction from freeSpaces
//            System.out.println("current backward looking : " + currentLookingBack);
//            System.out.println("current backward direction in freeSpaces: " + freeSpaces[currentLookingBack]);
            freeSpaces[currentLookingBack] = false;
            collide--;
//            System.out.println("current backward direction in freeSpaces: " + freeSpaces[currentLookingBack]);
/*
            // print the freeSpaces-Array to the console
            System.out.print("freeSpaces-Array: ");
            for (boolean fs : freeSpaces) {
                System.out.print(fs + " | ");
            }
            System.out.println();
*/
            if (collide == 1) { // if only one possible direction
                // set direction to the only free direction
                for(int i=0;i< freeSpaces.length;i++) {
                    if(freeSpaces[i]) {
                        direction = dirInEdirection(i);
                    }
                }
            } else {
//                System.out.println("More than one possible direction");
                // generating direction
                int dir = generatingDirection(collide, direction.ordinal());
                // set direction to the new direction
                direction = dirInEdirection(dir);
            }
//            System.out.println("new generated direction: " + direction.name());
        } else {
//            System.out.println("Change direction after turning.");
            // generating direction
            int dir = generatingDirection(3,direction.ordinal());
            // set direction to the new direction
            direction = dirInEdirection(dir);
        }
    }

    /**
     * testing if move is possible,
     * if it is impossible move is the Motion.
     * if it is not possible OLDmove is the Motion.
     *
     * adjust looking
     */
    public void move() {
        // collision
        if (super.collide(xMove, yMove) != NoCollision) { //test if collision
//            System.out.println("COLLIDE");
            if (super.xMove == super.xMoveOLD) // canceling xMove, if collide
                super.xMove = 0;
            else // set xMove to xMoveOLD, if collision was not on the x-Axis
                super.xMove = super.xMoveOLD;

            if (super.yMove == super.yMoveOLD) // canceling yMove, if collide
                super.yMove = 0;
            else // set yMove to yMoveOLD, if collision was not on the y-Axis
                super.yMove = super.yMoveOLD;

        }
//        System.out.println("xM: " + xMove + ", yM: " + yMove + ", xMO: " + xMoveOLD + ", yMO: " + yMoveOLD);
        if (collide(xMoveOLD, yMoveOLD) != NoCollision) {
            xMove = 0;
            yMove = 0;
        }
        if (xMove == 0 && yMove == 0 && xMoveOLD == 0 && yMoveOLD == 0) { // detect if direction is not possible and change the direction to a possible direction
//            System.out.println("xM: " + xMove + ", yM: " + yMove);
//            System.out.println("change dir(move)");
            setDirection(true);
            return;
        }
        //adjust looking
        super.adjustLooking();
        // change the position with xMove and yMove
        posX += xMove;
        posY += yMove;
        super.collisionBOX.setLocation((int) posX, (int) posY);
    }

    /**
     * OLDMove is the current move
     * if the motion of the entity has changed
     *  setDirection is called and return out of the function that setDirection can finish before the product is called
     */
    private void setMove() {
        // set MoveOLD to the current Move
        super.xMoveOLD = super.xMove;
        super.yMoveOLD = super.yMove;

        // setDir
//        System.out.println("Dir: " + direction);
        //          the direction          &&   move
        if (direction.equals(Edirections.Up) && yMove < 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Down) && yMove > 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Right) && xMove > 0) {
            setDirection(false);
            return;
        }
        if (direction.equals(Edirections.Left) && xMove < 0) {
            setDirection(false);
            return;
        }

        // set Input
        switch (direction) {
            case Up:
                super.yMove = -SPEED;
                super.xMove = 0;
                break;
            case Down:
                super.yMove = SPEED;
                super.xMove = 0;
                break;
            case Right:
                super.xMove = SPEED;
                super.yMove = 0;
                break;
            case Left:
                super.xMove = -SPEED;
                super.yMove = 0;
                break;
        }
    }

    /**
     * set the game to gameOver and call the GameOverWindow
     */
    public void eatPlayer() {
        //System.exit(999);
        System.out.println("YOU HAVE LOST");
        handler.getGameState().gameOver(GameState.LOST);
    }

}
