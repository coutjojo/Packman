package ImageLoad;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 50, height = 50;
    public static BufferedImage packman_UP, packman_DOWN, packman_LEFT, packman_RIGHT;
    public static BufferedImage ghost1, ghost2;
    public static BufferedImage wand,boden;

    public static void init(){
        Spritsheet allPackman = new Spritsheet(ImageLoader.loadImage("/textures/packman.png"));
        packman_RIGHT = allPackman.getTile(0,0,width,height);
        packman_DOWN = allPackman.getTile(width,0,width,height);
        packman_UP = allPackman.getTile(width * 2,0,width,height);
        packman_LEFT = allPackman.getTile(width * 3,0,width,height);
        Spritsheet allGeister = new Spritsheet(ImageLoader.loadImage("/textures/geister.png"));
        ghost1 = allGeister.getTile(0,0,width,height);
        ghost2 = allGeister.getTile(width,0,width,height);
        Spritsheet background = new Spritsheet(ImageLoader.loadImage("/textures/background.png"));
        wand = background.getTile(0,0,width,height);
        boden = background.getTile(width,0,width,height);
    }
}
