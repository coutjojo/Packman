package States;

import EntitySystem.Player;
import ImageLoad.Assets;

import java.awt.*;

public class GameState extends State {

    private final Player player;


    public GameState(){
        player = new Player(20,20);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }
}
