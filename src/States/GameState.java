package States;

import EntitySystem.Player;
import Main.Handler;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class GameState extends State {

    private final Player player;
    private WorldGenerator world;

    public GameState setWorld(WorldGenerator world) {
        this.world = world;
        return this;
    }

    public GameState(Handler handler){
        super(handler);
        world = new WorldGenerator("res/worlds/World1.txt",handler);
        player = new Player(handler, world.getSpawnX(),world.getSpawnY());

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


    //GETTER & SETTER
    public Player getPlayer() {
        return player;
    }

    public WorldGenerator getWorld() {
        return world;
    }
}
