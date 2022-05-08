package object;

import org.jbox2d.common.Vec2;

import game.Camera;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
/**
 * DisappearingPlatform is a platform that periodically disappears
 */

public class DisappearingPlatform extends StaticObject {

	//possible states
	static final int GHOST_STATE = 0;
	static final int PLATFORM_STATE = 1;

	//Timer of apparition/disparition
	public int TIMER = 70;
	private int timer = 0;

	private int state = GHOST_STATE;

	private World world;
	private Camera camera;

	private StaticBody fPlatform;

	private Vec2 position;

	private static BoxShape shapeDisappearingPlatform = new BoxShape(0.8f,0.5f);

	/**
	 * Constructor method
	 * @param w
	 * @param pos
	 * @param c
	 */
	public DisappearingPlatform(World w, Vec2 pos, Camera c) {
		super(w, shapeDisappearingPlatform);
		this.destroy();
		position = pos;
		world = w;
		camera = c;
		fPlatform = new StaticBody(w);
		makeGhostPlatform();
	}

	/**
	 * Create a static platform at the position of the DisappearingPlatform
	 */
	public void makeStaticPlatform() {
		state=PLATFORM_STATE;
		fPlatform.destroy();
		fPlatform = new StaticBody(world, shapeDisappearingPlatform);
		fPlatform.addImage(new BodyImage("resources/disappearingplatform.png",1f));
		fPlatform.setPosition(new Vec2(position.x-camera.cameraPosition, position.y));
	}

	/**
	 * Remove the static platform
	 */
	public void makeGhostPlatform() {
		state=GHOST_STATE;
		fPlatform.destroy();
	}

	/**
	 * Function that handles the changement of state of the disappearing platform
	 */
	@Override
	public void eventStep() {
		timer++;

		if (timer>=TIMER) {
			timer=0;
			if (state == GHOST_STATE) {
				makeStaticPlatform();
			}
			else {
				makeGhostPlatform();

			}
		}
		
	}
}
