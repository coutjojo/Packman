package Main;

import ImageLoad.Assets;
import Input.Input;
import PackmanUi.Window;
import States.GameState;
import States.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
    private Window window;
    private Graphics g;
    private BufferStrategy bs;
    private boolean running = true;
    private State gameState;
    Handler handler;
    //////////////////////////////////////////////////////////////////////
    public Game(){
        window = new Window();
        handler = new Handler(this);
        gameLoop();

    }
    public void gameLoop(){
        init();
        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >=1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;

                //ÜBERPRÜFUNG
            }
        }
    }

    private void init(){
        gameState = new GameState(handler);
        State.setState(gameState);
        Assets.init();
        window.addKeyListener(new Input());

    }
    public void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(window.getCanvas().getBufferStrategy() == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0, window.getWidth(), window.getHeight());
        if(State.getState()!= null){
            State.getState().render(g);
        }
        bs.show();
        g.dispose();
    }

    public void tick() {
        if(State.getState()!= null){
            State.getState().tick();
        }

    }

    //GETTER & SETTER
    public Window getWindow() {
        return window;
    }
    public State getGameState() {
        return gameState;
    }
}
