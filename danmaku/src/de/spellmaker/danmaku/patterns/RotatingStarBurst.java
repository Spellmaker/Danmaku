package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.bullets.VectorBullet;

public class RotatingStarBurst implements Pattern {
	private ArrayList<VectorBullet> bullets;
	private int offset;
	private float timer;
	private int x;
	private int y;
	private int rot;
	
	private int counter;
	
	public RotatingStarBurst(int x, int y, int rot){
		offset = 0;
		timer = 0;
		bullets = new ArrayList<VectorBullet>();
		this.x = x;
		this.y = y;
		counter = 20;
		this.rot = rot;
		
		spawnBullets();
	}
	
	private void spawnBullets(){
		if(counter > 0){
			Vector2 vec = new Vector2(200, 0);
			for(int i = 0; i < 360; i += 45){
				vec.setAngle(i + offset);
				bullets.add(new VectorBullet(x, y, (int)vec.x, (int)vec.y));
			}
			counter--;
		}
	}
	
	@Override
	public void render(float d) {
		Iterator<VectorBullet> it = bullets.iterator();
		while(it.hasNext()){
			VectorBullet current = it.next();
			current.render(d);
			if(!current.isAlive()) it.remove();
		}
		timer += d;
		if(timer > 0.2){
			timer = 0;
			offset += rot;
			spawnBullets();
		}
	}

	@Override
	public boolean collidesWith(CollisionObject rec) {
		Iterator<VectorBullet> it = bullets.iterator();
		while(it.hasNext()){
			if(it.next().collidesWith(rec)) return true;
		}
		return false;
	}

	@Override
	public void renderHitboxes() {
		Iterator<VectorBullet> it = bullets.iterator();
		while(it.hasNext()){
			it.next().renderHitboxes();
		}
	}

	@Override
	public boolean isAlive() {
		return (bullets.size() > 0) || (counter > 0);
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public float timeRemaing() {
		return -1;
	}

	@Override
	public void addListener(PatternListener pl) {
		//do nothing
	}

	@Override
	public void removeListener(PatternListener pl) {
		//do nothing
	}

	@Override
	public int getId() {
		return 0;
	}

}
