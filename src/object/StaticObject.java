package object;

import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
/**
 * A StaticObject is a StaticBody which handles a particular event
 */
public abstract class StaticObject extends StaticBody {

	/**
	 * Constructor
	 * @param w
	 * @param s
	 */
	public StaticObject(World w, Shape s) {
		super(w,s);
	}

	/**
	 * An event that happens on each loop
	 */
	public abstract void eventStep();
}
