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
 * Class EnemyLevel2, an enemy with a spike such that the player can't jump on it
 */
public class EnemyLevel2 extends Enemy {

	private static final float size = 0.8f;
    private static final Shape enemyShape = new BoxShape(size,size);

	/**
	 * Constructor function of the class EnemyLevel2
	 * @param w
	 */
	public EnemyLevel2(World w) {
		super(w, enemyShape);
		this.addImage(new BodyImage("resources/enemyspike.gif",1.6f));
		this.getImages().get(0).flipHorizontal();
	}

	/**
	 * Reaction when the enemy is attacked
	 */
	@Override
	public void attacked() {
		GameWorld.score+=5;
		this.destroy();
	}

	/**
	 * The enemy turns around every time the action is enclenched
	 */
	@Override
	public void action() {
		sideMovement=!sideMovement;
		this.getImages().get(0).flipHorizontal();
	}
	/**
	 * This function handles the reactions when the player and the enemy collide and the player isn't attacking
	 * @param p, the player
	 * @param side, -1 if the side is right, 1 if the side is left, 0 if there is no side (so vertically)
	 */
	public void reactionCollisionPlayerNoAttack (Player p, int side) {
		if (side==0) {
			p.applyImpulse(new Vec2(0,150/8));
			p.attacked();
		}
		else {
			float vx = (p.getLinearVelocity().x);
			this.applyImpulse(new Vec2(-vx*5,0));
			p.applyImpulse(new Vec2(side*150/8,100/8));
			p.attacked();
		}
	}

}
