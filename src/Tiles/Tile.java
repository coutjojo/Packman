package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Tile {
    public static final int TILEWIDTH = 50, TILEHEIGHT = 50;
    public static Tile[] tiles = new Tile[100];
    public static Tile wallTile = new Wall(1);
    public static Tile groundTile = new Ground(0);



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
