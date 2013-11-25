package de.spellmaker.danmaku.old;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.PatternListener;

public class VectorBullet implements Pattern {
	private TextureRegion image;
	private CollisionObject hitbox;
	private float vx;
	private float vy;
	private boolean active;
	
	public VectorBullet(float f, float g, float h, float i){
		hitbox = new CollisionObject(f, g, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.bullets, 0, 0, 32, 32);
		this.vx = h;
		this.vy = i;
		this.active = true;
	}
	
	@Override
	public void render(float d) {
		if(active){
			hitbox.setX(hitbox.getX() + d * this.vx);
			hitbox.setY(hitbox.getY() + d * this.vy);
			
			SpriteBatch batch = DataManager.getManager().batch;
			batch.draw(image, hitbox.getX(), hitbox.getY());
			
			active = !((hitbox.getY() <= 0 || hitbox.getY() >= Options.screen_height || hitbox.getX() <= DataManager.leftborder || hitbox.getX() >= DataManager.rightborder));
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
		//do nothing
	}

	@Override
	public void removeListener(PatternListener pl) {
		//do nothing
	}
}
