package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import object.StaticObject;
import other.LoadLevel;
import other.Sound;
import other.User;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamicObject.Movable;
import dynamicObject.Player;

/**
 * Class GameWorld
 */
public class GameWorld extends World implements KeyListener, StepListener {
	//controls
	private static boolean key_left = false;
	private static boolean key_right = false;
	private static boolean key_up = false;
	private static boolean key_space = false;

	//time when the level initially started
	public static long initial_time_level;

	//time since the level has started
	public static int oldtime;
	public static int time;
	public static int score;
	//Number of the current level
	public int numLevel = 1;
	//Sound
	public static Sound sound = new Sound();
	//Variables
	public Player player;
	public Camera camera;
	//List of the object
	public List<StaticObject> listStaticObject = new LinkedList<>();
	public List<Movable> listMovable = new LinkedList<>();

	/**
	 * Constructor function of GameWorld
	 */
    public GameWorld(){
        super();
		//Creation of the camera
        this.camera = new Camera();
		//We set the gravity to a higher level
        this.setGravity(75f);			
        this.addStepListener(this);
		//Initialisation of the sound
		sound.setFile(0);
		sound.play();
		sound.loop();
		//Loading the first level
        loadLevel(numLevel);

    }

	/**
	 * Load a level
	 * @param num, the number of the level
	 */
    public void loadLevel(int num) {
		//We clear the old parameters of the old level
		score=0;
    	listStaticObject.clear();
    	listMovable.clear();
		//We set the length of the level to its new value
    	camera.length=Level.LENGTH_LEVEL[num];

		//We destroy all the former bodies
    	for (StaticBody s : this.getStaticBodies()) {
    		s.destroy();
    	}
    	for (DynamicBody s : this.getDynamicBodies()) {
    		s.destroy();
    	}
		//We create a new player and a new camera
		player = new Player(this);
		player.setPosition(Level.STARTING_POSITION[0]);
		this.camera=new Camera();
		//We load the levek and add the player
    	Level.loadLevel(this, num);
		listMovable.add(player);
		//Initialisation of the time
		initial_time_level = (new Date()).getTime();
		oldtime = 0;
		time=0;

		//We launch the music
		GameWorld.sound.switchloop(numLevel);
	}

	/**
	 * Load a level from a file
	 * @param file, the name of the file
	 */
	public void loadLevel(String file) {
		//We clear the old parameters of the old level
		score=0;
		listStaticObject.clear();
		listMovable.clear();
		//We set the length of the level to its new value
		this.camera=new Camera();
		//We destroy all the former bodies
		for (StaticBody s : this.getStaticBodies()) {
			s.destroy();
		}
		for (DynamicBody s : this.getDynamicBodies()) {
			s.destroy();
		}
		//We create a new player and a new camera
		player = new Player(this);
		LoadLevel.loadLevel(this, file);
		player.setPosition(Level.startingPositionOpenLevel);
		camera.length = Level.lengthLevelOpenLevel;
		listMovable.add(player);
		//Initialisation of the time
		initial_time_level = (new Date()).getTime();
		//We launch the music
		GameWorld.sound.switchloop(0);
	}

	/**
	 * Load a level from a file
	 * @param file, the name of the file
	 */
	public void loadLevelT2(String file) {
		//We clear the old parameters of the old level
		score=0;
		listStaticObject.clear();
		listMovable.clear();
		//We set the length of the level to its new value
		this.camera=new Camera();
		//We destroy all the former bodies
		for (StaticBody s : this.getStaticBodies()) {
			s.destroy();
		}
		for (DynamicBody s : this.getDynamicBodies()) {
			s.destroy();
		}
		//We create a new player and a new camera
		player = new Player(this);
		LoadLevel.loadLevel(this, file);

	}

	/**
	 * This function check if there is a game over or not
	 */
	public void checkGameOver() {
		//if the health or the y position is too low, game over
    	if (player.getPosition().y<-20f || player.health<=0 && !Menu.gameOver) {
    		player.health=0;
    		Menu.game_over();
    	}
    }

	/**
	 * This function check if we have to change the level (if the player has gone to the end)
	 */
	public void check_changementLevel() {
    	if (camera.cameraPosition>=camera.length-786f/20f && player.getPosition().x>=786f/40f) {
    		//Changement of level, we save the result
			score+=100;
    		if (numLevel<Level.numberLv && numLevel!=-1) {
        		changeLevel();
    		}
    		else {
				//the player has finished the last level
    			Menu.endGame();
    		}

    	}
    }

	/**
	 * Changement of the level
	 */
	public void changeLevel() {
    	numLevel++;
    	loadLevel(numLevel);
    }

	/**
	 * When the player has done a pause, we change the value of the initial time so that the pause isn't counted in the time
	 */
	public static void actualizeTime() {
    	//actualize the time when the player does a pause
    	int timeBeforeBreak = time;
    	int timeAfterBreak = (int)((new Date()).getTime()-initial_time_level)/1000;
    	int delta = timeAfterBreak-timeBeforeBreak;
		initial_time_level+=delta*1000;
    }

	/**
	 * this function actualize the time
	 */
    public void loadTime() {
    	time=(int)((new Date()).getTime()-initial_time_level)/1000;
    }

	/**
	 * This function handles the typing
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		if (e.getKeyChar()=='f') {
			key_space=true;
		}
		if (Menu.stateInGame==Menu.ENTER_NAME) {
			//The player is entering its name
			if ((int)e.getKeyChar()==8) {
				try {
					//the player is removing the last character
					Menu.dt.setValue(Menu.dt.getValue().substring(0,Menu.dt.getValue().length()-1));
				}
				catch(Exception ex) {
					
				}
			}
			else
				//We actualize the value of the name in the menu
				Menu.dt.setValue(Menu.dt.getValue()+e.getKeyChar());
		}
	}

	/**
	 * Handle the pressing of any key
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 37) {
			key_left=true;
		}
		if (e.getKeyChar() == ' ') {
			key_up=true;
		}
		if (e.getKeyCode() == 39) {
			key_right=true;
		}
	}

	/**
	 * Handle the releasing of any key
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			key_left=false;
		}
		if (e.getKeyChar() == ' ') {
			key_up=false;
		}
		if (e.getKeyCode() == 39) {
			key_right=false;
		}
		
	}
	/**
	 * Main loop of the game, happens after each step
	 * @param e
	 */
	@Override
	public void postStep(StepEvent e) {
		//Handle the controls
		if (key_left) {
			player.move(Player.LEFT);
		}
		if (key_right) {
			player.move(Player.RIGHT);
		}
		if (key_up) {
			player.move(Player.UP);
		}
		if (!key_left && !key_right && !player.attacking) {
			player.move(Player.NO_DIRECTION);
		}
		if (key_space) {
			player.attack();
			key_space=false;
		}
		//Calculate the position of the camera
		camera.calculCameraPosition(player, this);
		//Actualize each staticObject/movable
		for (StaticObject sb : this.listStaticObject) {
			sb.eventStep();
		}
		for (Movable m : this.listMovable) {
			m.eventStep();
		}
		//Check if there is gameover or if we need to change the level
		check_changementLevel();
		checkGameOver();
		//Actualize the time
		loadTime();
	}

	@Override
	public void preStep(StepEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}