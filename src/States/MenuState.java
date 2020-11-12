package States;

import Componts.Button;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MenuState extends State implements ActionListener {

    private int ButtonWidth = 200;
    private int ButtonHeight = 50;
    private int ButtonCount = 3;
    private int ButtonPosX = (handler.getWindow().getCanvas().getSize().width / 2) - (ButtonWidth / 2);
    private int ButtondifferenceY = ((handler.getWindow().getCanvas().getSize().height / 2) / ButtonCount);

    // Buttons
    private Button play;
    private Button option;
    private Button exit;

    // messages
    private String titel = "Packmann";
    private String exitMessage = "Do you want to exit the Game!";

    private Font menuTitelFont = new Font(Font.MONOSPACED,Font.BOLD,100);
    private Font menuButtonFont = new Font(Font.MONOSPACED,Font.BOLD,30);

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public boolean initState() {
        initButton();
        return true;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        //background
        g.drawImage(Assets.menuBackground,0,0,null);

        //titel
        g.setColor(Color.BLACK);
        g.setFont(menuTitelFont);
        int stringwidth = g.getFontMetrics().stringWidth(titel);
        g.drawString(titel,handler.getWindow().getCanvas().getSize().width / 2 - stringwidth / 2,handler.getWindow().getCanvas().getSize().height / 4);

        //button
        play.render(g);
        option.render(g);
        exit.render(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Performed");
        if(e.getSource() == play) {
//            System.out.println("[Button] play");
            State.changeState(Game.gameState);
        } else if(e.getSource() == option) {
//            System.out.println("[Button] option");
        } else if(e.getSource() == exit) {
//            System.out.println("[Button] exit");
            int answer = JOptionPane.showOptionDialog(null, exitMessage, "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if(answer == JOptionPane.YES_OPTION) {
                System.exit(1);
            }
        }
    }

    // mouse input handeling
    @Override
    public void mousePressed(MouseEvent e) {
        play.mousePressed(e);
        option.mousePressed(e);
        exit.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        play.mouseReleased(e);
        option.mouseReleased(e);
        exit.mouseReleased(e);
    }

    private void initButton() {
        // Play
        play = new Button(this,handler,"Play",ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY * 3,ButtonWidth,ButtonHeight);
        play.setFont(menuButtonFont);
        // Option
        option = new Button(this,handler,"Option",ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY * 2,ButtonWidth,ButtonHeight);
        option.setFont(menuButtonFont);
        // Exit
        //TODO
        exit = new Button(this,handler,"Exit",ButtonPosX,handler.getWindow().getCanvas().getSize().height - ButtondifferenceY,ButtonWidth,ButtonHeight);
        exit.setFont(menuButtonFont);
//        System.out.println("initButton finished");
    }
}
