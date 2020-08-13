package States;

import Main.Handler;

import java.awt.*;

public abstract class State {
    private static  State currentState = null;
    private final Handler handler;

    public static void setState (State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }

    public State(Handler handler) {
        this.handler = handler;
    }
    // CLASS
    public abstract void tick();

    public abstract void render(Graphics g);
}