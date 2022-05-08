package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import frontend.Game;
import other.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * Menu of the game
 */
public class Menu implements MouseListener {
	//Possible states of the game
	public static int PLAY = 0;
	public static int PAUSE = 1;
	public static int MENU = 2;
	public static int LEADERBOARD = 3;
	public static int ENTER_NAME = 4;
	public static int LEVELS = 5;

	//State of the game
	public static int stateInGame = PLAY;
	public static boolean gameOver;
	public static boolean endGame;

	//mode fo the leaderBoard
	public static int modeLeaderBoard = 0;
	
	//Buttons position
	Rectangle buttonPlayRect = new Rectangle(786-50, 10, 40,40);
	Rectangle buttonMenuRect = new Rectangle(786-90, 10, 40,40);
	Rectangle buttonSave = new Rectangle(786-240, 16, 120,30);
	Rectangle buttonPlayMenu = new Rectangle(300, 148,240,60);
	Rectangle buttonLeaderBoard = new Rectangle(300, 228,240,60);
	Rectangle buttonLoadPreviousGame = new Rectangle(300, 308, 240,60);
	Rectangle buttonOk = new Rectangle(358, 292,129,60);
	Rectangle buttonUser = new Rectangle(306,117,83,40);
	Rectangle buttonGlobal = new Rectangle(399,112,83,40);
	Rectangle Level1Button = new Rectangle(300,141,240,60);
	Rectangle Level2Button = new Rectangle(300,221,240,60);
	Rectangle Level3Button = new Rectangle(300,301,240,60);
	Rectangle FileButton = new Rectangle(300,381,240,60);

	//state of the button
	public static boolean okPressed = false;
	public static boolean playPressed = false;
	public static boolean leaderBoardPressed = false;
	public static boolean level1Pressed = false;
	public static boolean level2Pressed = false;
	public static boolean level3Pressed = false;
	public static boolean filePressed = false;
	public static boolean buttonLoadPreviousGamepressed = false;

	//timer click
	private static int timer = -1;
	private static int timer_max = 3;

	//World
	static GameWorld w;
	//Box in which the text is displayed
	public static Display_text dt = new Display_text("");

	/**
	 * Constructor function of the Menu
	 * @param wo
	 */
	public Menu(GameWorld wo) {
		w=wo;
		playPauseGame();
		stateInGame = ENTER_NAME;
	}

	/**
	 * Function that is used to tell that the player has won
	 */
	public static void endGame() {
		playPauseGame();
		endGame=true;
		GameWorld.sound.setFile(Sound.WIN);
		GameWorld.sound.play();
	}

	/**
	 * Function that is used when the game is over
	 */
	public static void game_over() {
		playPauseGame();
		gameOver=true;
		GameWorld.sound.setFile(Sound.GAME_OVER);
		GameWorld.sound.play();
	}

	/**
	 * Function that is used the inverse the state of the game (when it's played or pause already)
	 */
	public static void playPauseGame() {
		stateInGame = 1-stateInGame;
		if (stateInGame == PLAY) {
			GameWorld.actualizeTime();
			GameWorld.sound.playCurrentLoop();
			w.start();
		}
		else {
			GameWorld.oldtime=GameWorld.time;
			GameWorld.sound.pauseCurrentLoop();
			w.stop();
		}
	}

	/**
	 * Function that is used to go to the menu
	 */
	public void goToMenu() {
		if (stateInGame==PLAY) {
			//If the game isn't paused, we pause the game
			playPauseGame();
		}
		stateInGame=MENU;
	}

	/**
	 * Function that is used to start the game while loading a level and to play
	 * @param numLevel
	 */
	public void startGame(int numLevel) {
		w.numLevel=numLevel;
		w.loadLevel(numLevel);
		stateInGame=PAUSE;
		playPauseGame();
	}

	/**
	 * Function that is used to load a level from the system file
	 * @param file
	 */
	public void startGame(String file) {
		w.numLevel=-1;
		w.loadLevel(file);
		stateInGame=PAUSE;
		playPauseGame();
	}

	/**
	 * Function that is used to load a level from the system file
	 * @param file
	 */
	public void startGameProgression(String file) {
		w.loadLevelT2(file);
		stateInGame=PAUSE;
		playPauseGame();
	}

	/**
	 * Function that is used to open the level menu
	 */
	public void openLevels() {
		stateInGame=LEVELS;
	}

	/**
	 * Function that is used to open the leaderboard
	 */
	public void openLeaderBoard() {
		LeaderBoard.loadLeaderBoardData();
		stateInGame = LEADERBOARD;
	}

	/**
	 * This function handles all the click events
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point actPoint = new Point(e.getPoint().x, e.getPoint().y-20);
		//We decrease the y position of the mouse by 20 because of the size of the window

		if ((stateInGame == PLAY || stateInGame == PAUSE)) {
			if (buttonPlayRect.contains(actPoint) && !gameOver && !endGame) {
				playPauseGame();
			}
			if (buttonMenuRect.contains(actPoint)) {
				if (w.numLevel!=-1)
					User.saveResult(w.numLevel,w.time,w.score);
				goToMenu();
			}
			else if (buttonSave.contains(actPoint)) {
				JFileChooser fc = new JFileChooser();
				int val_return = fc.showSaveDialog(null);
				if (val_return == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					User.saveGame(w.numLevel,w.player, w.camera, GameWorld.score, GameWorld.time, file);
				}
			}
			else {
				if (gameOver) {
					if (w.numLevel!=-1) {
						User.saveResult(w.numLevel,w.time,w.score);
						startGame(w.numLevel);
					}
					else {
						goToMenu();
					}
				}
				if (endGame) {
					if (w.numLevel!=-1)
						goToMenu();
				}
			}
		}
		else if (stateInGame == MENU) {
			if (buttonPlayMenu.contains(actPoint)) {
				openLevels();
			}
			if (buttonLeaderBoard.contains(actPoint)) {
				openLeaderBoard();
			}
			if (buttonLoadPreviousGame.contains(actPoint)) {
				JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int res = choose.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION && choose.getSelectedFile()!=null) {
					String file = choose.getSelectedFile().getAbsolutePath();
					this.startGameProgression(file);
				}
			}
		}
		else if (stateInGame == ENTER_NAME) {
			if (buttonOk.contains(actPoint)) {
				//We load the needed data in the leaderboard, using the name the player has entered
				User.name = dt.getValue();
				LeaderBoard.loadLeaderBoardData();
				if (LeaderBoard.map.containsKey(User.name)) {
					User.data=LeaderBoard.map.get(User.name);
				}
				else {
					User.data = new DataUser();
				}
				goToMenu();
			}
		}
		else if (stateInGame == LEADERBOARD ) {
			if (buttonUser.contains(actPoint)) {
				//change the mode of the leaderboard
				Menu.modeLeaderBoard=0;
			}
			if (buttonGlobal.contains(actPoint)) {
				//change the mode of the leaderboard
				Menu.modeLeaderBoard=1;
			}
			if (buttonMenuRect.contains(actPoint)) {
				//go to the menu
				goToMenu();
			}
		}
		else if (stateInGame == LEVELS) {
			//start a level if possible
			if (Level1Button.contains(actPoint)) {
				this.startGame(0);
			}
			if (Level2Button.contains(actPoint) && User.data.levelMax>=1) {
				this.startGame(1);
			}
			if (Level3Button.contains(actPoint) && User.data.levelMax>=2) {
				this.startGame(2);
			}
			if (FileButton.contains(actPoint)) {
				JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int res = choose.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION && choose.getSelectedFile()!=null) {
					String file = choose.getSelectedFile().getAbsolutePath();
					this.startGame(file);
				}
			}
		}

		gameOver=false;
		endGame=false;

	}

	/**
	 * handles the state of the buttons graphically
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point actPoint = new Point(e.getPoint().x, e.getPoint().y-20);
		if (stateInGame == ENTER_NAME) {
			if (buttonOk.contains(actPoint)) {
				okPressed=true;
			}
		}
		if (stateInGame == MENU) {
			if (buttonPlayMenu.contains(actPoint)) {
				playPressed=true;
			}
			if (buttonLeaderBoard.contains(actPoint)) {
				leaderBoardPressed=true;
			}
			if (buttonLoadPreviousGame.contains(actPoint)) {
				buttonLoadPreviousGamepressed=true;
			}
		}
		if (stateInGame == LEVELS) {
			if (Level1Button.contains(actPoint)) {
				level1Pressed=true;
			}
		}
		if (stateInGame == LEVELS) {
			if (Level1Button.contains(actPoint)) {
				level1Pressed=true;
			}
			if (Level2Button.contains(actPoint) && User.data.levelMax>=1) {
				level2Pressed=true;
			}
			if (Level3Button.contains(actPoint) && User.data.levelMax>=2) {
				level3Pressed=true;
			}
		}
	}

	/**
	 * Handles the states of the button graphically
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		okPressed=false;
		playPressed=false;
		leaderBoardPressed=false;
		level1Pressed=false;
		level2Pressed=false;
		level3Pressed=false;
		buttonLoadPreviousGamepressed=false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
