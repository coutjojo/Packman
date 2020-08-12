package Worldmanager;

import Tiles.Tile;

import java.awt.*;
import java.io.File;

public class WorldGenerator {
 private int[][] worldGrid;
 private int witdh = 5,height = 5;
 private File f;
    public WorldGenerator(String path){
    this.f = new File(path);
    }
    public void genWorld(){
         worldGrid = new int[witdh][height];
    }
    public void tick(){

    }
    public Tile getTile (int x ,int y){
         Tile t =Tile.tiles[worldGrid[x][y]];
         if (t == null ){
             return Tile.wallTile;
         }
         return t;
    }
    public void render(Graphics g){
        genWorld();
        for (int y = 0 ; y< height; y++){
            for (int x = 0 ; x< height; x++){
                getTile(x,y).render(g,x,y);
            }
        }
    }
}
