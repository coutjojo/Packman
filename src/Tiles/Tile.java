package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {
    public static final int TILEWIDTH = 50, TILEHEIGHT = 50;
    public static Tile[] tiles = new Tile[100];
    public static Tile groundTile = new Ground(0);
    public static Tile upperWallTile = new UpperWall(1);
    public static Tile rightWallTile = new RightWall(2);
    public static Tile leftWallTile = new LeftWall(3);
    public static Tile lowerWallTile = new LowerWall(4);
    public static Tile rightupperCornerTile = new RightUpperCorner(5);
    public static Tile rightLowerCornerTile = new RightLowerCorner(6);
    public static Tile leftUpperCornerTile = new LeftUpperCorner(7);
    public static Tile leftLowerCornerTile = new LeftLowerCorner(8);
    public static Tile upperU = new UpperU(9);
    public static Tile rightU = new RightU(10);
    public static Tile leftU = new LeftU(11);
    public static Tile lowerU = new LowerU(12);
    public static Tile allWall = new AllWall(13);
    public static Tile updownTunnel = new Updowntunnel(14);
    public static Tile rightleftTunnel = new RightleftTunnel(15);

    protected BufferedImage texture;

    protected final int id;

    public Tile(int id, BufferedImage texture){
        this.id = id;
        this.texture = texture;

        tiles[id] = this;
    }
    public int getID(){
        return id;
    }
    public boolean isSolid(){
        return false;
    }
    public void tick(){

    }
    public void render (Graphics g, int x, int y){
        g.drawImage(texture,x,y, TILEWIDTH,TILEHEIGHT,null);
    }
}
