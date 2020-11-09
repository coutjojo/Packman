package PackmanUi;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private Dimension size = new Dimension(1200,500);

    private final Canvas canvas = new Canvas();

    public Window() {
        this.setSize(size);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(canvas);
        canvas.setPreferredSize(size);
        this.pack();
        this.setVisible(true);
    }
    public Canvas getCanvas(){
        return canvas;
    }
}
