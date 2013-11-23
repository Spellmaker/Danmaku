package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;

import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.familiars.BasicFairy;

public class BasicPattern implements Pattern, PatternListener {
	private ArrayList<PatternListener> listeners;
	private ArrayList<Pattern> pattern;
	private float timer;
	private boolean tick;
	
	public BasicPattern(){
		listeners = new ArrayList<PatternListener>();
		pattern = new ArrayList<Pattern>();
		//bullet = new VectorBullet(DataManager.leftborder + 10, DataManager.upperborder, 200, -150);
		//int middle = DataManager.leftborder + (DataManager.rightborder - DataManager.leftborder) / 2;
		//burst1 = new RotatingStarBurst(middle - 200, 500, 20);
		//burst2 = new RotatingStarBurst(middle + 200, 500, -20);
		pattern.add(new BasicFairy(500, 100, 100, 0.5f));
		pattern.get(0).addListener(this);
		timer = 0;
		tick = true;
	}
	
	@Override
	public void render(float d) {
		//bullet.render(d);
		//burst1.render(d);
		//burst2.render(d);
		for(int i = 0; i < pattern.size(); i++) pattern.get(i).render(d);
		timer += d;
		if(timer >= 1.5){
			timer = 0;
			BasicFairy bf;
			if(tick) bf = new BasicFairy(500, 100, 250, 1f);
			else bf = new BasicFairy(500, 100, 100, 0.5f);
			tick = !tick;
			bf.addListener(this);
			pattern.add(bf);
			
		}
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
	public boolean collidesWith(CollisionObject rec) {
		for(int i = 0; i < pattern.size(); i++) if(pattern.get(i).collidesWith(rec)) return true;
		return false;
	}

	@Override
	public void renderHitboxes() {
		for(int i = 0; i < pattern.size(); i++) pattern.get(i).renderHitboxes();
	}

	@Override
	public boolean isAlive() {
		for(int i = 0; i < pattern.size(); i++){
			if(pattern.get(i).isAlive()) return true;
		}
		return false;
	}

	@Override
	public void patternCreated(Pattern p) {
		pattern.add(p);
	}

}
