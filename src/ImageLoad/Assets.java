package ImageLoad;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 50, height = 50;
    public static BufferedImage packman_UP, packman_DOWN, packman_LEFT, packman_RIGHT;
    public static BufferedImage ghost1, ghost2;
    public static BufferedImage upperWall, rightWall, leftWall, downWall;
    public static BufferedImage upperCorner, rightCorner, leftCorner, downCorner;
    public static BufferedImage updowntunnel, rightleftTunnel, boden;

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
        upperWall = background.getTile(0,0,width,height);
        rightWall = background.getTile(width,0 ,width,height);
        leftWall = background.getTile(width * 2,0,width,height);
        downWall = background.getTile(width * 3,0,width,height);
        upperCorner = background.getTile(0,height,width,height);
        rightCorner = background.getTile(width,height,width,height);
        leftCorner = background.getTile(width * 2,height,width,height);
        downCorner = background.getTile(width * 3,height,width,height);
        updowntunnel = background.getTile(0,height * 2,width,height);
        rightleftTunnel = background.getTile(width,height * 2,width,height);
        boden = background.getTile(width * 2,height * 2,width,height);
    }

    /**
     g.drawImage(Assets.upperWall,0,0,null);
     g.drawImage(Assets.rightWall,50,0,null);
     g.drawImage(Assets.leftWall,100,0,null);
     g.drawImage(Assets.downWall,150,0,null);
     g.drawImage(Assets.upperCorner,0,50,null);
     g.drawImage(Assets.rightCorner,50,50,null);
     g.drawImage(Assets.leftCorner,100,50,null);
     g.drawImage(Assets.downCorner,150,50,null);
     g.drawImage(Assets.updowntunnel,0,100,null);
     g.drawImage(Assets.rightleftTunnel,50,100,null);
     g.drawImage(Assets.boden,100,100,null);
     */
}
