package object;

import org.jbox2d.common.Vec2;

import game.Camera;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import dynamicObject.Player;
/**
 * A platorm that falls when the player is standing on it
 */
public class FallingPlatform extends StaticObject implements CollisionListener  {

	//states
	static final int GHOST_STATE = 0;
	static final int PLATFORM_STATE = 1;
	int state = GHOST_STATE;

	//maximal time in which the player can stand on the falling platform
	public int TIMER_STANDING = 10;
	private int timer = -1;

	//tells if the platform is falling or not
	boolean falling = false;

	//camera of the game
	Camera camera;

	//position
	Vec2 position;
	World world;

	StaticBody fPlatform;
	static BoxShape shapeFallingPlatform = new BoxShape(0.5f,0.5f);

	//vector that makes fall the platform
	Vec2 vectorFall = new Vec2(0,-0.2f);
	Player perso;

	/**
	 * Constructor method
	 * @param w
	 * @param pos
	 * @param p
	 * @param c
	 */
	public FallingPlatform(World w, Vec2 pos, Player p, Camera c) {
		super(w, shapeFallingPlatform);
		System.out.println("a");
		this.destroy();
		position = pos;
		world = w;
		perso=p;	
		camera=c;
		makeGhostPlatform();
	}

	/**
	 * Transform the platform into a static platform with which the player can collide
	 */
	public void makeStaticPlatform() {
		
		state=PLATFORM_STATE;
		fPlatform = new StaticBody(world, shapeFallingPlatform);
		fPlatform.addImage(new BodyImage("resources/log.png",1f));
		fPlatform.addCollisionListener(this);
		fPlatform.setPosition(new Vec2(position.x-camera.cameraPosition, position.y));
	}

	/**
	 * Transform the platform into a ghost platform with which the player can't collide
	 */
	public void makeGhostPlatform() {
		state=GHOST_STATE;
		fPlatform = new StaticBody(world);
		GhostlyFixture gf = new GhostlyFixture(fPlatform,shapeFallingPlatform);
		fPlatform.addImage(new BodyImage("resources/log.png",1f));
		fPlatform.setPosition(new Vec2(position.x-camera.cameraPosition, position.y));
	}

	/**
	 * Make the platform fall
	 */
	public void fall() {
		makeGhostPlatform();
		falling=true;
	}

	/**
	 * Handle the falling event
	 */
	@Override
	public void eventStep() {
	
		if (falling) {
			//makes the platform fall
			fPlatform.move(vectorFall);
			if (fPlatform.getPosition().y<=-25f) {
				fPlatform.destroy();
				makeGhostPlatform();
				falling = false;
			}
		}
		else {
			//if the player is higher than the platform, this one became static, otherwise it's a ghost platform
			//so that the player can pass through it when he is not standing on it
			if (perso.getPosition().y-1.5>position.y && state != PLATFORM_STATE) {
				fPlatform.destroy();
				makeStaticPlatform();
			}
			else if (perso.getPosition().y-1.5<=position.y && state != GHOST_STATE) {
				fPlatform.destroy();
				makeGhostPlatform();
			}

		}

		//handle the destroying of the platform when it has fallen
		if (timer>=0) {
			timer++;
			if (timer>=TIMER_STANDING) {
				timer=-1;
				fPlatform.destroy();
				fall();
			}
		}
	}

	/**
	 * Handle the collision events with the player
	 * @param e
	 */
	@Override
	public void collide(CollisionEvent e) {
		//if the player collides with the platform, the timer of falling begins
		if (timer== -1 && e.getOtherBody() instanceof Player) {
			timer=0;
		}
	}
}
