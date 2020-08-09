package States;

import ImageLoad.Assets;

import java.awt.*;

public class GameState extends State {
    public GameState(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.packman,0,0,null);
        g.drawImage(Assets.ghost1,50,0,null);
        g.drawImage(Assets.ghost2,100,0,null);
        g.drawImage(Assets.wand,150,0,null);
        g.drawImage(Assets.boden,200,0,null);
    }
}
