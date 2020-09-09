package Main;

import Worldmanager.WorldGenerator;

public class Handler {

    private Game game;
    private WorldGenerator world;
    public Handler(Game game) {
        this.game = game;
    }

    //GETTER & SETTER
    public Game getGame() {
        return game;
    }

    public WorldGenerator getWorld(){return world;}
    public void setWorld(WorldGenerator world){this.world = world;}
}
