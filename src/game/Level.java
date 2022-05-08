package game;

import java.awt.Image;

import dynamicObject.*;
import object.*;
import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import resources.Resources;
/**
 * This class is used to load each level
 */
public class Level {

	//Characteristics and values related to each level
	public final static float[] LENGTH_LEVEL = {100f, 100f,100f};
	public final static Vec2[] STARTING_POSITION = {new Vec2(-16,-8),new Vec2(-16,-8), new Vec2(-16,-8)};
	public final static Image[] backgroundLevels = {Resources.backgroundLevel1,Resources.backgroundLevel2, Resources.backgroundLevel3};
	public final static Image[] foregroundLevels = {Resources.foregroundLevel1, Resources.foregroundLevel2, Resources.foregroundLevel3};
	public static float lengthLevelOpenLevel;
	public static Vec2 startingPositionOpenLevel;

	//total number of level
	public final static int numberLv = 2;

	/**
	 * Load a level
	 * @param w, a GameWorld
	 * @param num, the number of the level we want to load
	 */
	public static void loadLevel(GameWorld w, int num) {
		switch (num) {
		case 0:
			loadLevel1(w);
			break;
		case 1 :
			loadLevel2(w);
			break;
		case 2:
			loadLevel3(w);
			break;
		}
	}

	/**
	 * Load level 1
	 * @param w, the world
	 */
	public static void loadLevel1(GameWorld w) {
		//Load the border
		Shape borderShape = new BoxShape(1f,60f);
		StaticBody border_left = new StaticBody(w,borderShape);
		border_left.setPosition(new Vec2(-21f,0f));

		//Load the enemy
		Enemy enemy = new EnemyLevel1(w);
		enemy.setPosition(new Vec2((float)(502.0/20.0), (float)(-81.5/20.0f)));
		w.listMovable.add(enemy);

		Enemy enemy2 = new EnemyLevel1(w);
		enemy2.setPosition(new Vec2((float)(1010.0/20.0), (float)(-36.5/20.0f)));
		w.listMovable.add(enemy2);

		Enemy enemy3 = new EnemyLevel1(w);
		enemy3.removeAllImages();
		enemy3.addImage(new BodyImage("resources/enemylevel1blue.gif",1.4f));
		enemy3.setPosition(new Vec2((float)(590.0/20.0), (float)(84.5/20.0f)));
		w.listMovable.add(enemy3);

		//Load the platforms, the ground, the tree, the coins
		BoxShape treeShape = new BoxShape(0.6f, 8f);
		StaticBody tree = new StaticBody(w, treeShape);
		tree.setPosition(new Vec2(-19f, 0f));
		tree.addImage(new BodyImage("resources/tree.png",17.6f));
		 
		BoxShape platform3Shape = new BoxShape((float)(67/40.0),(float)(192/40.0));
		StaticBody platform3 = new StaticBody(w, platform3Shape);
		platform3.setPosition(new Vec2((float)(131.5/20.0), (float)(-187.5/20.0f)));
	  	platform3.addImage(new BodyImage("resources/platform3.png",(float)(192.0/20.0)));

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(platform3.getPosition(),new Vec2((float)(67/40.0),(float)(192/40.0)),i,w);
		}

		BoxShape groundShape = new BoxShape((float)(558/40.0),(float)(129/40.0));
		StaticBody ground = new StaticBody(w, groundShape);
		ground.setPosition(new Vec2((float)(-114.0/20.0), (float)(-216.0/20.0f)));
		ground.addImage(new BodyImage("resources/ground.png",(float)(129.0/20.0)));

		for (int i = 0; i<5; i++) {
			Coin.makeCoins(new Vec2((float)(-114.0/20.0), (float)(-216.0/20.0f)),new Vec2((float)(558/40.0),(float)(129/40.0)),i,w);
		}

	    PolygonShape PolygonShape = new PolygonShape(-3.75f/2,-0.75f, 3.75f/2,-0.75f, 3.75f/2f,0.75f);
	    StaticBody triangleShape = new StaticBody(w, PolygonShape);
	    triangleShape.setPosition(new Vec2(2.73f-3.75f/2, -7.65f+0.75f));
	    triangleShape.addImage(new BodyImage("resources/platform1.png",1.5f));
	
	    BoxShape platform2Shape = new BoxShape((float)(44/40.0),(float)(29/40.0));
	    StaticBody platform2 = new StaticBody(w, platform2Shape);
	    platform2.setPosition(new Vec2((float)(76.0/20.0), (float)(-139.0/20.0f)));
	   	platform2.addImage(new BodyImage("resources/platform2.png",(float)(29.0/20.0)));

	   	BoxShape platform6Shape = new BoxShape((float)(203/40.0),(float)(246/40.0));
	   	StaticBody platform6 = new StaticBody(w, platform6Shape);
	   	platform6.setPosition(new Vec2((float)(476.5/20.0), (float)(-159.5/20.0f)));
	  	platform6.addImage(new BodyImage("resources/platform6.png",(float)(246.0/20.0)));

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(platform6.getPosition(),new Vec2((float)(203/40.0),(float)(246/40.0)),i,w);
		}

	  	BoxShape platform5Shape = new BoxShape((float)(138/40.0),(float)(190/40.0));
	   	StaticBody platform5 = new StaticBody(w, platform5Shape);
	   	platform5.setPosition(new Vec2((float)(308.0/20.0), (float)(-187.5/20.0f)));
	   	platform5.addImage(new BodyImage("resources/platform5.png",(float)(190.0/20.0)));

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(platform5.getPosition(),new Vec2((float)(138/40.0),(float)(190/40.0)),i,w);
		}

	  	BoxShape platform4Shape = new BoxShape((float)(70/40.0),(float)(23/40.0));
	  	StaticBody platform4 = new StaticBody(w, platform4Shape);
	  	platform4.setPosition(new Vec2((float)(204.0/20.0), (float)(-107.0/20.0f)));
	  	platform4.addImage(new BodyImage("resources/platform4.png",(float)(23.0/20.0)));

	  	BoxShape platform7Shape = new BoxShape((float)(144/40.0),(float)(342/40.0));
	  	StaticBody platform7 = new StaticBody(w, platform7Shape);
	  	platform7.setPosition(new Vec2((float)(639.0/20.0), (float)(-111.5/20.0f)));
	  	platform7.addImage(new BodyImage("resources/platform7.png",(float)(342.0/20.0)));
	 
	  	BoxShape platform8Shape = new BoxShape((float)(56/40.0),(float)(17/40.0));
	  	StaticBody platform8 = new StaticBody(w, platform8Shape);
	  	platform8.setPosition(new Vec2((float)(302.0/20.0), (float)(-28.0/20.0f)));
	  	platform8.addImage(new BodyImage("resources/platform8.png",(float)(17.0/20.0)));
	  	
	  	BoxShape platform9Shape = new BoxShape((float)(35/40.0),(float)(17/40.0));
	  	StaticBody platform9 = new StaticBody(w, platform9Shape);
	  	platform9.setPosition(new Vec2((float)(259.5/20.0), (float)(31.0/20.0f)));
	  	platform9.addImage(new BodyImage("resources/platform9.png",(float)(17.0/20.0)));
		Coin.makeCoins(platform9.getPosition(),new Vec2((float)(35/40.0),(float)(17/40.0)),0,w);

	  	BoxShape platform10Shape = new BoxShape((float)(106/40.0),(float)(15/40.0));
	  	StaticBody platform10 = new StaticBody(w, platform10Shape);
	  	platform10.setPosition(new Vec2((float)(326.0/20.0), (float)(117.0/20.0f)));
	  	platform10.addImage(new BodyImage("resources/platform10.png",(float)(15.0/20.0)));
	  	
	  	BoxShape boxShape = new BoxShape((float)(22/40.0),(float)(22/40.0));
	  	DynamicBody box = new DynamicBody(w, boxShape);
	  	box.setGravityScale(11f);
	  	box.setPosition(new Vec2((float)(340.0/20.0), (float)(135.5/20.0f)));
	  	box.addImage(new BodyImage("resources/box.png",(float)(22.0/20.0)));
	  	
	
	  	BoxShape platform11Shape = new BoxShape((float)(144/40.0),(float)(234/40.0));
	  	StaticBody platform11 = new StaticBody(w, platform11Shape);
	  	platform11.setPosition(new Vec2((float)(1069.0/20.0), (float)(-163.5/20.0f)));
	  	platform11.addImage(new BodyImage("resources/platform11.png",(float)(234.0/20.0)));
	  	
	  	BoxShape platform13Shape = new BoxShape((float)(144/40.0),(float)(234/40.0));
	  	StaticBody platform13 = new StaticBody(w, platform13Shape);
	  	platform13.setPosition(new Vec2((float)(1326.0/20.0), (float)(-163.5/20.0f)));
	  	platform13.addImage(new BodyImage("resources/platform13.png",(float)(234.0/20.0)));
	  	
	  	BoxShape platform12Shape = new BoxShape((float)(117/40.0),(float)(33/40.0));
	  	StaticBody platform12 = new StaticBody(w, platform12Shape);
	  	platform12.setPosition(new Vec2((float)(1199.5/20.0), (float)(-69.0/20.0f)));
	  	platform12.addImage(new BodyImage("resources/platform12.png",(float)(33.0/20.0)));
	  	
	  	BoxShape platform14Shape = new BoxShape((float)(217/40.0),(float)(438/40.0));
	  	StaticBody platform14 = new StaticBody(w, platform14Shape);
	  	platform14.setPosition(new Vec2((float)(1498.5/20.0), (float)(-61.5/20.0f)));
	  	platform14.addImage(new BodyImage("resources/platform14.png",(float)(438.0/20.0)));

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(platform14.getPosition(),new Vec2((float)(217/40.0),(float)(438/40.0)),i,w);
		}

	  	StaticBody platform15 = new StaticBody(w, platform9Shape);
	  	platform15.setPosition(new Vec2((float)(1077.5/20.0), (float)(129.0/20.0f)));
	  	platform15.addImage(new BodyImage("resources/platform9.png",(float)(17.0/20.0)));
	  	
	  	CircleShape wheelShape = new CircleShape((float)(56/40.0));
	  	DynamicBody wheel = new DynamicBody(w, wheelShape);
	  	wheel.setPosition(new Vec2((float)(1324.0/20.0), (float)(-18.5/20.0f)));
	  	wheel.addImage(new BodyImage("resources/wheel.png",(float)(56.0/20.0)));
	  	
	  	BoxShape platform16Shape = new BoxShape((float)(139/40.0),(float)(15/40.0));
	  	StaticBody platform16 = new StaticBody(w, platform16Shape);
	  	platform16.setPosition(new Vec2((float)(1161.5/20.0), (float)(51.0/20.0f)));
	  	platform16.addImage(new BodyImage("resources/platform16.png",(float)(15.0/20.0)));
		for (int i = -2; i<3; i++) {
			Coin.makeCoins(platform16.getPosition(),new Vec2((float)(139/40.0),(float)(15/40.0)),i,w);
		}
	  	StaticBody platform17 = new StaticBody(w, platform16Shape);
	  	platform17.setPosition(new Vec2((float)(1161.5/20.0), (float)(204.0/20.0f)));
	  	platform17.addImage(new BodyImage("resources/platform17.png",(float)(15.0/20.0)));
		for (int i = -2; i<3; i++) {
			Coin.makeCoins(platform17.getPosition(),new Vec2((float)(139/40.0),(float)(15/40.0)),i,w);
		}

	  	MovingPlatform mv= new MovingPlatform(w,9,5);
	  	mv.setPosition(new Vec2((float)(763.5/20.0), (float)(47.0/20.0f)));
	  	w.listStaticObject.add(mv);

	}

	/**
	 * Load level 2
	 * @param w
	 */
	public static void loadLevel2(GameWorld w) {
		//Border of the screen
		Shape borderShape = new BoxShape(1f,60f);
		StaticBody border_left = new StaticBody(w,borderShape);
		border_left.setPosition(new Vec2(-21f,0f));

		//Enemies
		Slime slime1 = new Slime(w);
		slime1.setPosition(new Vec2((float)(-18.0/20.0), (float)(-87.5/20.0f)));
		w.listMovable.add(slime1);

		EnemyLevel2 enemyspike = new EnemyLevel2(w);
		enemyspike.setPosition(new Vec2((float)(-236.5/20.0), (float)(-138.0/20.0f)));
		w.listMovable.add(enemyspike);

		EnemyLevel2 enemyspike2 = new EnemyLevel2(w);
		enemyspike2.setPosition(new Vec2((float)(832.5/20.0), (float)(-83.0/20.0f)));
		w.listMovable.add(enemyspike2);

		//Platform, coins, ...
		BoxShape lv2platform1Shape = new BoxShape((float)(307/40.0),(float)(129/40.0));
		StaticBody lv2platform1 = new StaticBody(w, lv2platform1Shape);
		lv2platform1.setPosition(new Vec2((float)(-228.5/20.0), (float)(-216.0/20.0f)));
		lv2platform1.addImage(new BodyImage("resources/lv2platform1.png",(float)(129.0/20.0)));

		for (int i = -2; i<3; i++) {
			Coin.makeCoins(lv2platform1.getPosition(),new Vec2((float)(307/40.0),(float)(129/40.0)),i,w);
		}

		BoxShape lv2platform2Shape = new BoxShape((float)(44/40.0),(float)(156/40.0));
		StaticBody lv2platform2 = new StaticBody(w, lv2platform2Shape);
		lv2platform2.setPosition(new Vec2((float)(-55.0/20.0), (float)(-202.5/20.0f)));
		lv2platform2.addImage(new BodyImage("resources/lv2platform2.png",(float)(156.0/20.0)));

		for (int i = 0; i<2; i++) {
			Coin.makeCoins(lv2platform2.getPosition(),new Vec2((float)(44/40.0),(float)(156/40.0)),i,w);
		}

		BoxShape lv2platform3Shape = new BoxShape((float)(219/40.0),(float)(183/40.0));
		StaticBody lv2platform3 = new StaticBody(w, lv2platform3Shape);
		lv2platform3.setPosition(new Vec2((float)(74.5/20.0), (float)(-189.0/20.0f)));
		lv2platform3.addImage(new BodyImage("resources/lv2platform3.png",(float)(183.0/20.0)));
		for (int i = 0;i<2;i++) {
			Coin.makeCoins(lv2platform3.getPosition(),new Vec2((float)(219/40.0),(float)(183/40.0)),i,w);
		}
		BoxShape lv2platform4Shape = new BoxShape((float)(760/40.0),(float)(184/40.0));
		StaticBody lv2platform4 = new StaticBody(w, lv2platform4Shape);
		lv2platform4.setPosition(new Vec2((float)(745.0/20.0), (float)(-188.5/20.0f)));
		lv2platform4.addImage(new BodyImage("resources/lv2platform4.png",(float)(184.0/20.0)));

		PolygonShape lv2platform5Shape = new PolygonShape((float)(-156.0/20.0),(float)(-15.0/20.0),
				(float)(-128.0/20.0),(float)(15.0/20.0),
				(float)(128.0/20.0),(float)(15.0/20.0),
				(float)(156.0/20.0),(float)(-15.0/20.0));
		StaticBody lv2platform5 = new StaticBody(w, lv2platform5Shape);
		lv2platform5.setPosition(new Vec2((float)(645.0/20.0), (float)(-84.0/20.0f)));
		lv2platform5.addImage(new BodyImage("resources/lv2platform5.png",(float)(29.0/20.0)));


		BoxShape lv2platform6Shape = new BoxShape((float)(45/40.0),(float)(15/40.0));
		StaticBody lv2platform6 = new StaticBody(w, lv2platform6Shape);
		lv2platform6.setPosition(new Vec2((float)(640.5/20.0), (float)(-62.0/20.0f)));
		lv2platform6.addImage(new BodyImage("resources/lv2platform6.png",(float)(15.0/20.0)));

		BoxShape lv2platform7Shape = new BoxShape((float)(217/40.0),(float)(374/40.0));
		StaticBody lv2platform7 = new StaticBody(w, lv2platform7Shape);
		lv2platform7.setPosition(new Vec2((float)(1498.5/20.0), (float)(-93.5/20.0f)));
		lv2platform7.addImage(new BodyImage("resources/lv2platform7.png",(float)(374.0/20.0)));

		FallingPlatform p = new FallingPlatform(w, new Vec2((float)(194.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p);
		FallingPlatform p2 = new FallingPlatform(w, new Vec2((float)(214.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p2);
		FallingPlatform p3 = new FallingPlatform(w, new Vec2((float)(234.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p3);
		FallingPlatform p4 = new FallingPlatform(w, new Vec2((float)(254.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p4);
		FallingPlatform p5 = new FallingPlatform(w, new Vec2((float)(274.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p5);
		FallingPlatform p6 = new FallingPlatform(w, new Vec2((float)(294.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p6);
		FallingPlatform p7 = new FallingPlatform(w, new Vec2((float)(314.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p7);
		FallingPlatform p8 = new FallingPlatform(w, new Vec2((float)(334.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p8);
		FallingPlatform p9 = new FallingPlatform(w, new Vec2((float)(354.0/20.0), (float)(-107.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p9);

		FallingPlatform p10 = new FallingPlatform(w, new Vec2((float)(444.0/20.0), (float)(-50/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p10);
		p10.TIMER_STANDING = 30;

		FallingPlatform p11 = new FallingPlatform(w, new Vec2((float)(444.0/20.0), (float)(10/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p11);
		p11.TIMER_STANDING = 30;

		FallingPlatform p12 = new FallingPlatform(w, new Vec2((float)(444.0/20.0), (float)(70/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p12);
		p12.TIMER_STANDING = 30;

		FallingPlatform p14 = new FallingPlatform(w, new Vec2((float)(1302.0/20.0), (float)(-50.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p14);
		p14.TIMER_STANDING = 40;
		FallingPlatform p141 = new FallingPlatform(w, new Vec2((float)(1282.0/20.0), (float)(-50.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p141);
		p141.TIMER_STANDING = 40;
		FallingPlatform p142 = new FallingPlatform(w, new Vec2((float)(1322.0/20.0), (float)(-50.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p142);
		p142.TIMER_STANDING = 40;

		FallingPlatform p15 = new FallingPlatform(w, new Vec2((float)(1200.0/20.0), (float)(22.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p15);
		p15.TIMER_STANDING = 40;
		FallingPlatform p151 = new FallingPlatform(w, new Vec2((float)(1180.0/20.0), (float)(22.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p151);
		p151.TIMER_STANDING = 40;

		FallingPlatform p152 = new FallingPlatform(w, new Vec2((float)(1220.0/20.0), (float)(22.5/20.0f)), w.player, w.camera);
		w.listStaticObject.add(p152);
		p152.TIMER_STANDING = 40;
		
		BoxShape paltformer11Shape = new BoxShape((float)(237/40.0),(float)(43/40.0));
		StaticBody paltformer11 = new StaticBody(w, paltformer11Shape);
		paltformer11.setPosition(new Vec2((float)(644.5/20.0), (float)(67.0/20.0f)));
		paltformer11.addImage(new BodyImage("resources/platformer11.png",(float)(43.0/20.0)));

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(paltformer11.getPosition(),new Vec2((float)(237/40.0),(float)(43/40.0)),i,w);
		}
		
		Sword sword = new Sword(w);
		sword.setPosition(new Vec2((float)(644.5/20.0), (float)(-44.0/20.0f)));

	}

	/**
	 * Load level 3
	 * @param w
	 */
	public static void loadLevel3(GameWorld w) {
		//Border of the level
		Shape borderShape = new BoxShape(1f,60f);
		StaticBody border_left = new StaticBody(w,borderShape);
		border_left.setPosition(new Vec2(-21f,0f));

		//DisappearingPlatform
		DisappearingPlatform dp = new DisappearingPlatform(w,new Vec2((float)(505.5/20.0), (float)(37.5/20.0f)),w.camera);
		w.listStaticObject.add(dp);
		DisappearingPlatform dp2 = new DisappearingPlatform(w,new Vec2((float)(159.5/20.0), (float)(-73.5/20.0f)),w.camera);
		w.listStaticObject.add(dp2);

		//Platforms, coins, ...
		BoxShape lv3platform1Shape = new BoxShape((float)(168/40.0),(float)(144/40.0));
		StaticBody lv3platform1 = new StaticBody(w, lv3platform1Shape);
		lv3platform1.setPosition(new Vec2((float)(-309.0/20.0), (float)(-208.5/20.0f)));
		lv3platform1.addImage(new BodyImage("resources/lv3platform1.png",(float)(144.0/20.0)));

		BoxShape lv3platform2Shape = new BoxShape((float)(145/40.0),(float)(13/40.0));
		StaticBody lv3platform2 = new StaticBody(w, lv3platform2Shape);
		lv3platform2.setPosition(new Vec2((float)(-320.5/20.0), (float)(-27.0/20.0f)));
		lv3platform2.addImage(new BodyImage("resources/lv3platform2.png",(float)(13.0/20.0)));

		BoxShape lv3platform3Shape = new BoxShape((float)(10/40.0),(float)(81/40.0));
		StaticBody lv3platform3 = new StaticBody(w, lv3platform3Shape);
		lv3platform3.setPosition(new Vec2((float)(-388.0/20.0), (float)(20.0/20.0f)));
		lv3platform3.addImage(new BodyImage("resources/lv3platform3.png",(float)(81.0/20.0)));

		BoxShape lv3platform4Shape = new BoxShape((float)(145/40.0),(float)(13/40.0));
		StaticBody lv3platform4 = new StaticBody(w, lv3platform4Shape);
		lv3platform4.setPosition(new Vec2((float)(-320.5/20.0), (float)(67.0/20.0f)));
		lv3platform4.addImage(new BodyImage("resources/lv3platform4.png",(float)(13.0/20.0)));
		
		BoxShape lv3platform7Shape = new BoxShape((float)(31/40.0),(float)(69/40.0));
		StaticBody lv3platform7 = new StaticBody(w, lv3platform7Shape);
		lv3platform7.setPosition(new Vec2((float)(-88.5/20.0), (float)(-2.0/20.0f)));
		lv3platform7.addImage(new BodyImage("resources/lv3platform7.png",(float)(69.0/20.0)));
		
		BoxShape lv3platform6Shape = new BoxShape((float)(541/40.0),(float)(43/40.0));
		StaticBody lv3platform6 = new StaticBody(w, lv3platform6Shape);
		lv3platform6.setPosition(new Vec2((float)(164.5/20.0), (float)(52.0/20.0f)));
		lv3platform6.addImage(new BodyImage("resources/lv3platform6.png",(float)(43.0/20.0)));

		BoxShape lv3platform8Shape = new BoxShape((float)(34/40.0),(float)(68/40.0));
		StaticBody lv3platform8 = new StaticBody(w, lv3platform8Shape);
		lv3platform8.setPosition(new Vec2((float)(414.0/20.0), (float)(-2.5/20.0f)));
		lv3platform8.addImage(new BodyImage("resources/lv3platform8.png",(float)(68.0/20.0)));


		BoxShape lv3platform9Shape = new BoxShape((float)(202/40.0),(float)(190/40.0));
		StaticBody lv3platform9 = new StaticBody(w, lv3platform9Shape);
		lv3platform9.setPosition(new Vec2((float)(-50.0/20.0), (float)(-187.5/20.0f)));
		lv3platform9.addImage(new BodyImage("resources/lv3platform9.png",(float)(190.0/20.0)));

		BoxShape lv3platform10Shape = new BoxShape((float)(248/40.0),(float)(98/40.0));
		StaticBody lv3platform10 = new StaticBody(w, lv3platform10Shape);
		lv3platform10.setPosition(new Vec2((float)(172.0/20.0), (float)(-231.5/20.0f)));
		lv3platform10.addImage(new BodyImage("resources/lv3platform10.png",(float)(98.0/20.0)));

		BoxShape lv3platform11Shape = new BoxShape((float)(331/40.0),(float)(188/40.0));
		StaticBody lv3platform11 = new StaticBody(w, lv3platform11Shape);
		lv3platform11.setPosition(new Vec2((float)(458.5/20.0), (float)(-186.5/20.0f)));
		lv3platform11.addImage(new BodyImage("resources/lv3platform11.png",(float)(188.0/20.0)));
		
		BoxShape lv3platform5Shape = new BoxShape((float)(159/40.0),(float)(21/40.0));
		StaticBody lv3platform5 = new StaticBody(w, lv3platform5Shape);
		lv3platform5.setPosition(new Vec2((float)(-176.5/20.0), (float)(61.0/20.0f)));
		lv3platform5.addImage(new BodyImage("resources/lv3platform5.png",(float)(21.0/20.0)));

		BoxShape lv3platform12Shape = new BoxShape((float)(417/40.0),(float)(188/40.0));
		 StaticBody lv3platform12 = new StaticBody(w, lv3platform12Shape);
		lv3platform12.setPosition(new Vec2((float)(1031.5/20.0), (float)(-186.5/20.0f)));
		lv3platform12.addImage(new BodyImage("resources/lv3platform12.png",(float)(188.0/20.0)));

		BoxShape lv3platform13Shape = new BoxShape((float)(248/40.0),(float)(247/40.0));
		StaticBody lv3platform13 = new StaticBody(w, lv3platform13Shape);
		lv3platform13.setPosition(new Vec2((float)(1483.0/20.0), (float)(-157.0/20.0f)));
		lv3platform13.addImage(new BodyImage("resources/lv3platform13.png",(float)(247.0/20.0)));

		BoxShape box2Shape = new BoxShape((float)(55/40.0),(float)(79/40.0));
		 StaticBody box2 = new StaticBody(w, box2Shape);
		box2.setPosition(new Vec2((float)(584.5/20.0), (float)(-53.0/20.0f)));
		box2.addImage(new BodyImage("resources/box2.png",(float)(79.0/20.0)));
		
		BoxShape box1Shape = new BoxShape((float)(33/40.0),(float)(40/40.0));
		 StaticBody box1 = new StaticBody(w, box1Shape);
		box1.setPosition(new Vec2((float)(541.5/20.0), (float)(-72.5/20.0f)));
		box1.addImage(new BodyImage("resources/box1.png",(float)(40.0/20.0)));
		
		BoxShape pipeShape = new BoxShape((float)(58/40.0),(float)(151/40.0));
		 StaticBody pipe = new StaticBody(w, pipeShape);
		pipe.setPosition(new Vec2((float)(714.0/20.0), (float)(-205.0/20.0f)));
		pipe.addImage(new BodyImage("resources/pipe.png",(float)(151.0/20.0)));

		BoxShape lv3platform14Shape = new BoxShape((float)(38/40.0),(float)(259/40.0));
		StaticBody lv3platform14 = new StaticBody(w, lv3platform14Shape);
		lv3platform14.setPosition(new Vec2((float)(1588.0/20.0), (float)(151.0/20.0f)));
		lv3platform14.addImage(new BodyImage("resources/lv3platform14.png",(float)(259.0/20.0)));

		Key key = new Key(w);
		key.setPosition(new Vec2((float)(-353.0/20.0), (float)(-5.5/20.0f)));

		Door d = new Door(w,new Vec2((float)(-89.0/20.0), (float)(-65.5/20.0f)));

		ElectricPortal ep = new ElectricPortal(w);
		ep.setPosition(new Vec2((float)(414.0/20.0), (float)(-64.5/20.0f)));
		w.listStaticObject.add(ep);

		w.player.upgrade();

		EnemyFollower ef = new EnemyFollower(w,w.player);
		ef.setPosition(new Vec2((float)(0/20.0), (float)(89.5/20.0f)));
		w.listMovable.add(ef);

		EnemyMettalic em = new EnemyMettalic(w);
		em.setPosition(new Vec2((float)(232.0/20.0), (float)(-155.5/20.0f)));
		w.listMovable.add(em);

		EnemyLevel1 el1 = new EnemyLevel1(w);
		el1.setPosition(new Vec2((float)(15.5/20.0), (float)(-169.0/20.0f)));
		w.listMovable.add(el1);

		Shooter s = new Shooter(w);
		s.setPosition(new Vec2((float)(-369.5/20.0), (float)(80.5/20.0f)));
		w.listMovable.add(s);

		Key key2 = new Key(w);
		key2.setPosition(new Vec2((float)(71.0/20.0), (float)(89.5/20.0f)));

		Door d2 = new Door(w,new Vec2((float)(1589.0/20.0), (float)(-6.5/20.0f)));
		w.listStaticObject.add(d2);

		EnemyFollower ef2 = new EnemyFollower(w, w.player);
		ef2.setPosition(new Vec2((float)(891.0/20.0), (float)(-77.5/20.0f)));
		w.listMovable.add(ef2);

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform2.getPosition(),new Vec2((float)(145/40.0),(float)(13/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform4.getPosition(),new Vec2((float)(145/40.0),(float)(13/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform6.getPosition(),new Vec2((float)(541/40.0),(float)(43/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform9.getPosition(),new Vec2((float)(202/40.0),(float)(190/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform5.getPosition(),new Vec2((float)(159/40.0),(float)(21/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform12.getPosition(),new Vec2((float)(417/40.0),(float)(188/40.0)),i,w);
		}

		for (int i = -1; i<2; i++) {
			Coin.makeCoins(lv3platform13.getPosition(),new Vec2((float)(248/40.0),(float)(247/40.0)),i,w);
		}
	}
	
}
