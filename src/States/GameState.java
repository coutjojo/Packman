package States;

import EntitySystem.Player;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class GameState extends State {

    private final Player player;
    private WorldGenerator world;

    public GameState(){
        world = new WorldGenerator("res/worlds/World1.txt");
        player = new Player(20,20);

    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
