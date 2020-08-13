package PackmanUi;
import javax.swing.*;
import java.awt.*;
public class Window extends JFrame{
    private Canvas canvas = new Canvas();
    public Window() {
        this.setSize(new Dimension(1200, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(canvas);
        canvas.setPreferredSize(new Dimension(1200, 500));
        this.pack();

    }
    public Canvas getCanvas(){
        return canvas;
    }
}
