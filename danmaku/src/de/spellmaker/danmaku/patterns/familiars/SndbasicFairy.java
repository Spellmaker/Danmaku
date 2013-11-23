package de.spellmaker.danmaku.patterns.familiars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.level.LevelHandler;
import de.spellmaker.danmaku.patterns.BasePattern;
import de.spellmaker.danmaku.patterns.bullets.VectorBullet;

public class SndbasicFairy extends BasePattern {
	private TextureRegion image;
	private CollisionObject hitbox;
	private boolean entered;
	private int movdir; 
	private float bulletspeed;
	private boolean active;

	public SndbasicFairy(int y, int movdir, float bulletspeed, float bullettimer){
		super();
		hitbox = new CollisionObject(DataManager.leftborder - 32, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.characters, 32, 0, 32, 32);
		
		entered = false;
		this.movdir = movdir;
		this.bulletspeed = bulletspeed;
		this.active = true;
		
		this.addTimer(bullettimer, 0);
	}
	
	@Override
	protected void step(float d) {
		//movement
		hitbox.setX(hitbox.getX() + movdir * d);
		//draw texture
		SpriteBatch batch = DataManager.getManager().batch;
		batch.draw(image, hitbox.getX(), hitbox.getY());
		//handle enter and stuff
		if(hitbox.getX() <= DataManager.leftborder ||  hitbox.getX() >= DataManager.rightborder){
			if(entered){
				active = false;
			}
		}
		else if(!entered){
			entered = true;
		}

	}

	@Override
	protected void patternEnd(int i) {
		//do nothing
	}

	@Override
	protected boolean collision(CollisionObject rec) {
		return false;
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
	public void timerEvent(int id) {
		if(id == 0){
			if(entered){
				float posx = LevelHandler.getPlayer().getX();
				float posy = LevelHandler.getPlayer().getY();
				
				Vector2 vec = new Vector2(posx - hitbox.getX(), posy - hitbox.getY());
				vec.div(vec.len()).mul(bulletspeed);
				
				VectorBullet vb = new VectorBullet(hitbox.getX(), hitbox.getY(), vec.x, vec.y);
				
				notifyListenersCreation(vb);
			}
		}
	}
	
	@Override
	protected boolean hasEnded() {
		return !active;
	}

	@Override
	protected void patternHitbox() {
		hitbox.render();
	}
}
