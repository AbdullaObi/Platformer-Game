package other;

import dynamicObject.Player;
import game.Camera;
import resources.TextSearch;

import java.io.File;

/**
 * The user of the game
 */
public class User {

	//name and data of the user
	public static String name = "";
	public static DataUser data;

	/**
	 * Save a new result of the player
	 * @param level, the level concerned
	 * @param time, the time in which the player passed the level
	 * @param score, the score the player has made
	 */
	public static void saveResult(int level, int time, int score) {
		data.appendData(level, time, score);
		//we save and load again the results
		TextSearch.appendLine("data.txt", name + ";" + level + ";" + time + ";" + score + ";");
		LeaderBoard.loadLeaderBoardData();
		data = LeaderBoard.map.get(name);
		data.sort();
	}

	public static void saveGame(int level, Player p, Camera c, int time, int score, File f) {
		TextSearch.appendLine(f.getPath(),"level;"+level+";");
		TextSearch.appendLine(f.getPath(),"score;"+score+";");
		TextSearch.appendLine(f.getPath(),"player;"+p.getPosition().x+";"+p.getPosition().y+";"+p.upgraded+";");
		TextSearch.appendLine(f.getPath(),"time;"+time+";");
		TextSearch.appendLine(f.getPath(),"camera;"+c.cameraPosition+";");
	}
	
}
