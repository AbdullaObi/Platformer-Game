package object;

import city.cs.engine.*;
import dynamicObject.Player;
import game.GameWorld;
import other.Sound;
/**
 * A Key is a collectible object that can be picked by the player
 */
public class Key extends Collectible {

	static BoxShape keyShape = new BoxShape((float)(43/40.0),(float)(41/40.0));

	/**
	 * Constructor method
	 * @param w
	 */
	public Key(World w) {
		super(w,keyShape);
		this.addImage(new BodyImage("resources/key.png",(float)(83.0/20.0)));
	}

	/**
	 * When the key is collected by the player, we play a sound and we actualize the state of the player
	 * @param p
	 */
	@Override
	public void action(Player p) {
		p.hasKey=true;
		this.destroy();
		GameWorld.sound.setFile(Sound.COIN);
		GameWorld.sound.play();
	}
}
