import PackmanUi.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
    private Window window;
    private Graphics g;
    private BufferStrategy bs;
    private boolean running = true;
    int x;
    //////////////////////////////////////////////////////////////////////
    public Game(){
        window = new Window();
        gameLoop();
    }
    public void gameLoop(){
        int fps = 80;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if(delta >=1) {
                tick();
                render();
                delta--;
            }
        }
    }

    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0,1200, 500);
        g.drawRect(x,30,100,100);
        bs.show();
        g.dispose();
    }

    public void tick() {
        x +=1;
    }
}
