package PackmanUi;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    public static int width = 1200;
    public static int height = 500;

    private JPanel content;

    private Canvas canvas = new Canvas();

    public Window() {
        this.setSize(new Dimension(width,height));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        content = new JPanel(new GridLayout(2,2));
        content.add(canvas);
//        this.add(canvas);
        canvas.setPreferredSize(new Dimension(width,height));
        this.add(content);
        this.pack();
    }
    public Canvas getCanvas(){
        return canvas;
    }
    public JPanel getContent() {return content;}
}
