package States;

import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import PackmanUi.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.PhantomReference;

public class MenuState extends State{

    private int ButtonWidth = 50;
    private int ButtonHeight = 10;
    private int ButtonCount = 3;
    private int ButtonPosY = (int) (Window.height / 0.75);

    private Button play;
    private Button option;
    private Button exit;

    private ActListener actListener = new ActListener();

    private int differencX = (Window.width - (ButtonCount * ButtonWidth)) / (ButtonCount - 1);

    private String exitMessage = "Do you want to exit the Game";
    private String dontExitMessage = "Welcome back!!";

    public MenuState(Handler handler) {
        super(handler);
        initButton();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawString("Packmann",Window.width / 2,Window.height / 4);
    }

    private void initButton() {
        int differencX = (Window.width - (ButtonCount * ButtonWidth)) / (ButtonCount - 1);
        // Play
        play = new Button("Play");
        play.setBounds(differencX,ButtonPosY,ButtonWidth,ButtonHeight);
        play.addActionListener(actListener);
        handler.getWindowContent().add(play);
//        // Option
//        option = new Button("Option");
//        option.setBounds(differencX * 2 + ButtonWidth,ButtonPosY,ButtonWidth,ButtonHeight);
//        option.addActionListener(actListener);
//        // Exit
//        exit = new Button("Exit");
//        exit.setBounds(differencX * 3 + ButtonWidth * 2,ButtonPosY,ButtonWidth,ButtonHeight);
//        exit.addActionListener(actListener);

        System.out.println("initButton finished");
    }

    private class ActListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == play) {
                handler.getWindowContent().remove(play);
                handler.getWindow().setVisible(true);
                State.setState(Game.gameState);
            }
//            } else if(e.getSource() == option) {
//                System.out.println("In progress");
//            } else if(e.getSource() == exit) {
//                int answer = JOptionPane.showConfirmDialog(null,exitMessage);
//                if(answer == 0) {
//                    System.exit(1);
//                } else {
//                    JOptionPane.showMessageDialog(null,dontExitMessage);
//                }
//            }
        }
    }
}
