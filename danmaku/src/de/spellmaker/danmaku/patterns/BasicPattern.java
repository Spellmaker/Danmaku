package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.bullets.VectorBullet;

public class BasicPattern implements Pattern {
	//private VectorBullet bullet;
	private ArrayList<PatternListener> listeners;
	private StarBurst burst; 
	//private RotatingStarBurst burst1;
	//private RotatingStarBurst burst2;
	
	public BasicPattern(){
		listeners = new ArrayList<PatternListener>();
		//bullet = new VectorBullet(DataManager.leftborder + 10, DataManager.upperborder, 200, -150);
		int middle = DataManager.leftborder + (DataManager.rightborder - DataManager.leftborder) / 2;
		//burst1 = new RotatingStarBurst(middle - 200, 500, 20);
		//burst2 = new RotatingStarBurst(middle + 200, 500, -20);
		burst = new StarBurst(middle, 500);
	}
	
	@Override
	public void render(float d) {
		//bullet.render(d);
		//burst1.render(d);
		//burst2.render(d);
		burst.render(d);
	}

	@Override
	public String getName() {
		return "Testing sign - Master debugger";
	}

	@Override
	public float timeRemaing() {
		return -1;
	}

	@Override
	public void addListener(PatternListener pl) {
		if(!listeners.contains(pl)) listeners.add(pl);
	}

	@Override
	public void removeListener(PatternListener pl) {
		listeners.remove(pl);
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public boolean collidesWith(CollisionObject rec) {
		return burst.collidesWith(rec);
		//return bullet.collidesWith(rec) || burst1.collidesWith(rec) || burst2.collidesWith(rec);
	}

	@Override
	public void renderHitboxes() {
		burst.renderHitbox();
		//bullet.renderHitboxes();
		//burst1.renderHitboxes();
		//burst2.renderHitboxes();
	}

	@Override
	public boolean isAlive() {
		return burst.isAlive();
		//return bullet.isAlive() || burst1.isAlive() || burst2.isAlive();
	}

}
