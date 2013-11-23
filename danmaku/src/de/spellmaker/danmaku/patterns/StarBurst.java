package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.bullets.Bullet;
import de.spellmaker.danmaku.patterns.bullets.SpreadBullet;

public class StarBurst implements Bullet, PatternListener {
	private ArrayList<Pattern> bullets;
	
	public StarBurst(int x, int y){
		bullets = new ArrayList<Pattern>();
		Vector2 vec = new Vector2(200, 0);
		for(int i = 0; i < 360; i += 45){
			vec.setAngle(i);
			SpreadBullet sb = new SpreadBullet(x, y, (int)vec.x, (int)vec.y, 5, 10);
			sb.addListener(this);
			bullets.add(sb);
		}
	}
	
	@Override
	public void render(float d) {
		for(int i = 0; i < bullets.size(); i++){
			Pattern current = bullets.get(i);
			current.render(d);
			if(!current.isAlive()){
				bullets.remove(i);
				i--;
			}
		}
	}

	@Override
	public boolean collidesWith(CollisionObject rec) {
		Iterator<Pattern> it = bullets.iterator();
		while(it.hasNext()){
			if(it.next().collidesWith(rec)) return true;
		}
		return false;
	}

	@Override
	public void renderHitbox() {
		Iterator<Pattern> it = bullets.iterator();
		while(it.hasNext()){
			it.next().renderHitboxes();
		}
	}

	@Override
	public boolean isAlive() {
		return bullets.size() > 0;
	}

	@Override
	public void patternCreated(Pattern p) {
		this.bullets.add(p);
		p.addListener(this);
	}

}
