package object;

import city.cs.engine.*;
import dynamicObject.Player;
import org.jbox2d.common.Vec2;
/**
 * Door is a door that need a key to be opened
 */
public class Door extends StaticObject implements CollisionListener {

	private World world;

	private StaticBody door;
	private static Shape shapeDoor = new BoxShape((float)(14.0/40.0),(float)(56.0/40.0));

	private boolean state = false;

	/**
	 * Constructor function
	 * @param w
	 * @param position
	 */
	public Door(World w, Vec2 position) {
		super(w, shapeDoor);
		this.destroy();
		world=w;
		door = new StaticBody(w,shapeDoor);
		door.setPosition(position);
		close();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This function opens the door so that the player can go through it
	 */
	public void open() {
		Vec2 position = door.getPosition();
		door.destroy();
		door = new StaticBody(world);
		GhostlyFixture gf = new GhostlyFixture(door,shapeDoor);
		door.addImage(new BodyImage("resources/doorAnim2.png",(float)(57.0/20.0)));
		door.setPosition(position);
		state=true;
	}

	/**
	 * This function closes the door so that the player can't go through it
	 */
	public void close() {
		Vec2 position = door.getPosition();
		door.destroy();
		door = new StaticBody(world,shapeDoor);
		door.addImage(new BodyImage("resources/doorAnim1.png",(float)(56.0/20.0)));
		door.setPosition(position);
		door.addCollisionListener(this);
		state=false;
	}

	@Override
	public void eventStep() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This function handles the collision with the player, so that when it collides with the player
	 * and the player has the key, the door opens
	 * @param e
	 */
	@Override
	public void collide(CollisionEvent e) {
		if (e.getOtherBody() instanceof Player && !state) {
			if (((Player)e.getOtherBody()).hasKey)
				open();
				((Player)e.getOtherBody()).hasKey=false;
		}
	}
}
