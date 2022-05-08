package resources;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * This class manages all the resources files
 */
public class Resources {

	public static Image[] background1 = new Image[3];
	public static Image[] background2 = new Image[3];
	public static Image[] background3 = new Image[3];
	public static Image[] background4 = new Image[3];
	public static Image backgroundLevel1;
	public static Image foregroundLevel1;
	public static Image backgroundLevel2;
	public static Image foregroundLevel2;
	public static Image backgroundLevel3;
	public static Image foregroundLevel3;
	public static Image[] gradient = new Image[3];
	public static Image sword;	
	public static Image heart;
	public static Image coin;
	public static Image pauseIcon;
	public static Image playIcon;
	public static Image menuIcon;
	public static Image darkforeground;
	public static Image backgroundmenu;
	public static Image playButton;
	public static Image playButton2;
	public static Image leaderBoardButton;
	public static Image leaderBoardButton2;
	public static Image nameBox;
	public static Image namebackground;
	public static Image okbutton;
	public static Image okbutton2;
	public static Image backgroundleaderboard;
	public static Image level1button;
	public static Image level1button2;
	public static Image level1buttonlocked;
	public static Image level2button;
	public static Image level2button2;
	public static Image level2buttonlocked;
	public static Image level3button;
	public static Image level3button2;
	public static Image level3buttonlocked;
	public static Image savebutton;
	public static Image loadPreviousGame;
	public static Image loadPreviousGame2;
	public static Image congratulations;
	public static Image gameOver;
	public static Image key;
	public static Image buttonFile;

	/**
	 * Load all the resources files
	 */
	public static void loadResources() {
		/*
		 * This function download all the image files used in the game in the functions GameView.paintBackground and GameView.paintForeground
		 */
		try {
			background1[0] = ImageIO.read(new File("resources/background.png"));
			background2[0] = ImageIO.read(new File("resources/background2.png"));
			background3[0] = ImageIO.read(new File("resources/background3.png"));
			background4[0] = ImageIO.read(new File("resources/background4.png"));
			background1[1] = ImageIO.read(new File("resources/background.png"));
			background2[1] = ImageIO.read(new File("resources/background2.png"));
			background3[1] = ImageIO.read(new File("resources/background3.png"));
			background4[1] = ImageIO.read(new File("resources/background4.png"));
			background1[2] = ImageIO.read(new File("resources/background5.png"));
			backgroundLevel1 = ImageIO.read(new File("resources/backgroundLevel.png"));
			foregroundLevel1 = ImageIO.read(new File("resources/foregroundLevel.png"));
			backgroundLevel2 = ImageIO.read(new File("resources/backgroundLevel2.png"));
			foregroundLevel2 = ImageIO.read(new File("resources/foregroundLevel2.png"));
			backgroundLevel3 = ImageIO.read(new File("resources/backgroundLevel3.png"));
			foregroundLevel3 = ImageIO.read(new File("resources/foregroundLevel3.png"));
			heart = ImageIO.read(new File("resources/heart.png"));
			coin = ImageIO.read(new File("resources/coin.png"));
			gradient[0] = ImageIO.read(new File("resources/gradient.png"));
			gradient[1] = ImageIO.read(new File("resources/gradient.png"));
			gradient[2] = ImageIO.read(new File("resources/gradient2.png"));
			sword = ImageIO.read(new File("resources/swordicone.png"));
			pauseIcon =  ImageIO.read(new File("resources/break_icon.png"));
			playIcon =  ImageIO.read(new File("resources/play_icon.png"));
			menuIcon =  ImageIO.read(new File("resources/menu_icon.png"));
			darkforeground =  ImageIO.read(new File("resources/darkforeground.png"));
			backgroundmenu = ImageIO.read(new File("resources/backgroundmenu.png"));
			playButton = ImageIO.read(new File("resources/play.png"));
			playButton2 = ImageIO.read(new File("resources/play2.png"));
			leaderBoardButton = ImageIO.read(new File("resources/leaderboard.png"));
			leaderBoardButton2 = ImageIO.read(new File("resources/leaderboard2.png"));
			nameBox = ImageIO.read(new File("resources/name_box.png"));
			namebackground = ImageIO.read(new File("resources/backgroundname.png"));
			okbutton = ImageIO.read(new File("resources/ok.png"));
			okbutton2 = ImageIO.read(new File("resources/ok2.png"));
			backgroundleaderboard = ImageIO.read(new File("resources/backgroundleadername.png"));
			level1button = ImageIO.read(new File("resources/level1.png"));
			level1button2 = ImageIO.read(new File("resources/level12.png"));
			level2button = ImageIO.read(new File("resources/level2.png"));
			level2button2 = ImageIO.read(new File("resources/level22.png"));
			level3button = ImageIO.read(new File("resources/level3.png"));
			level3button2 = ImageIO.read(new File("resources/level32.png"));
			level1buttonlocked = ImageIO.read(new File("resources/level1locked.png"));
			level2buttonlocked = ImageIO.read(new File("resources/level2locked.png"));
			level3buttonlocked = ImageIO.read(new File("resources/level3locked.png"));
			gameOver = ImageIO.read(new File("resources/gameOver.png"));
			congratulations = ImageIO.read(new File("resources/congratulations.png"));
			key = ImageIO.read(new File("resources/keyicon.png"));
			buttonFile = ImageIO.read(new File("resources/file.png"));
			savebutton = ImageIO.read(new File("resources/savebutton.png"));
			loadPreviousGame = ImageIO.read(new File("resources/loadpreviousgame.png"));
			loadPreviousGame2 = ImageIO.read(new File("resources/loadpreviousgame2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
