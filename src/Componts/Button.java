package Componts;

import Main.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Button {

    //Position data
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    Handler handler;

    //graphic data
    private Color backgroundColor = Color.RED;
    private Color pressedColor = backgroundColor.darker();

    private boolean enabled;
    private boolean pressed;

    private String text;
    private Font font = new Font(null,Font.PLAIN,20);
    private ActionListener listener;

    public Button(ActionListener listener, Handler handler, String text, int x, int y, int width, int height) {
        this.listener = listener;
        this.handler = handler;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        enabled = true;
    }

    public void render(Graphics g) {
        if(pressed) {
            g.setColor(pressedColor);
        } else {
            g.setColor(backgroundColor);
        }

        if (enabled) {
            g.fillRect(x,y,width,height);
            g.setFont(font);
            g.setColor(Color.BLACK);
            int stringwidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text,(x + width / 2) - stringwidth / 2, y + (height / 2) + (font.getSize() / 3));
        }
    }

    private boolean isPressed(int x, int y) {
        return x >= this.x && x <= this.x + width
                && y >= this.y && y <= this.y + height;
    }

    public void mousePressed(MouseEvent e) {
        if(enabled && isPressed(e.getX(),e.getY())) {
            pressed = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if(enabled && pressed) {
            pressed = false;
            if(isPressed(e.getX(),e.getY())) {
                listener.actionPerformed(new ActionEvent(this, 0, "Buttonaction in the Menu"));
            }
        }
    }

    // GETTER && SETTER
    public boolean isEnabled() {
        return enabled;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public String getText() {
        return text;
    }
    public Font getFont() {
        return  font;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        pressedColor = backgroundColor.darker();
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setFont(Font font) {
        this.font = font;
    }
}
