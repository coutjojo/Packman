import PackmanUi.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

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
        while(running){
            tick();
            render();
        }
    }

    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.drawRect(30,30,100,100);
        bs.show();
        g.dispose();
    }

    public void tick() {
    }
}
