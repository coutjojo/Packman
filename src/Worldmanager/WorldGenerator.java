package Worldmanager;

import Main.Handler;
import Tiles.Tile;
import Utility.CustomFileReader;

import java.awt.*;

public class WorldGenerator {
    private int[][] worldGrid;
    private int witdh,height;
    private int spawnX, spawnY;
    private int ghostSpawnX, ghostSpawnY;
    private int ghostCount;
    private Handler handler;
    private PowerupManager powerupManager;
    public static int upperWall = 1;
    public static int rightWall = 2;
    public static int leftWall = 3;
    public static int lowerWall = 4;
    public static int upperU = 9;
    public static int rightU = 10;
    public static int leftU = 11;
    public static int lowerU = 12;
    public static int rightUpperCorner = 5;
    public static int rightLowerCorner = 6;
    public static int leftUpperCorner = 7;
    public static int leftLowerCorner = 8;
    public static int updownTunnel = 14;
    public static int rightleftTunnel = 15;
    public static int groundTile = 0;
    public static int allWall = 13;
    /////////////////////////////////////////////////////////Class
    public WorldGenerator(String path, Handler handler){
        this.handler = handler;
        genWorld(path);
        powerupManager = new PowerupManager(this, handler);
    }
    public void genWorld(String path){
        String file = CustomFileReader.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        witdh = CustomFileReader.parseInt(tokens[0]);
        height = CustomFileReader.parseInt(tokens[1]);
        spawnX = CustomFileReader.parseInt(tokens[2]) * Tile.TILEWIDTH;
        spawnY = CustomFileReader.parseInt(tokens[3]) * Tile.TILEHEIGHT;
        ghostSpawnX = CustomFileReader.parseInt(tokens[4]) * Tile.TILEWIDTH;
        ghostSpawnY = CustomFileReader.parseInt(tokens[5]) * Tile.TILEHEIGHT;
        ghostCount = CustomFileReader.parseInt(tokens[6]);
        worldGrid = new int[witdh][height];
        for (int y = 0 ; y< height; y++){
            for (int x = 0 ; x< witdh; x++){
                worldGrid[x][y] = CustomFileReader.parseInt(tokens[(x + y*witdh)+7]);
            }
        }
        modifyWorld(worldGrid);
    }
    public void tick(){

    }
    public Tile getTile (int x ,int y){
         Tile t =Tile.tiles[worldGrid[x][y]];
         if (t == null ){
             return Tile.groundTile;
         }
         return t;
    }
    public void render(Graphics g){
        for (int y = 0 ; y< height; y++){
            for (int x = 0 ; x< witdh; x++){
                getTile(x,y).render(g,x * Tile.TILEWIDTH,y *Tile.TILEHEIGHT);
            }
        }
        powerupManager.render(g);
    }
    public void modifyWorld(int[][] world) {
        String pattern = "";//LEFT RIGHT UP DOWN
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < witdh; x++) {
                if (world[x][y]!= 0) {
                    if ( x == 0) {
                        pattern += 0;
                    }else{
                        if ( world[x - 1][y] != 0) { // check Wall Left
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if (x == witdh-1) {
                        pattern += 0;
                    }else {
                        if ( world[x + 1][y] != 0 ){ // check Wall Right
                            pattern += 1;
                        } else{
                            pattern += 0;
                        }
                    }
                    if ( y == 0) {
                        pattern += 0;
                    }else {
                        if (world[x][y - 1] != 0) { // check Wall Up
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    if ( y == height - 1 ) {
                        pattern += 0;
                    }else {
                        if (world[x][y + 1] != 0) { // check Wall Down
                            pattern += 1;
                        } else {
                            pattern += 0;
                        }
                    }
                    worldGrid[x][y] = getIdFromPattern(pattern);
                    //System.out.println("An der Stelle "+"x="+x+" "+"y="+y+" "+"ist das pattern"+pattern);
                    pattern ="";
                }
            }
        }
    }
    // LINKS RECHTS OBEN UNTEN
        public int getIdFromPattern (String pattern){
            if (pattern.equals("1000")) {
                return rightU;
            } else if (pattern.equals("0100")) {
                return leftU;
            } else if (pattern.equals("0010")) {
                return lowerU;
            } else if (pattern.equals("0001")) {
                return upperU;
            } else if (pattern.equals("1100")) {
                return rightleftTunnel;
            } else if (pattern.equals("1010")) {
                return rightLowerCorner;
            } else if (pattern.equals("1001")){
                return rightUpperCorner;
            } else if (pattern.equals("0011")) {
                return updownTunnel;
            } else if (pattern.equals("0101")) {
                return leftUpperCorner;
            } else if (pattern.equals("0110")) {
                return leftLowerCorner;
            } else if (pattern.equals("1110")) {
                return lowerWall;
            } else if (pattern.equals( "1101")) {
                return upperWall;
            } else if (pattern.equals( "1011")) {
                return rightWall;
            } else if (pattern.equals( "0111")) {
                return leftWall;
            } else if (pattern.equals( "1111")) {
                return allWall;
            }else if (pattern.equals( "0000")) {
                return allWall;
            }
          return 0;
        }


    //GETTER & SETTER
    public int getSpawnX() {
        return spawnX;
    }
    public int getSpawnY() {
        return spawnY;
    }
    public int getGhostSpawnX() {
        return ghostSpawnX;
    }
    public int getGhostSpawnY() {
        return ghostSpawnY;
    }
    public int getGhostCount() {
        return ghostCount;
    }
    public int[][] getWorldGrid() {
        return worldGrid;
    }
    public int getWitdh() {
        return witdh;
    }
    public int getHeight() {
        return height;
    }
    public PowerupManager getPowerupManager() {
        return powerupManager;
    }
}
























