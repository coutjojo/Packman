package States;

import EntitySystem.Ghost;
import EntitySystem.Player;
import Main.Handler;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private WorldGenerator world;
    private Ghost ghost;

    public GameState setWorld(WorldGenerator world) {
        this.world = world;
        return this;
    }

    public GameState(Handler handler){
        super(handler);
        world = new WorldGenerator("res/worlds/World1.txt",handler);
        player = new Player(handler, world.getSpawnX(),world.getSpawnY());
        ghost = new Ghost(handler,400,400);

    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        ghost.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        ghost.render(g);
    }


    //GETTER & SETTER
    public Player getPlayer() {
        return player;
    }

    public WorldGenerator getWorld() {
        return world;
    }
}
