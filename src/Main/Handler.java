package Main;

import Worldmanager.WorldGenerator;

public class Handler {

    private Game game;

    public Handler(Game game) {
        this.game = game;
    }

    //GETTER & SETTER
    public Game getGame() {
        return game;
    }

    public WorldGenerator getWorld(){return game.getGameState().getWorld();}
}
