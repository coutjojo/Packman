package States;

import Componts.Button;
import EntitySystem.Ghost;
import EntitySystem.Player;
import ImageLoad.Assets;
import Main.Game;
import Main.Handler;
import Worldmanager.WorldGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GameState extends State implements ActionListener {

    private Player player;
    private WorldGenerator world;
    private Ghost[] ghosts;
    private boolean gameOver;

    //GameOverwWindow
    int windowWidth = 300;
    int windowHeight = 300;
    int windowX = handler.getWindow().getCanvas().getWidth() / 2 - windowWidth / 2;
    int windowY = handler.getWindow().getCanvas().getHeight() / 2 - windowHeight / 2;
    int buttonCount = 2;
    int buttonWidth = 100;
    int buttonHeight = 40;
    int buttonXInWindow = (windowWidth - buttonWidth) / 2;
    int buttondifYInWindow = (windowHeight / buttonCount) / 2;
    Button play;
    Button exit;

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public boolean initState() {
        world = new WorldGenerator("res/worlds/World1.txt",handler);
        player = new Player(handler, world.getSpawnX(),world.getSpawnY(),3.0f);
        ghosts = new Ghost[world.getGhostCount()];
        ghosts[0] = new Ghost(handler, world.getGhostSpawnX(), world.getGhostSpawnY(),3.0f);
        gameOver = false;
        initGameOverWindow();
        return true;
    }

    @Override
    public void tick() {
        if(!gameOver) {
            world.tick();
            player.tick();
            for (Ghost ghost : ghosts) {
                ghost.tick();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        for (Ghost ghost : ghosts) {
            ghost.render(g);

        }
        if(gameOver) {
            g.drawImage(Assets.gameOverWindow,windowX,windowY,null);
            play.render(g);
            exit.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play) {
            State.changeState(Game.gameState);
        } else  if(e.getSource() == exit) {
            State.changeState(Game.menuState);
        }
    }

    //mouse Inpus
    @Override
    public void mousePressed(MouseEvent e) {
        play.mousePressed(e);
        exit.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        play.mouseReleased(e);
        exit.mouseReleased(e);
    }

    /**
     * initialized the gameOverWindow
     */
    private void initGameOverWindow() {
        windowWidth = 300;
        windowHeight = 300;
        windowX = handler.getWindow().getCanvas().getWidth() / 2 - windowWidth / 2;
        windowY = handler.getWindow().getCanvas().getHeight() / 2 - windowHeight / 2;
        play = new Button(this,handler,"play",windowX + buttonXInWindow,windowY + buttondifYInWindow,buttonWidth,buttonHeight);
        exit = new Button(this,handler,"exit",windowX + buttonXInWindow,windowY + 2 * buttondifYInWindow,buttonWidth,buttonHeight);
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

    public void gameOver() {
        gameOver = true;
    }
}
