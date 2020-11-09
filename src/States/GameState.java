package States;

import EntitySystem.Ghost;
import EntitySystem.Player;
import Main.Handler;
import Worldmanager.WorldGenerator;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private WorldGenerator world;
    private Ghost[] ghosts;

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public boolean initState() {
        world = new WorldGenerator("res/worlds/World1.txt",handler);
        player = new Player(handler, world.getSpawnX(),world.getSpawnY(),3.0f);
        ghosts = new Ghost[world.getGhostCount()];
        ghosts[0] = new Ghost(handler, world.getGhostSpawnX(), world.getGhostSpawnY(),3.0f);
        return true;
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        for (Ghost ghost : ghosts) {
            ghost.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        for (Ghost ghost : ghosts) {
            ghost.render(g);
        }
    }


    //GETTER & SETTER
    public Player getPlayer() {
        return player;
    }
    public Ghost[] getGhosts() {
        return ghosts;
    }

    public WorldGenerator getWorld() {
        return world;
    }
}
