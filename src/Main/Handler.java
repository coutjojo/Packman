package Main;

import EntitySystem.Ghost;
import EntitySystem.Player;
import PackmanUi.Window;
import Worldmanager.WorldGenerator;

import javax.swing.*;


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
    public Player getPlayer() {return game.getGameState().getPlayer();}
    public Ghost[] getGhosts() {return game.getGameState().getGhosts();}
    public JPanel getWindowContent() {return game.getWindow().getContent();}
    public Window getWindow() {return game.getWindow();}
}
