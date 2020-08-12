package Worldmanager;

import Tiles.Tile;
import Utility.CustomFileReader;

import java.awt.*;

public class WorldGenerator {
 private int[][] worldGrid;
 private int witdh = 5,height = 5;
 private int spawnX, spawnY;

    public WorldGenerator(String path){
        genWorld(path);

    }
    public void genWorld(String path){
        String file = CustomFileReader.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        witdh = CustomFileReader.parseInt(tokens[0]);
        height = CustomFileReader.parseInt(tokens[1]);
        spawnX = CustomFileReader.parseInt(tokens[2]);
        spawnY = CustomFileReader.parseInt(tokens[3]);
        worldGrid = new int[witdh][height];
        for (int y = 0 ; y< height; y++){
            for (int x = 0 ; x< height; x++){
                worldGrid[x][y] = CustomFileReader.parseInt(tokens[(x + y*witdh)+4]);
            }
        }
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
        for (int y = 0 ; y< height; y++){
            for (int x = 0 ; x< height; x++){
                getTile(x,y).render(g,x * Tile.TILEWIDTH,y *Tile.TILEHEIGHT);
            }
        }
    }
}
