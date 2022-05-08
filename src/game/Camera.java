package game;
import org.jbox2d.common.Vec2;

import city.cs.engine.Body;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
/**
 * Camera of the game, handles the scrolling
 */
public class Camera {

	public float cameraPosition =0f;
	public float wantedPositionCharacter = 0f;
	public float length = 100f;

	/**
	 * Center the camera on a body
	 * @param bodyCentered Body on which the camera is centred
	 * @param w
	 */
	public void calculCameraPosition(Body bodyCentered, World w) {
		float xBodyCentered = bodyCentered.getPosition().x;
		float deltaX = wantedPositionCharacter-xBodyCentered;
		//we decide the new position of the camera, according to the body and to the fact that the user
		//can't see the border of the screen
		if (cameraPosition-deltaX<0) {
			deltaX=cameraPosition;
		}
		if (cameraPosition-deltaX>length-786f/20f) {
			deltaX=cameraPosition-length+786f/20f;
		}
		
		cameraPosition-=deltaX;
		//we decrease the position of each body by deltaX
		for (StaticBody sb : w.getStaticBodies()) {
			sb.move(new Vec2(deltaX,0));
		}
		for (DynamicBody sb : w.getDynamicBodies()) {
			sb.move(new Vec2(deltaX,0));
		}
		
	}
}
