package EntitySystem;

import ImageLoad.Assets;
import Main.Handler;
import Tiles.Tile;

import java.awt.*;

public class Ghost extends Creature {
    private String identifier;

    private enum freeTiles {
        Up,Down,Right,Left;
    }
    private freeTiles direction;
    private freeTiles[] path;
    private int currentPath;

    //////////////////////////////////
    public Ghost(Handler handler, float posX, float posY) {
        super(handler,posX,posY, Tile.TILEWIDTH,Tile.TILEHEIGHT,3.0f);
        //this.identifier = name;
        buildPath();
        direction = freeTiles.Right;
        currentPath = 0;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.ghost1,(int) posX,(int) posY,width,height,null);
        /*
        {
            g.setColor(Color.MAGENTA);
            g.fillRect(collisionBOX.x, collisionBOX.y, collisionBOX.width, collisionBOX.height);
        }
        */
    }

    public void tick() {
        setMove();
        move();
        if(this.collisionBOX.intersects(handler.getGame().getGameState().getPlayer().collisionBOX))
            eatPlayer();
    }

    private void buildPath() {
        int u=0,d=0,r=0,l=0;
        //int random = 0;
        int[] a = {2,3,4,1,4,3,4,1,2,3,2,1,4,3,2,1,2,1,2,3,2,1,3,4,2,3,2,1,2,3,4,3,4,3};
        path = new freeTiles[34];
        for (int i=0;i<path.length;i++) {
            //while (i > 2 && path[i] == path[i-1]) {
                //random = (int) (Math.floorMod((int) (Math.random() * 100), 4));
                if (a[i] == 4) {
                    this.path[i] = freeTiles.Up;
                    u++;
                } else if (a[i] == 1) {
                    this.path[i] = freeTiles.Down;
                    d++;
                } else if (a[i] == 2) {
                    this.path[i] = freeTiles.Right;
                    r++;
                } else if (a[i] == 3) {
                    this.path[i] = freeTiles.Left;
                    l++;
                } else {
                    //System.out.println(r);
                }
            //}
        }
        System.out.println("U: " + u +",D: " + d +",R: " + r +",L: " + l );
    }

    public void setDirection() {
        if(currentPath >= path.length) {
            currentPath = 0;
        }
        direction = path[currentPath];
        currentPath++;
        /*
        int r = (int) (Math.random() * 4);
        System.out.println(r);
        if(xMove != 0 && yMove == 0) {
            if(r == 2) {
                direction = freeTiles.Up;
                System.out.println("WW");
            } else if(r == 3) {
                direction = freeTiles.Down;
            } else {
                if((xMove / SPEED) == -1) {
                    direction = freeTiles.Right;
                } else if((xMove / SPEED) == 1) {
                    direction = freeTiles.Left;
                }
            }
        }
        if(yMove != 0 && xMove == 0) {
            if(r == 2) {
                direction = freeTiles.Right;
            } else if(r == 3) {
                direction = freeTiles.Left;
            } else {
                if((yMove / SPEED) == -1) {
                    direction = freeTiles.Down;
                } else if((yMove / SPEED) == 1) {
                    direction = freeTiles.Up;
                }
            }
        }
        if(yMove == 0 && xMove == 0) {
            if(xMoveOLD != 0 && yMoveOLD == 0) {
                System.out.println("WW");
                if(r == 2) {
                    direction = freeTiles.Up;
                } else if(r == 3) {
                    direction = freeTiles.Down;
                } else {
                    if((xMoveOLD / SPEED) == -1) {
                        direction = freeTiles.Right;
                    } else if((xMoveOLD / SPEED) == 1) {
                        direction = freeTiles.Left;
                    }
                }
            }
            if(yMoveOLD != 0 && xMoveOLD == 0) {
                System.out.println("QQ");
                if(r == 2) {
                    direction = freeTiles.Right;
                } else if(r == 3) {
                    direction = freeTiles.Left;
                } else {
                    if((yMoveOLD / SPEED) == -1) {
                        direction = freeTiles.Down;
                    } else if((yMoveOLD / SPEED) == 1) {
                        direction = freeTiles.Up;
                    }
                }
            }
        }
        System.out.println(direction.name());
        */
    }

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
        if(collide(xMoveOLD, yMoveOLD) != NoCollision) {
            xMove = 0;
            yMove = 0;
        }
        if(xMove == 0 && yMove == 0) {
            setDirection();
            return;
        }
        posX += xMove;
        posY += yMove;
        super.collisionBOX.setLocation((int) posX, (int) posY);
    }

    private void setMove() {
        // set MoveOLD to the current Move
        super.xMoveOLD = super.xMove;
        super.yMoveOLD = super.yMove;

        // setDir
        System.out.println("Dir: " + direction);
        if(direction.equals(freeTiles.Up) && yMove < 0) {
            System.out.println("direction changed: UP");
            setDirection();
            return;
        }
        if(direction.equals(freeTiles.Down) && yMove > 0) {
            System.out.println("direction changed: Down");
            setDirection();
            return;
        }
        if(direction.equals(freeTiles.Right) && xMove > 0) {
            System.out.println("direction changed: Right");
            setDirection();
            return;
        }
        if(direction.equals(freeTiles.Left) && xMove < 0) {
            System.out.println("direction changed: Left");
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

    public void killedByPlayer() {

    }
}
/*
    move()
        /
        if(collide(xMove, yMove) != NoCollision) {
            xMove = xMoveOLD;
            yMove = yMoveOLD;
        }
        posX += xMove;
        posY += yMove;
        /
        /
        System.out.println("Dir: " + direction);
        // change the position with xMove and yMove
        if(collide(xMove, yMove) != NoCollision) {
            if(collide(xMoveOLD, yMoveOLD) != NoCollision) {
                setDirection(0);
                return;
            }
            super.posX += super.xMove;
            super.posY += super.yMove;
        } else {
            super.posX += super.xMoveOLD;
            super.posY += super.yMoveOLD;
        }
        super.posX += super.xMove;
        super.posY += super.yMove;
         /

    setMove()
        /
        if(direction == Top) {
            yMove = -SPEED;
            xMove = 0;
        }
        if(direction == Down) {
            yMove = SPEED;
            xMove = 0;
        }
        if(direction == Right) {
            xMove = SPEED;
            yMove = 0;
        }
        if(direction == Left) {
            xMove = -SPEED;
            yMove = 0;
        }
        /

    setDirection()
        direction = (int) (Math.round(Math.random() * 4));
        dirChanged = false;
        /
        int a = 5;
        System.out.println("Vordir: " + direction);
        while(a > 0 && direction == directionFront && direction == directionBack && direction == 4) {
            direction = (int) (Math.round(Math.random() * 4));
            System.out.println("dir: " + direction);
            a--;
        }
        if(direction == Top) {
            directionFront = Top;
            directionBack = Down;
        }
        if(direction == Down) {
            directionFront = Down;
            directionBack = Top;
        }
        if(direction == Right) {
            directionFront = Right;
            directionBack = Left;
        }
        if(direction == Left) {
            directionFront = Left;
            directionBack = Right;
        }
        dirChanged = false;
         /
 */