package dynamicObject;

import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;
import other.Sound;
/**
 * EnemyFollower is an enemy that follows the player when the player is too close
 */
public class EnemyFollower extends Enemy {

    static Shape shapeEnemyFollower = new BoxShape(0.8f,0.8f);
    Player p;

    /**
     * Constructor method
     * @param w, the world in which the game takes place
     * @param player, the player
     */
    public EnemyFollower(World w, Player player) {
        super(w, shapeEnemyFollower);
        this.addImage(new BodyImage("resources/enemyfollower.gif",1.6f));
        p=player;
        this.getImages().get(0).flipHorizontal();
        TIMER_CHANGEMENT=140;
    }

    /**
     * Handles the timer of the action of the enemy. If the player is too close, the enemy will go to the direction
     * of the player, otherwise, it will periodically turn around
     */
    @Override
    public void eventStep() {
        //we set the angle to 0 so that the enemy doesn't turn
        this.setAngle(0);
        //if the player is close enough, the enemy follows him
        if (Math.abs(this.getPosition().x-p.getPosition().x)<=6f && Math.abs(this.getPosition().y-p.getPosition().y)<=5f) {
            if (this.getPosition().x>p.getPosition().x) {
                if (!sideMovement) {
                    action();
                }
            }
            else {
                if (sideMovement) {
                    action();
                }
            }
        }
        else {
            timer++;
            if (timer>=TIMER_CHANGEMENT) {
                timer=0;
                action();
            }
        }

        if (!sideMovement) {
            this.setLinearVelocity(new Vec2(5,0));
        }
        else {
            this.setLinearVelocity(new Vec2(-5,0));
        }

    }

    /**
     * The action of the enemy is turning around
     */
    @Override
    public void action() {
        this.turnAround();
    }

    @Override
    public void attacked() {
        this.destroy();
    }

}
