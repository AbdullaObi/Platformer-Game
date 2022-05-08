package dynamicObject;

import city.cs.engine.*;
import game.GameWorld;
import org.jbox2d.common.Vec2;
import other.Sound;
/**
 * Class EnemyMettalic, a big enemy that can't be destroyed
 */
public class EnemyMettalic extends Enemy {

    private static final float size = 1.5f;
    private static final Shape enemyShape = new BoxShape(size,size);

    public EnemyMettalic(World w) {
        super(w,enemyShape);
        this.removeAllImages();
        this.getFixtureList().clear();
        SolidFixture f = new SolidFixture(this,enemyShape);
        this.addImage(new BodyImage("resources/enemyMettalic.gif",3f));
        speed=2;
        this.TIMER_CHANGEMENT = 200;
    }

    /**
     * Reaction when the enemy is attacked
     */
    @Override
    public void attacked() {
        //Nothing happens because this enemy can not be destroyed
    }

    /**
     * The enemy turns around every time the action is enclenched
     */
    @Override
    public void action() {
        this.turnAround();
    }

}
