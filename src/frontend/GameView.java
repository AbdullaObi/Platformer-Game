package frontend;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.GameWorld;
import game.Level;
import game.Menu;
import other.LeaderBoard;
import other.User;
import city.cs.engine.UserView;
import resources.Resources;
/**
 * GameView, the GameView of the game
 */
public class GameView extends UserView {

	private static final long serialVersionUID = 1L;
	
	GameWorld w;
	public static Font fontGame = new Font("",Font.BOLD,20);

	/**
	 * Constructor function
	 * @param world
	 * @param width
	 * @param height
	 */
    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        w=world;
    }

	/**
	 * Draw the background according to which state the game is
	 * @param g
	 */
    @Override
    public void paintBackground(Graphics2D g) {
		//if the game state is the game (so play or pause), we draw the background and the level
    	if (Menu.stateInGame==Menu.PLAY || Menu.stateInGame == Menu.PAUSE) {
			if (w.numLevel!=-1) {
				g.drawImage(Resources.background1[w.numLevel], 0,0,null);
				g.drawImage(Resources.background2[w.numLevel], -(int)(w.camera.cameraPosition),0,null);
				g.drawImage(Resources.background3[w.numLevel], -(int)(w.camera.cameraPosition*2),0,null);
				g.drawImage(Resources.background4[w.numLevel], -(int)(w.camera.cameraPosition*3),0,null);
				g.drawImage(Level.backgroundLevels[w.numLevel], -(int)(w.camera.cameraPosition*20)-2,1,null);
			}
			else {
				g.drawImage(Resources.background1[0], 0,0,null);
				g.drawImage(Resources.background2[0], -(int)(w.camera.cameraPosition),0,null);
				g.drawImage(Resources.background3[0], -(int)(w.camera.cameraPosition*2),0,null);
				g.drawImage(Resources.background4[0], -(int)(w.camera.cameraPosition*3),0,null);

			}
    	}
    }
	/**
	 * Draw the foreground according to which state the game is
	 * @param g
	 */
    @Override
    public void paintForeground(Graphics2D g) {
    	if (Menu.stateInGame==Menu.PLAY || Menu.stateInGame == Menu.PAUSE) {
			//The player is playing to the game, the state is play or pause
			//we draw the foreground and the gradient
			if (w.numLevel!=-1) {
				g.drawImage(Level.foregroundLevels[w.numLevel], -(int)(w.camera.cameraPosition*20)-2,1,null);
				g.drawImage(Resources.gradient[w.numLevel], 0,0,null);
			}
			g.drawImage(Resources.savebutton, 786-240, 16, 120,30, null);
			if (Menu.stateInGame==Menu.PLAY) {
				//the player is playing
        		g.drawImage(Resources.pauseIcon, 786-50, 10, 40,40, null);       	
        	}
        	else {
				//there is a pause, we draw a blackforeground
        		if (!Menu.gameOver && !Menu.endGame) {
            		g.drawImage(Resources.darkforeground, 0, 0, 786, 600,null);
        		}
        		else if (Menu.gameOver) {
					//if it's game over, we draw the gameOver screen
        			g.drawImage(Resources.gameOver, 0, 0, 786, 600,null);
        		}
        		else {
					//if the player has win, we draw the congratulations screen
        			g.drawImage(Resources.congratulations, 0, 0, 786, 600,null);
        		}
        		g.drawImage(Resources.playIcon, 786-50, 10, 40,40, null);      
        	}
        	//we draw the health bar
        	for (int i = 0; i<w.player.health;i++) {
            	g.drawImage(Resources.heart, 10+35*i, 10, 20, 20, null);
        	}
			//we draw the coins and the score
        	g.drawImage(Resources.coin, 10, 40, 20,20, null);
			g.setColor(Color.black);
			g.setFont(fontGame);
			g.drawString(String.valueOf(w.score*5), 40,57);
			//we draw the icon menu
        	g.drawImage(Resources.menuIcon, 786-90, 10, 40,40, null);
        	if (w.player.upgraded) {
				//if the player has the sword, we draw a sword icon
        		g.drawImage(Resources.sword, 110, 6, 30,30, null);
        	}
			if (w.player.hasKey) {
				//if the player has the key, we draw a key icon
				g.drawImage(Resources.key, 140, 6, 30,30, null);
			}
			//we write how many time the player has spent in the level
        	g.drawString(String.valueOf(GameWorld.time) + "s", 786-130,38);
    	}   
    	else if (Menu.stateInGame == Menu.MENU){
    		drawMenu(g);
    	}
    	else if (Menu.stateInGame == Menu.ENTER_NAME) {
    		drawEnterName(g);    		
    	}
    	else if (Menu.stateInGame == Menu.LEADERBOARD) {
    		drawLeaderboard(g);
    	}
    	else {
    		drawLevels(g);
    	}
    }

	/**
	 * We draw the menu
	 * @param g
	 */
	public void drawMenu(Graphics2D g) {
		//we draw the backgrounds and the gradient
    	g.drawImage(Resources.background1[0], 0,0,null);
		g.drawImage(Resources.background2[0], 0,0,null);
		g.drawImage(Resources.background3[0], 0,0,null);
		g.drawImage(Resources.background4[0], 0,0,null);
		g.drawImage(Resources.backgroundmenu, 0, 0, null);
		g.drawImage(Resources.gradient[0], 0,0,null);
		//we draw the two buttons, according to their state (pressed or not)
		g.drawImage(!Menu.playPressed?Resources.playButton:Resources.playButton2, 300, 148,240,60, null);
		g.drawImage(!Menu.leaderBoardPressed?Resources.leaderBoardButton:Resources.leaderBoardButton2, 300, 228,240,60, null);
		g.drawImage(!Menu.buttonLoadPreviousGamepressed?Resources.loadPreviousGame:Resources.loadPreviousGame2, 300, 308,240,60, null);

    }

	/**
	 * We draw the EnterName menu
	 * @param g
	 */
    public void drawEnterName(Graphics2D g) {
		//backgrounds and gradient
    	g.drawImage(Resources.background1[0], 0,0,null);
		g.drawImage(Resources.background2[0], 0,0,null);
		g.drawImage(Resources.background3[0], 0,0,null);
		g.drawImage(Resources.background4[0], 0,0,null);
		g.drawImage(Resources.namebackground, 0, 0, null);
		g.drawImage(Resources.gradient[0], 0,0,null);
		//namebox
		g.drawImage(Resources.nameBox, 283,217, 240,60, null);
		//buttons
		g.drawImage(!Menu.okPressed?Resources.okbutton:Resources.okbutton2, 283,287, 240,60, null);
		//text the player has entered
		Menu.dt.draw(g);
    }

	/**We draw the leaderboard
	 * @param g
	 */
	public void drawLeaderboard(Graphics2D g) {
		//backgrounds and gradient
    	g.drawImage(Resources.background1[0], 0,0,null);
		g.drawImage(Resources.background2[0], 0,0,null);
		g.drawImage(Resources.background3[0], 0,0,null);
		g.drawImage(Resources.background4[0], 0,0,null);
		g.drawImage(Resources.backgroundleaderboard, 0, 0, null);
		g.drawImage(Resources.gradient[0], 0,0,null);
		//we draw the leaderboard according to its mode
		if (Menu.modeLeaderBoard==1) {
			LeaderBoard.drawLeaderBoardMode1(g);
		}
		else if (Menu.modeLeaderBoard==0) {
			LeaderBoard.drawLeaderBoardMode2(g, User.name);
		}
		//button to go back to the menu
		g.drawImage(Resources.menuIcon, 786-90, 10, 40,40, null);

    }

	/**
	 * We draw the level menu
	 * @param g
	 */
	public void drawLevels(Graphics2D g) {
		//backgrounds and gradient
    	g.drawImage(Resources.background1[0], 0,0,null);
		g.drawImage(Resources.background2[0], 0,0,null);
		g.drawImage(Resources.background3[0], 0,0,null);
		g.drawImage(Resources.background4[0], 0,0,null);
		g.drawImage(Resources.backgroundmenu, 0, 0, null);
		g.drawImage(Resources.gradient[0], 0,0,null);
		//buttons, if the player can't access to the level, we draw the button with a lock, otherwise it's
		//like a normal button
		g.drawImage(!Menu.level1Pressed?Resources.level1button:Resources.level1button2,300,141,240,60,null);
		if (User.data.levelMax>=1) {
			g.drawImage(!Menu.level2Pressed?Resources.level2button:Resources.level2button2,300,221,240,60,null);
		}
		else {
			g.drawImage(Resources.level2buttonlocked,300,221,240,60,null);
		}
		if (User.data.levelMax>=2) {
			g.drawImage(!Menu.level3Pressed?Resources.level3button:Resources.level3button2,300,301,240,60,null);
		}
		else {
			g.drawImage(Resources.level3buttonlocked,300,301,240,60,null);
		}
		g.drawImage(Resources.buttonFile,300,381,240,60,null);
    }
    
}