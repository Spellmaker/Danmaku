package de.spellmaker.danmaku.old;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.PatternListener;

public class BasicBullet implements Pattern {
	private TextureRegion image;
	private CollisionObject hitbox;
	private boolean active;
	
	public BasicBullet(int x, int y){
		hitbox = new CollisionObject(x, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.bullets, 0, 0, 32, 32);
		active = true;
	}
	
	@Override
	public void render(float d) {
		if(active){
			hitbox.setY(hitbox.getY() - d * 50);
			
			SpriteBatch batch = DataManager.getManager().batch;
			batch.draw(image, hitbox.getX(), hitbox.getY());
			active = !(hitbox.getY() <= 0);
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
