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
 * EnemyLevel1 is a type of enemy that is displayed in the level 1
 */
public class EnemyLevel1 extends Enemy{

	private static final float size = 0.8f;
    private static final Shape enemyShape = new BoxShape(size,size);

	/**
	 * Constructor function
	 * @param w, World in which the game takes place
	 */
	public EnemyLevel1(World w) {
		super(w,enemyShape);
		this.addImage(new BodyImage("resources/enemylevel1.gif",1.6f));
		this.addCollisionListener(this);
	}

	/**
	 * Reaction of the enemy when it is attacked
	 */
	public void attacked() {
		GameWorld.score+=5;
		this.destroy();
	}

	/**
	 * Action of the enemy (it turns around)
	 */
	@Override
	public void action() {
		this.turnAround();
	}

}
