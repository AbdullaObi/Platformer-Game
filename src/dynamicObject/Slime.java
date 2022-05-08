package dynamicObject;

import game.GameWorld;
import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.Shape;
import city.cs.engine.World;
import other.Sound;

/**
 * Slime, an enemy of the level 2
 */
public class Slime extends Enemy {
	private static final float size = 0.6f;
    private static final Shape enemyShape = new BoxShape(size,size);

	/**
	 * Constructor method
	 * @param w, the world in which the game takes place
	 */
	public Slime(World w) {
		super(w,enemyShape);
		this.addImage(new BodyImage("resources/slime.gif",1.2f));
		this.addCollisionListener(this);
	}

	/**
	 * Action of the slime when it's attacked
	 */
	public void attacked() {
		GameWorld.score+=5;
		this.destroy();	
	}

	/**
	 * Action of the slime (it turns around and does a small jump)
	 */
	@Override
	public void action() {
		this.setAngle(0);
		this.turnAround();
		this.applyImpulse(new Vec2(0,20f));
	}

	
}
