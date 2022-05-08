package object;

import city.cs.engine.*;
import dynamicObject.Player;
import org.jbox2d.common.Vec2;
/**
 * ElectricPortal is an electric portal that is periodically turn on/off
 */
public class ElectricPortal extends StaticObject implements CollisionListener {

    //timers
    int TIMER_MAX = 50;
    int timer;
    //current state
    boolean state;
    //appearance;
    static Shape shapeElectricPortal = new BoxShape((float)(14.0/20.0),(float)(56.0/20.0));

    /**
     * Constructor function
     * @param w
     */
    public ElectricPortal(World w) {
        super(w,shapeElectricPortal);
        this.addCollisionListener(this)
        ;
    }

    /**
     * Make the portal electric
     */
    public void electric() {
        state=true;
        this.removeAllImages();
        this.addImage(new BodyImage("resources/electricportale2.png",(float)(56.0/20.0)));
        this.getFixtureList().get(0).destroy();
        SolidFixture f = new SolidFixture(this,shapeElectricPortal);
    }

    /**
     * Turn off the portal
     */
    public void normal() {
        state=false;
        this.removeAllImages();
        this.addImage(new BodyImage("resources/electricportale1.png",(float)(56.0/20.0)));
        this.getFixtureList().get(0).destroy();
        GhostlyFixture f = new GhostlyFixture(this,shapeElectricPortal);
    }

    /**
     * Handle the event related to the electric portal
     */
    @Override
    public void eventStep() {
        timer++;
        if (timer>=TIMER_MAX) {
            timer=0;
            if (!state) {
                electric();
            }
            else {
                normal();
            }
        }
    }

    /**
     * Handle the collision between the electric portal and the player. If the electric portal is turned on,
     * the player received damages and is repulsed.
     */
    @Override
    public void collide(CollisionEvent e) {
        if (state) {
            //if the portal is turned on and the object with which it collides is the player, the player
            //receives damages
            if (e.getOtherBody() instanceof Player) {
                if (e.getOtherBody().getLinearVelocity().x>0) {
                    ((Player) e.getOtherBody()).applyImpulse(new Vec2(-50,0));
                }
                else {
                    ((Player) e.getOtherBody()).applyImpulse(new Vec2(50,0));
                }
                ((Player)e.getOtherBody()).attacked();
            }
        }
    }

}
