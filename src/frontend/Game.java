package frontend;
import javax.swing.JFrame;

import game.GameWorld;
import game.Menu;
import resources.Resources;

/**
 * Main class
 */
public class Game {
    /**
     * Constructor function
     */
    public Game() {
        //Initialization of the world, the menu, the view and the jframe
        GameWorld world = new GameWorld();
        Menu menu = new Menu(world);
        GameView view = new GameView(world, 783, 561);
        JFrame frame = new JFrame("game");
        frame.addKeyListener(world);
        frame.addMouseListener(menu);
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        world.start();
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
    	Resources.loadResources();
        new Game();
    }

}
