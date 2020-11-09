package Input;

import java.awt.event.*;
import Main.Handler;

public class MListener implements MouseListener {

    private Handler handler;

    public MListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        handler.getState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        handler.getState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        handler.getState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("enter");
        handler.getState().mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit");
        handler.getState().mouseExited(e);
    }
}