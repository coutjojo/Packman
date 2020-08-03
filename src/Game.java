import ImageLoad.Assets;
import ImageLoad.ImageLoader;
import PackmanUi.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game {
    private Window window;
    private Graphics g;
    private BufferStrategy bs;
    private boolean running = true;
    //////////////////////////////////////////////////////////////////////
    public Game(){
        window = new Window();
        gameLoop();
    }
    public void gameLoop(){
        init();
        while(running){
            tick();
            render();
        }
    }

    public void init() {
        Assets.init();
    }

    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.drawImage(Assets.packman,0,0,null);
        g.drawImage(Assets.geist1,50,0,null);
        g.drawImage(Assets.geist2,100,0,null);
        g.drawImage(Assets.wand,150,0,null);
        g.drawImage(Assets.boden,200,0,null);
        bs.show();
        g.dispose();
    }

    public void tick() {
    }
}
