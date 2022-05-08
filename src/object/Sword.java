package object;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import dynamicObject.Player;
import game.GameWorld;
import other.Sound;
/**
 * A sword is an object that can be picked by the player
 */
public class Sword extends StaticBody implements CollisionListener {
	static BoxShape swordShape = new BoxShape((float)(43/40.0),(float)(41/40.0));

	/**
	 * Constructor function
	 * @param w
	 */
	public Sword(World w) {
		super(w,swordShape);
		this.addImage(new BodyImage("resources/sword.png",(float)(83.0/20.0)));
		this.addCollisionListener(this);
	}

	/**
	 * Handles the collision between the player and the sword
	 * @param e
	 */
	@Override
	public void collide(CollisionEvent e) {
		// TODO Auto-generated method stub
		if (e.getOtherBody() instanceof Player) {
			Player p = (Player)e.getOtherBody();
			p.upgrade();
			this.destroy();
			GameWorld.sound.setFile(Sound.COIN);
			GameWorld.sound.play();
		}
	}
	
	

	
}
