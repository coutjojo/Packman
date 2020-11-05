package Input;

import java.awt.*;
import java.awt.event.*;

public class MListener implements MouseListener {

    private static Point point = null;
    private static boolean clickOnPosB = false;
    public static boolean clickOnPos(int x,int y) {
        point = new Point(x,y);
        return clickOnPosB;
    }
    public static boolean clickOnPos() {
        return clickOnPosB;
    }
//    public static boolean clickOnArea(int x,int y,int width,int height) {
//        point =
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        System.out.println(e.getPoint().toString());
        point = new Point(e.getX(),e.getY());
        if(e.getPoint() == point) {
            clickOnPosB = true;
        } else {
            clickOnPosB = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit");
    }
}