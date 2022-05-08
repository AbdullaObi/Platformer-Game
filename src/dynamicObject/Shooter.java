package dynamicObject;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
/**
 * Shooter is an Enemy that shoots bullets
 */
public class Shooter extends Enemy {

    static Shape shooterShape = new BoxShape((float)(37.0/40.0),(float)(38.0/40.0));

    /**
     * Constructor method
     * @param w, world in which the game takes place
     */
    public Shooter(World w) {
        super(w, shooterShape);
        this.setGravityScale(1000);
        this.addImage(new BodyImage("resources/shooter.png",2f));
        speed=0;
    }

    /**
     * Handle the situation when the shooter is attacked (nothing happens)
     */
    @Override
    public void attacked() {
        //Nothing happens
    }

    /**
     * Action of the shooter (shooting bullet)
     */
    @Override
    public void action() {
        Bullet b = new Bullet(this.getWorld(), new Vec2(this.getPosition().x+1,this.getPosition().y), true);
    }
}
