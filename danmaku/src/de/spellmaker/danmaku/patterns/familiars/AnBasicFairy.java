package de.spellmaker.danmaku.patterns.familiars;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.level.LevelHandler;
import de.spellmaker.danmaku.old.VectorBullet;
import de.spellmaker.danmaku.patterns.AbstractDisplayablePattern;

public class AnBasicFairy extends AbstractDisplayablePattern {
	private boolean entered;
	private int movdir; 
	private float bulletspeed;
	private boolean active;
	
	public AnBasicFairy(int y, int movdir, float bulletspeed, float bullettimer){
		super();
		
		entered = false;
		this.movdir = movdir;
		this.bulletspeed = bulletspeed;
		this.active = true;
		
		this.addTimer(bullettimer, 0);
		

		hitbox = new CollisionObject(DataManager.leftborder - 32, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.characters, 32, 0, 32, 32);
	}

	@Override
	protected void stepPattern(float d) {
		//movement
		hitbox.setX(hitbox.getX() + movdir * d);

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
	protected boolean hasEnded() {
		return !active;
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

}
