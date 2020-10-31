package States;

import Main.Handler;

import java.awt.*;

public abstract class State {
    private static  State currentState = null;
    protected final Handler handler;

    public static void setState (State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }

    public State(Handler handler) {
        this.handler = handler;
    }
    public State getCurrentState() {
        return currentState;
    }

    // CLASS
    public abstract void tick();

    public abstract void render(Graphics g);
}