package dynamicObject;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
/**
 * A Bullet is a Dynamic that goes in to a direction (left or right), and whose collision with the player
 * causes damages.
 */

public class Bullet extends DynamicBody implements CollisionListener {

    static Shape bulletShape = new BoxShape(0.2f,0.2f);

    /** Constructor function of the class bullet
        @param w, the world in which the game takes place
        @param position, the starting position of the bullet
        @param side, if side==true, the bullet goes to the right, otherwise it goes to the left
    */
    public Bullet(World w, Vec2 position, boolean side) {
        super(w, bulletShape);
        this.setPosition(position);
        this.addImage(new BodyImage("resources/bullet.png",0.4f));
        this.addCollisionListener(this);
        if (side) {
            this.setLinearVelocity(new Vec2(15,0));
        }
        else {
            this.setLinearVelocity(new Vec2(-15,0));
        }
        this.setGravityScale(0);
    }

    /** Handle the collision between the bullet and the other bodies
         @param e, the CollisionEvent
    */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player) {
            //if the other body is the player, the player receives damages and the bullet is destroyed
            ((Player)e.getOtherBody()).attacked();
            if (this.getLinearVelocity().x>0) {
                ((Player)e.getOtherBody()).applyImpulse(new Vec2(20,0));
            }
            else {
                ((Player)e.getOtherBody()).applyImpulse(new Vec2(-20,0));
            }
            this.destroy();
        }
        else if (!(e.getOtherBody() instanceof Shooter)) {
            //if the other body is not the shooter, we delete the bullet
            this.destroy();
        }

    }

}
