package Main;

import EntitySystem.Ghost;
import EntitySystem.Player;
import PackmanUi.Window;
import States.GameState;
import States.MenuState;
import States.State;
import Worldmanager.WorldGenerator;
import Input.MListener;

public class Handler {

    private Game game;

    public Handler(Game game) {
        this.game = game;
    }

    //GETTER & SETTER
    public Game getGame() {
        return game;
    }
    public WorldGenerator getWorld(){
        return game.getGameState().getWorld();
    }
    public Player getPlayer() {
        return game.getGameState().getPlayer();
    }
    public Ghost[] getGhosts() {
        return game.getGameState().getGhosts();
    }
    public Window getWindow() {
        return game.getWindow();
    }
    public MListener getmListener() {
        return game.getmListener();
    }
    public State getState() {
        return State.getState();
    }
    public GameState getGameState() {
        return game.getGameState();
    }
    public MenuState getMenuState() {
        return game.getMenuState();
    }
}
