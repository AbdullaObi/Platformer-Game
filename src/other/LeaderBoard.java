package other;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
import java.util.Map.Entry;

import frontend.GameView;

import other.DataUser.LevelData;
import resources.TextSearch;
/**
 * Leaderboard of the game, generated thanks to .txt file
 */
public class LeaderBoard {
	public static Map<String,DataUser> map = new HashMap<String,DataUser>(); 
	static SortedMap<Integer, DataUser> sortedUser = new TreeMap<Integer,DataUser>();

	/**
	 * Load the datas
	 */
	public static void loadLeaderBoardData() {
		//we clear the old datas
		map.clear();
		sortedUser.clear();
		//we read the file
		List<String> list = TextSearch.ReadTextLevel("data.txt");
		//we get the arguments
		for (String str : list) {
			try {
				List<String> args = TextSearch.AnalyseLine(str);
				String name = args.get(0);
				String level = args.get(1);
				String time = args.get(2);
				String score = args.get(3);
				if (map.containsKey(name)) {
					//we add the datas to the name as it is contained in the map
					map.get(name).appendData(Integer.valueOf(level), Integer.valueOf(time), Integer.valueOf(score));
				}
				else {
					//we add the data and the name since it is not contained in the map
					DataUser dtu = new DataUser();
					dtu.nameUser = name;
					dtu.appendData(Integer.valueOf(level), Integer.valueOf(time), Integer.valueOf(score));
					map.put(name,dtu);
				}
			}
			catch (Exception e) {

			}

		}
		int length = map.size()+1;
		int i = 0;
		for (Entry<String,DataUser> nameUser : map.entrySet()) {
			//We sort all the data
			i++;
			nameUser.getValue().sort();
			sortedUser.put(length*nameUser.getValue().scoreMax+i, nameUser.getValue());
		}
	}

	/**
	 * We draw the leaderboard according to mode 1 (so the date related to the player and its best score
	 * @param g
	 */
	public static void drawLeaderBoardMode1(Graphics2D g) {
		int line = 0;
		int px = 210;
		int py = 230;
		List<DataUser> listeDataUser = new LinkedList<>();
		for (Entry<Integer, DataUser> entry : sortedUser.entrySet()) {
			listeDataUser.add(entry.getValue());
		}

		for (int i = listeDataUser.size()-1; i>=0;i--) {
			DataUser entry = listeDataUser.get(i);
			line++;
			if (line<6) {
				g.setFont(GameView.fontGame);
				g.setColor(Color.white);
				g.drawString(entry.nameUser, px, py+line*30);
				g.drawString(String.valueOf(entry.sortedData.get(entry.sortedData.lastKey()).score), px+100, py+line*30);
				g.drawString(String.valueOf(entry.sortedData.get(entry.sortedData.lastKey()).time), px+200, py+line*30);
				g.drawString(String.valueOf(entry.sortedData.get(entry.sortedData.lastKey()).num+1), px+300, py+line*30);

			}
		}
	}
	/**
	 * We draw the leaderboard according to mode 2 (so all the best score of all the players)
	 * @param g
	 */
	public static void drawLeaderBoardMode2(Graphics2D g, String userAct) {
		int line = 0;
		int px = 210;
		int py = 260;
		if (map.containsKey(userAct)) {
			List<LevelData> listeDataUser = new LinkedList<>();
			for (Entry<Integer, LevelData> entry : map.get(userAct).sortedData.entrySet()) {
				listeDataUser.add(entry.getValue());

			}
			for (int i = listeDataUser.size()-1; i>=0;i--) {
				if (line<6) {
					LevelData entry = listeDataUser.get(i);
					g.setFont(GameView.fontGame);
					g.setColor(Color.white);
					g.drawString(userAct, px, py+line*30);
					g.drawString(String.valueOf(entry.score), px+100, py+line*30);
					g.drawString(String.valueOf(entry.time), px+200, py+line*30);
					g.drawString(String.valueOf(entry.num+1), px+300, py+line*30);
				}
				line++;
			}
		}
		
	}
	
}
