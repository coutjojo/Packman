package Worldmanager;

import EntitySystem.Dot;
import EntitySystem.Item;

import java.awt.*;
import java.util.ArrayList;

public class PowerupManager {
    private ArrayList<Item> dots;
    private ArrayList<Item> emptyPlaces;
    private WorldGenerator world;
    private ArrayList<String> emptyTiles; //xCordinate + " " + yCordinate
   // private String[] lines = new String[50];
    //private int nextFree = 0  ;//defines the next free index in Lines
    public PowerupManager(WorldGenerator world) {
        this.world = world;
        emptyTiles = new ArrayList<String>();
        dots = new ArrayList<Item>();
        emptyPlaces = new ArrayList<Item>();
        initDots();
    }

    public void initDots(){
        findEmptySpaces();
        for (String i:emptyTiles){
            String[] Tile = i.split(" ");
            int x,y;
            x= Integer.parseInt(Tile[0]);
            y= Integer.parseInt(Tile[1]);
            dots.add(new Dot(x*50+25,y*50+25,'D'));
        }
    }


    public void findEmptySpaces(){
        for (int x = 0; x < world.getWitdh(); x++) {
            for (int y = 0; y <world.getHeight() ; y++) {
                if(world.getWorldGrid()[x][y] ==  0){
                   emptyTiles.add(x+" "+y);
                }
            }
        }
    }
    public void render(Graphics g){
        for (Item i:dots){
            i.render(g);
        }
    }
/*
    public void initDots() {
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int length = 0;
        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWitdh(); x++) {
                if (world.getWorldGrid()[x][y] == 0) {
                    startX = getlocation(x, y)[0];
                    startY = getlocation(x, y)[1];
                    while (world.getWorldGrid()[x][y] == 0) {
                        length += 1;
                        x++;
                    }
                    endX =  getlocation(x, y)[0];
                    lines[nextFree] = startX+" "+startY+" "+length+" "+endX;
                    System.out.println(startX+" "+startY+" "+length+" "+endX);
                    nextFree++;
                    length = 0;
                }
            }
        }
    }

    public int[] getlocation(int x, int y) {
        int[] res = new int[2];
        if (x == 0||x == 1){
            res[0] = 75;
        }else{res[0] = (x-1)*50+25;}
        if (y == 0||y == 1){
            res[1] = 75;
        }else{res[1] = (y-1)*50+25;}
    return res;
    }
    public void render(Graphics g){
        for (int i = 0; i < lines.length ; i++) {
            if (lines[i] != null) {
                int[] line = transformToIntArray(lines[i].split(" "));
                int times = line[2] - line[0] / 20;
                int xPos = line[0];
                while (times > 0) {
                    g.setColor(Color.yellow);
                    g.fillOval(xPos, line[1], 5, 5);
                    xPos += 15;
                    times--;
                }
            }
        }
    }
    public int[] transformToIntArray(String[] line){
        int[] res = new int[4];
        for (int j = 0; j < line.length; j++) {
            res[j] = Integer.parseInt(line[j]);
        }
        return res;
    }

 */

    public ArrayList<Item> getDots() {
        return dots;
    }
    public ArrayList<Item> getEmptyPlaces() {
        return emptyPlaces;
    }
}