package object;
import game.GameWorld;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import dynamicObject.Player;
import org.jbox2d.common.Vec2;
import other.Sound;

/**
 * A Coin is a object that is collectible and that make increase the score when collected
 */
public class Coin extends Collectible {

	private static final Shape shapeCoin = new BoxShape(0.3f,0.3f);		//Shape of the coin

	/**
	 * Constructor function
	 * @param w
	 */
	public Coin(World w) {
		super(w, shapeCoin);
		this.addImage(new BodyImage("resources/coin.png",0.6f));
	}

	/**
	 * Action that happens if the object is collected by the player
	 * @param p
	 */
	@Override
	public void action(Player p) {
		GameWorld.score++;
		this.destroy();
		GameWorld.sound.setFile(Sound.COIN);
		GameWorld.sound.play();
	}

	/**
	 * Function used to create a serie of coin more easily
	 * @param position
	 * @param shape
	 * @param number
	 * @param w
	 */
	public static void makeCoins(Vec2 position, Vec2 shape, int number, World w) {
		Coin c = new Coin(w);
		c.setPosition(new Vec2(position.x-0.3f-number,position.y+shape.y+0.4f));
	}
}
