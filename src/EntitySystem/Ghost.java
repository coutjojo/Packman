package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;
import Tiles.Tile;
import Utility.OwnMath;

import java.awt.*;
import java.util.Random;

public class Ghost extends Creature {

    private enum Edirections {
        Up, Right, Down, Left
    }

    private Edirections direction;
    private Edirections[] path;
    private int currentPath;

    //////////////////////////////////
    public Ghost(Handler handler, float posX, float posY) {
        super(handler, posX, posY, Tile.TILEWIDTH, Tile.TILEHEIGHT, 3.0f);
        buildPath();
        currentPath = 0;
        setDirection();
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
     * Build a random path for the entity.
     * Random direction right, left forward.
     * Then add the direction on the current direction.
     */
    private void buildPath() {
        int u = 0, r = 0, d = 0, l = 0; // u=0,r=1,d=2,l=3
        path = new Edirections[34];
        path[0] = Edirections.Down;
        d++;
        for (int i=1;i<path.length;i++) {
            // generating direction
            int dir = generatingDirection(3,path[i-1].ordinal());
            // add direction to path
            if (dir == 0) {
                this.path[i] = Edirections.Up;
                u++;
            } else if (dir == 1) {
                this.path[i] = Edirections.Right;
                r++;
            } else if (dir == 2) {
                this.path[i] = Edirections.Down;
                d++;
            } else if (dir == 3) {
                this.path[i] = Edirections.Left;
                l++;
            } else {
                System.out.println("[ERROR]" + "Not possible _dir_: " + dir);
                System.exit(1);
            }
            System.out.println(path[i].name());
        }
        System.out.println("U: " + u + ",R: " + r + ",D: " + d + ",L: " + l + ", all: " + (u+d+r+l));
    }

    /**
     * generating a random direction based on the forerunner direction
     */
    private int generatingDirection(int maxdirections, int forerunnerDirection) {
        int dir = (new Random().nextInt(maxdirections)) - 1;
        System.out.println("next direction: " + dir);
        System.out.println("before direction: " + forerunnerDirection);
        System.out.println("new direction: " + (forerunnerDirection + dir));
        dir = OwnMath.positiveValue(forerunnerDirection + dir);
        System.out.println("normalized direction: " + dir);
        if(dir == 4) {
            dir = 0;
        }
        System.out.println("final direction: " + dir);
        return dir;
    }

    /**
     * set direction to the next direction from the path-Array
     */
    public void setDirection() {
        if (currentPath >= path.length) {
            currentPath = 0;
        }
        direction = path[currentPath];
        System.out.println(currentPath);
        currentPath++;
    }

    /**
     * testing if move is possible,
     * if it is impossible move is the Motion.
     * if it is not possible OLDmove is the Motion.
     */
    public void move() {
        // collision
        if (super.collide(xMove, yMove) != NoCollision) { //test if collision
            if (super.xMove == super.xMoveOLD) // canceling xMove, if collide
                super.xMove = 0;
            else // set xMove to xMoveOLD, if collision was not on the x-Axis
                super.xMove = super.xMoveOLD;

            if (super.yMove == super.yMoveOLD) // canceling yMove, if collide
                super.yMove = 0;
            else // set yMove to yMoveOLD, if collision was not on the y-Axis
                super.yMove = super.yMoveOLD;

        }
        if (collide(xMoveOLD, yMoveOLD) != NoCollision) {
            xMove = 0;
            yMove = 0;
        }
        if (xMove == 0 && yMove == 0) {
//            System.out.println("change dir(move)");
            setDirection();
            return;
        }
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
        System.out.println("Dir: " + direction);
        //          the direction          &&   move
        if (direction.equals(Edirections.Up) && yMove < 0) {
            setDirection();
            return;
        }
        if (direction.equals(Edirections.Down) && yMove > 0) {
            setDirection();
            return;
        }
        if (direction.equals(Edirections.Right) && xMove > 0) {
            setDirection();
            return;
        }
        if (direction.equals(Edirections.Left) && xMove < 0) {
            setDirection();
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

    public void eatPlayer() {

    }

}
