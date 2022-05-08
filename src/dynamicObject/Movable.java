package dynamicObject;
import org.jbox2d.common.Vec2;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;
/**
 * Class Movable, a dynamic body that can be deplaced by applying impulse
 */
public abstract class Movable extends DynamicBody implements CollisionListener{


	//possible direction
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int NO_DIRECTION = -1;

	//vector used to move
    protected Vec2 x_right;
    protected Vec2 x_left;
    protected Vec2 x_slow_right;
    protected Vec2 x_slow_left;
    protected Vec2 impulse_jump;

	//maximum velocity according to x direction
    protected int max_velocity;

	//jump equals true if the movable can jump
    protected boolean jump = false;
	//side equals true if the movable is moving to the right
    protected boolean side = false;
	//move is the last type of move the movable has made
    protected int move=-1;

	/**
	 * Constructor function
	 * @param w, the world in which the game takes place
	 * @param s, the shape of the movable object
	 */
	public Movable(World w, Shape s) {
		super(w, s);
		this.addCollisionListener(this);
	}

	/**
	 * Handles the collision with the ground
	 * @param e
	 */
	@Override
	public void collide(CollisionEvent e) {
		if (e.getNormal().y<=-0.4) {
			jump=true;
		}
	}

	/**
	 * Handle the possible type of move.
	 * @param direction
	 */
    public void move(int direction) {
    	switch(direction) {
    	case RIGHT:
    		this.side=true;			
    		move=0;
    		if (this.getLinearVelocity().x<=max_velocity) {	
    			this.applyImpulse(x_right);
    		}
    		break;
    	case LEFT:
    		this.side=false;
    		move=1;
    		if (this.getLinearVelocity().x>=-max_velocity) {	
    			this.applyImpulse(x_left);
    		}
    		break;
    	case UP:
    		if (jump && this.getLinearVelocity().y<=1) {
				//the movable can jump
    			this.applyImpulse(impulse_jump);
    			jump=false;
    		}
    		break;    	   
    	case NO_DIRECTION:
			//The movable was moving but doesn't move anymore, we slow it by using impulse
    		move=-1;
    		if (this.getLinearVelocity().x>=1) {
    			this.applyImpulse(x_slow_left);
    		}
    		else if (this.getLinearVelocity().x<=-1) {
    			this.applyImpulse(x_slow_right);
    		}
    		break;
    	}
    }

	/**
	 * eventStep() is a function that handles the basic behaviour of the movable object
	 */
	public void eventStep() {
    	if (Math.abs(this.getLinearVelocity().y)>1) {
    		jump=false;
    	}
    }

}
