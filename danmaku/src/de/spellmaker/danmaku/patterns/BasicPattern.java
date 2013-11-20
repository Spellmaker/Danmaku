package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.bullets.BasicBullet;

public class BasicPattern implements Pattern {
	private BasicBullet bullet;
	private ArrayList<PatternListener> listeners;
	
	public BasicPattern(){
		listeners = new ArrayList<PatternListener>();
		bullet = new BasicBullet(DataManager.leftborder + 10, DataManager.upperborder);
	}
	
	@Override
	public boolean render(float d) {
		return bullet.render(d);
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
		return bullet.collidesWith(rec);
	}

}
