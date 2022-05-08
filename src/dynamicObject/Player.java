package dynamicObject;
import frontend.Game;
import game.GameWorld;
import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;
import other.Sound;
/**
 * Class Player, the player that is moved by the user
 */
public class Player extends Movable{

	//CONSTANTS
	//timer
	private static final int TIMER_ATTACK_MAX = 15;
	private static final int TIMER_ATTACKED_MAX = 20;
	//appearance
	private static final float size_w = 0.5f;
	private static final float size_h = 1f;
    private static final Shape playerShape = new BoxShape(size_w,size_h);

	//Animations
    private static final int STANDING_ANIM = 0;
    private static final int RUN_ANIM = 1;
    private static final int JUMP_ANIM = 2;
    private static final int ATTACK_ANIM = 3;

	//tells if the player is attacking or not
    public boolean attacking=false;
	public int health = 3;
	//timer of the attacks and the attacked state (so that the player is not attacked continuously
    private int timer_attack = 0;
	private int timer_attacked = -1;
	//index of the anim
    private int anim = 0;
    private int last_move_anim = -1;
	//upgraded equals true if the player has the sword
    public boolean upgraded;
    public boolean hasKey = false;

	/**
	 * Constructor function
	 * @param world
	 */
	public Player(World world){
        super(world, playerShape);
        this.addImage(new BodyImage("resources/characterstanding.png",2f));
        x_right 		 = new Vec2(12/2,0);
        x_left 			 = new Vec2(-12/2,0);
        x_slow_right	 = new Vec2(2/2,0);
        x_slow_left		 = new Vec2(-2/2,0);
        impulse_jump	 = new Vec2(0,150/3);
        max_velocity	 = 10;
        side=true;
    }

	/**
	 * eventStep is a function that handles the basic behaviour of the player, like the animations and the attacks
	 */
	@Override
	public void eventStep() {
		// TODO Auto-generated method stub
		super.eventStep();
		//we set the angle to 0 so that the player doesn't turn
		this.setAngle(0);
		this.animation();
		if (attacking) {
			handle_attack();
		}
		if (timer_attacked>=0) {
			timer_attacked++;
			if (timer_attacked>=TIMER_ATTACKED_MAX) {
				timer_attacked=-1;
			}
		}
	}

	/**
	 * This function handle the animation of the player
	 */
	public void animation() {
		if (!attacking) {
			if (!jump) {
				//the player is either jumping or falling, we actualize the animation if needed
				//(if the player has turn or if the player wasn't jumping/falling before)
				if (((move!=last_move_anim)||this.anim!=JUMP_ANIM)) {
					anim=JUMP_ANIM;
					this.removeAllImages();
					if (!upgraded) {
						this.addImage(new BodyImage("resources/persojump.gif",2f));
					}
					else {
						this.addImage(new BodyImage("resources/persojumpsword.gif",2f));
					}
					if (!side) {
						this.getImages().get(0).flipHorizontal();
					}
					last_move_anim=move;
				}
			}
			else {
				//the player is standing put, we actualize the animation if needed
				if ((move==-1) && this.anim != STANDING_ANIM) {
					anim=STANDING_ANIM;
					this.removeAllImages();
					if (!upgraded) {
						this.addImage(new BodyImage("resources/characterstanding.png",2f));
					}
					else {
						this.addImage(new BodyImage("resources/characterstandingsword.png",2f));
					}
					if (!side) {
						this.getImages().get(0).flipHorizontal();
					}
					last_move_anim=move;
				}
				else if ((move!=-1) && ((move!=last_move_anim) || this.anim != RUN_ANIM)){
					//the player is running, we actualize the animation if needed
					anim=RUN_ANIM;
					this.removeAllImages();
					if (!upgraded) {
						this.addImage(new BodyImage("resources/persorun.gif",2f));
					}
					else {
						this.addImage(new BodyImage("resources/persorunsword.gif",2f));
					}

					if (!side) {
						this.getImages().get(0).flipHorizontal();
					}
					last_move_anim=move;
				}	
			}

		}
		else if (this.anim!=ATTACK_ANIM) {
			//the player is attacking, we actualize the animation and we put the attack animation
			anim=ATTACK_ANIM;
			this.removeAllImages();
			this.addImage(new BodyImage("resources/persoattack.gif",2f));
			
			if (!side) {
				this.getImages().get(0).flipHorizontal();
			}
			last_move_anim=move;
		}
	}

	/**
	 * We upgrade the player if he has grabbed the sword
	 */
	public void upgrade() {
		upgraded=true;
	}

	/**
	 * This function handles the attacks of the player so that he can't attacked continuously
	 */
	public void handle_attack() {
		timer_attack++;
		if (timer_attack>=TIMER_ATTACK_MAX) {
			timer_attack=0;
			attacking=false;
		}

	}

	/**
	 * the player attack with his sword
	 */
	public void attack() {
		if (upgraded && !attacking) {
			attacking=true;
			if (side) {
				this.applyImpulse(new Vec2(50,0));
			}
			else {
				this.applyImpulse(new Vec2(-50,0));
			}
			GameWorld.sound.setFile(Sound.DAMAGE);
			GameWorld.sound.play();
		}
	}

	/**
	 * This function handles the situation when the player is attacked
	 */
	public void attacked() {
		if (timer_attacked<0) {
			health--;
			timer_attacked=0;
		}
		GameWorld.sound.setFile(Sound.DAMAGE);
		GameWorld.sound.play();
	}
}
