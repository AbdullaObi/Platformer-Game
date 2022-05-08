package dynamicObject;
import game.GameWorld;
import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;
import other.Sound;
/**
 * Enemy is an abstract class from which all the enemies in the game inherit.
 */

public abstract class Enemy extends Movable implements CollisionListener{

    protected int TIMER_CHANGEMENT = 70;
    protected int timer = 0;
	protected int speed = 5;
	//side in which the enemy moves (true if right, false if left)
    protected boolean sideMovement = false;


	/**
	 * Constructor method
	 * @param w, the world in which the game takes place
	 * @param s, the shape of the enemy
	 */
	public Enemy(World w, Shape s) {
		super(w,s);
		this.addCollisionListener(this);
	}

	/**
	 * This function handles the way the enemy reacts when it is attacked
	 */
	public abstract void attacked();

	/**
	 * This function make the enemy do an action (like jumping for example)
	 */
	public abstract void action();

	/**
	 * This function keeps the speed of the enemy constant (according to x axis) and handles the action timing
	 * so that every TIMER_CHANGEMENT loop, the enemy does the action
	 */
	@Override
	public void eventStep() {
		timer++;
		if (timer>=TIMER_CHANGEMENT) {
			timer=0;
			action();
		}
		if (!sideMovement) {
			this.setLinearVelocity(new Vec2(speed,this.getLinearVelocity().y));
		}
		else {
			this.setLinearVelocity(new Vec2(-speed,this.getLinearVelocity().y));
		}
	}

	/**
	 * This function handles the basic reactions when the player and the enemy collide and the player isn't attacking
	 * @param p, the player
	 * @param side, -1 if the side is right, 1 if the side is left, 0 if there is no side (so vertically)
	 */
	public void reactionCollisionPlayerNoAttack (Player p, int side) {
		if (side==0) {
			this.attacked();
			p.applyImpulse(new Vec2(0,150/8));
			GameWorld.sound.setFile(Sound.BOUNCE);
			GameWorld.sound.play();
		}
		else {
			float vx = (p.getLinearVelocity().x);
			this.applyImpulse(new Vec2(-vx*5,0));
			p.applyImpulse(new Vec2(side*150/8,100/8));
			p.attacked();
		}
	}

	/**
	 * This function handles the basic reactions when the player and the enemy collide and the player is attacking
	 */
	public void reactionCollisionPlayerAttack () {
		this.attacked();
	}

	/**
	 * Function that makes the enemy turn around
	 */
	public void turnAround() {
		sideMovement=!sideMovement;
		this.getImages().get(0).flipHorizontal();
	}

	/**
	 * This function handles by default the collision between the player and the enemy
	 * @param e, the CollisionEvent of the collision
	 */
	@Override
	public void collide(CollisionEvent e) {
		if (e.getOtherBody().getClass().getName().equals(Player.class.getName())) {
			Player p =  (Player)e.getOtherBody();
			if (e.getNormal().x==-1) {
				if (!p.attacking) {
					reactionCollisionPlayerNoAttack(p,-1);
				}
				else {
					reactionCollisionPlayerAttack();
				}

			}
			else if (e.getNormal().x==1) {
				if (!p.attacking) {
					reactionCollisionPlayerNoAttack(p,1);
				}
				else {
					reactionCollisionPlayerAttack();
				}
			}
			else if (e.getNormal().y >= 0.8){
				if (!p.attacking) {
					reactionCollisionPlayerNoAttack(p,0);
				}
				else {
					reactionCollisionPlayerAttack();
				}
			}
			else {
				if (!p.attacking) {
					p.applyImpulse(new Vec2(-p.getLinearVelocity().x,150/8));
					p.attacked();
				}
				else {
					reactionCollisionPlayerAttack();
				}

			}

		}
	}

}
