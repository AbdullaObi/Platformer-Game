package other;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
/**
 * DataUser is the date of a user of the game
 */
public class DataUser {

	//name of the user
	String nameUser;
	//list of the level date
	List<LevelData> listLevelData = new LinkedList<>();
	//map sorted of the level data
	SortedMap<Integer, LevelData> sortedData = new TreeMap<>();

	//maximum level that the player can play to
	public int levelMax=0;
	//maximal score that the player has achieved on a level
	int scoreMax=0;

	/**
	 * Append a data to the list of the data of the user
	 * @param num, number of the level
	 * @param time, time in which the player has done the level
	 * @param score, score of the player
	 */
	public void appendData(int num, int time, int score) {
		LevelData lv = new LevelData(num,time,score);
		listLevelData.add(lv);
	}

	/**
	 * Sort the data of the user by score
	 */
	public void sort() {
		sortedData.clear();
		levelMax=0;
		scoreMax = 0;
		int i = 0;
		for (LevelData dt : listLevelData) {
			i++;
			if (dt.num>levelMax) {
				levelMax=dt.num;
			}
			int scoreTotal = dt.calculateScoreTotal();
			if (scoreTotal>scoreMax) {
				scoreMax=scoreTotal;
			}
			int l = listLevelData.size()+1;
			sortedData.put(l*scoreTotal+i, dt);
		}
		levelMax+=1;
	}

	/**
	 * LevelData is a class that contains data concerning a play
	 */
	static class LevelData {
		int num;
		int time;
		int score;

		/**
		 * Constructor function
		 * @param _num number of the level
		 * @param _time time in which the player has done the level
		 * @param _score score of the player
		 */
		public LevelData(int _num, int _time, int _score) {
			num=_num;
			time=_time;
			score=_score;
		}

		//Calculate the score of the player, using the score and the time
		public int calculateScoreTotal() {
			return (60-time)+score;
		}
		
	}
}
