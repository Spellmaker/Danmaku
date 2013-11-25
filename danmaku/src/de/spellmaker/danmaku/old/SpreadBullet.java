package de.spellmaker.danmaku.old;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.PatternListener;

public class SpreadBullet implements Pattern {
	private TextureRegion image;
	private CollisionObject hitbox;
	private int vx;
	private int vy;
	private boolean active;
	private int spread;
	private int delay;
	private int mdelay;
	private ArrayList<PatternListener> listeners; 
	
	public SpreadBullet(int x, int y, int vx, int vy, int spread, int delay){
		hitbox = new CollisionObject(x, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.bullets, 0, 0, 32, 32);
		this.vx = vx;
		this.vy = vy;
		this.active = true;
		this.spread = spread;
		this.delay = delay;
		this.mdelay = delay;
		listeners = new ArrayList<PatternListener>();
	}
	
	@Override
	public void render(float d) {
		if(active){
			hitbox.setX(hitbox.getX() + d * this.vx);
			hitbox.setY(hitbox.getY() + d * this.vy);
			
			SpriteBatch batch = DataManager.getManager().batch;
			batch.draw(image, hitbox.getX(), hitbox.getY());
			
			delay -= d;
			if(delay <= 0){
				spread -= 1;
				Iterator<PatternListener> it = null; 
				if(spread > 0){
					Vector2 vec = new Vector2(vx, vy);
					vec.setAngle(vec.angle() + 45);
					SpreadBullet sb1 = new SpreadBullet((int)hitbox.getX(), (int)hitbox.getY(), (int)vec.x, (int)vec.y, spread, mdelay);
					vec.setAngle(vec.angle() - 90);
					SpreadBullet sb2 = new SpreadBullet((int)hitbox.getX(), (int)hitbox.getY(), (int)vec.x,(int) vec.y, spread, mdelay);
					it = listeners.iterator();
					while(it.hasNext()){
						PatternListener current = it.next();
						current.patternCreated(sb1);
						current.patternCreated(sb2);
					}
				}
				it = listeners.iterator();
			}
			
			
			active = active && !((hitbox.getY() <= 0 || hitbox.getY() >= Options.screen_height || hitbox.getX() <= DataManager.leftborder || hitbox.getX() >= DataManager.rightborder));
		}
	}

	@Override
	public boolean collidesWith(CollisionObject rec) {
		return hitbox.collidesWith(rec);
	}

	@Override
	public boolean isAlive() {
		return active;
	}

	@Override
	public void renderHitboxes() {
		this.hitbox.render();
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
		if(!this.listeners.contains(pl)) this.listeners.add(pl);
	}

	@Override
	public void removeListener(PatternListener pl) {
		this.listeners.remove(pl);
	}
}
