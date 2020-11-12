package ImageLoad;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 50, height = 50;
    //Game
    public static BufferedImage packman_UP, packman_DOWN, packman_LEFT, packman_RIGHT;
    public static BufferedImage ghost1, ghost2;
    public static BufferedImage upperWall, rightWall, leftWall, lowerWall;
    public static BufferedImage upperU, rightU, leftU, lowerU;
    public static BufferedImage rightUpperCorner, rightLowerCorner, leftUpperCorner, leftLowerCorner;
    public static BufferedImage updowntunnel, rightleftTunnel, ground, allWall;
    //GameOverWindow in the game
    public static BufferedImage gameOverWindow;
    //Menu
    public static BufferedImage menuBackground;

    public static void init(){
        //PACKMAN
        Spritsheet allPackman = new Spritsheet(ImageLoader.loadImage("/textures/packman.png"));
        packman_RIGHT = allPackman.getTile(0,0,width,height);
        packman_DOWN = allPackman.getTile(width,0,width,height);
        packman_UP = allPackman.getTile(width * 2,0,width,height);
        packman_LEFT = allPackman.getTile(width * 3,0,width,height);
        //GEISTER
        Spritsheet allGeister = new Spritsheet(ImageLoader.loadImage("/textures/geister.png"));
        ghost1 = allGeister.getTile(0,0,width,height);
        ghost2 = allGeister.getTile(width,0,width,height);
        //BACKGROUND
        Spritsheet background = new Spritsheet(ImageLoader.loadImage("/textures/background.png"));
        upperWall = background.getTile(0,0,width,height);
        rightWall = background.getTile(width,0 ,width,height);
        leftWall = background.getTile(width * 2,0,width,height);
        lowerWall = background.getTile(width * 3,0,width,height);
        upperU = background.getTile(0,height,width,height);
        rightU = background.getTile(width,height,width,height);
        leftU = background.getTile(width * 2,height,width,height);
        lowerU = background.getTile(width * 3,height,width,height);
        rightUpperCorner = background.getTile(0,height * 2,width,height);
        rightLowerCorner = background.getTile(width,height * 2,width,height);
        leftUpperCorner = background.getTile(width * 2,height * 2,width,height);
        leftLowerCorner = background.getTile(width * 3,height * 2,width,height);
        rightleftTunnel = background.getTile(0,height * 3,width,height);
        updowntunnel = background.getTile(0,height * 3,width,height);
        rightleftTunnel = background.getTile(width,height * 3,width,height);
        ground = background.getTile(width * 2,height * 3,width,height);
        allWall = background.getTile(width * 3,height*3,width,height);

        gameOverWindow = ImageLoader.loadImage("/textures/gameOverWindow.png");

        //MenuBackground
        menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
    }
}
