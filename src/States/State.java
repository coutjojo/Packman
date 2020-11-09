package States;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class State {
    private static  State currentState = null;
    protected final Handler handler;
    private static boolean doneLoading = false;

    public static void setState (State state){
        currentState = state;
    }
    public static State getState(){
        return currentState;
    }
    public static void changeState(State state) {
        doneLoading = false;
        currentState = state;
        doneLoading = currentState.initState();
    }

    public State(Handler handler) {
        this.handler = handler;
    }
    public boolean isDoneLoading() {
        return doneLoading;
    }

    //MouseListener
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // CLASS
    public abstract boolean initState();

    public abstract void tick();

    public abstract void render(Graphics g);
}