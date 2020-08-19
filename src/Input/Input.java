package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    public static boolean UP = false;
    public static boolean DOWN = false;
    public static boolean RIGHT = false;
    public static boolean LEFT = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            resetUDRL();
            this.UP = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            resetUDRL();
            this.DOWN = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            resetUDRL();
            this.RIGHT = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            resetUDRL();
            this.LEFT = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.UP = false;
        this.DOWN = false;
        this.RIGHT = false;
        this.LEFT = false;
    }

    private void resetUDRL(){
        this.UP = false;
        this.DOWN = false;
        this.RIGHT = false;
        this.LEFT = false;
    }
}
