package object;

import org.jbox2d.common.Vec2;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.World;
/**
 * A platform that moves horizontally and vertically, according to a rectangle trajectory
 */
public class MovingPlatform extends StaticObject implements SensorListener {

	//DIRECTION
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;

	public static final BoxShape movingPlatformShape = new BoxShape((float)(63.0/40.0),(float)(29.0/40.0));

	//direction in which the platform is heading to
	int state = 0;
	//speed
	float speed_x = 0.1f;
	float speed_y = 0.1f;
	//length of the current deplacement
	float lengthAct = 0f;
	//deltaX is the length of the width of the rectangle, deltaY is the length of its height
	float deltaX;
	float deltaY; 
	
	Sensor s;
	//Body that is standing on the platform
	Body bodyAbove;

	/**
	 * Constructor function
	 * @param w
	 * @param delta_x
	 * @param delta_y
	 */
	public MovingPlatform(World w, float delta_x, float delta_y) {
		super(w, movingPlatformShape);
		this.addImage(new BodyImage("resources/movingplatform.png",(float)(29.0/20.0)));
		this.deltaX = delta_x;
		this.deltaY = delta_y;
		s = new Sensor(this,movingPlatformShape);
		s.addSensorListener(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles the deplacement so that the trajectory is a rectangle
	 */
	@Override
	public void eventStep() {
		Vec2 deplacement = new Vec2(0,0);
		if (state==HORIZONTAL) {
			if (lengthAct>=Math.abs(deltaX)) {
				state=VERTICAL;
				speed_y*=-1;
				lengthAct=0;
			}
			else {
				deplacement = new Vec2(speed_x,0);
				lengthAct+=Math.abs(speed_x);
			}
		}
		
		if (state==VERTICAL){
			if (lengthAct>=Math.abs(deltaY)) {
				state=HORIZONTAL;
				speed_x*=-1;
				lengthAct=0;
			}
			else {
				deplacement = new Vec2(0,speed_y);
				lengthAct+=Math.abs(speed_y);
			}
		}
		this.move(deplacement);
		if (bodyAbove!=null) {
			//we move the body that is standing on the platform according to the trajectory
			bodyAbove.move(deplacement);
		}
		
	}
	/**
	 * Tells which body is above the platform
	 */
	@Override
	public void beginContact(SensorEvent arg0) {
		// TODO Auto-generated method stub
		bodyAbove = arg0.getContactBody();
	}
	@Override
	public void endContact(SensorEvent e) {
		// TODO Auto-generated method stub
		bodyAbove=null;
	}

	
	
}
