package object;

import city.cs.engine.*;
import dynamicObject.Player;
/**
 * Collectible is a static body which is such that all the other can go through it and that can be collected by
 * the player
 */

public abstract class Collectible extends StaticBody implements SensorListener {

    /**
     * Constructor function
     * @param w
     * @param shape
     */
    public Collectible(World w, Shape shape) {
        super(w);
        //We add a GhostlyFixture
        GhostlyFixture gf = new GhostlyFixture(this,shape);
        Sensor s = new Sensor(this, shape);
        s.addSensorListener(this);
    }

    /**
     * Function that is enclenched when there is a contact with an other body
     * @param e
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player) {
            action((Player)e.getContactBody());
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    /**
     * Action made when the player is in contact with the collectible
     * @param p
     */
    public abstract void action(Player p);
}
