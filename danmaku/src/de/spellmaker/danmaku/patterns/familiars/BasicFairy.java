 package de.spellmaker.danmaku.patterns.familiars;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.level.LevelHandler;
import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.PatternListener;
import de.spellmaker.danmaku.patterns.bullets.VectorBullet;

public class BasicFairy implements Pattern {
	private TextureRegion image;
	private CollisionObject hitbox;
	private boolean active;
	
	private boolean entered;
	private int movdir; 
	private float timer;
	private float bullettimer;
	private float bulletspeed;
	private ArrayList<PatternListener> listeners;
	
	public BasicFairy(int y, int movdir, float bulletspeed, float bullettimer){
		hitbox = new CollisionObject(DataManager.leftborder - 32, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.characters, 32, 0, 32, 32);
		active = true;
		
		entered = false;
		this.movdir = movdir;
		listeners = new ArrayList<PatternListener>();
		this.bulletspeed = bulletspeed;
		this.bullettimer = bullettimer;
	}
	
	@Override
	public void render(float d) {
		if(active){ 
			hitbox.setX(hitbox.getX() + movdir * d);
			
			SpriteBatch batch = DataManager.getManager().batch;
			batch.draw(image, hitbox.getX(), hitbox.getY());
			
			if(hitbox.getX() <= DataManager.leftborder ||  hitbox.getX() >= DataManager.rightborder){
				if(entered){
					active = false;
				}
			}
			else if(!entered){
				entered = true;
			}
			
			if(entered){
				timer += d;
				if(timer >= bullettimer){
					System.out.println("bullet created");
					timer = 0;
					float posx = LevelHandler.getPlayer().getX();
					float posy = LevelHandler.getPlayer().getY();
					
					Vector2 vec = new Vector2(posx - hitbox.getX(), posy - hitbox.getY());
					vec.div(vec.len()).mul(bulletspeed);
					
					VectorBullet vb = new VectorBullet(hitbox.getX(), hitbox.getY(), vec.x, vec.y);
					
					for(int i = 0; i < listeners.size(); i++){
						listeners.get(i).patternCreated(vb);
					}
				}
			}
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
		this.listeners.add(pl);
	}

	@Override
	public void removeListener(PatternListener pl) {
		this.listeners.remove(pl);
	}
}
