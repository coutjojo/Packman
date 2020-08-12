package States;

import EntitySystem.Player;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class GameState extends State {

    private final Player player;
    private WorldGenerator worldManager;

    public GameState(){
        player = new Player(20,20);
        worldManager = new WorldGenerator("");
    }

    @Override
    public void tick() {
        worldManager.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        worldManager.render(g);
        player.render(g);
    }
}
