package ImageLoad;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 50, height = 50;
    public static BufferedImage packman;
    public static BufferedImage ghost1, ghost2;
    public static BufferedImage wand,boden;

    public static void init(){
        packman = ImageLoader.loadImage("/textures/packman.png");
        Spritsheet geister = new Spritsheet(ImageLoader.loadImage("/textures/geister.png"));
        ghost1 = geister.getTile(0,0,width,height);
        ghost2 = geister.getTile(width,0,width,height);
        Spritsheet background = new Spritsheet(ImageLoader.loadImage("/textures/background.png"));
        wand = background.getTile(0,0,width,height);
        boden = background.getTile(width,0,width,height);
    }
}
