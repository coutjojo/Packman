package States;

import EntitySystem.Player;
import ImageLoad.Assets;

import java.awt.*;

public class GameState extends State {

    private final Player player;


    public GameState(){
        player = new Player(100,100);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.packman,0,0,null);
//        g.drawImage(Assets.ghost1,50,0,null);
//        g.drawImage(Assets.ghost2,100,0,null);
//        g.drawImage(Assets.wand,150,0,null);
       g.drawImage(Assets.boden,200,0,null);
//        player.render(g);
    }
}
