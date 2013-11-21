package de.spellmaker.danmaku.patterns.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;

public class BasicBullet implements Bullet {
	private TextureRegion image;
	private CollisionObject hitbox;
	
	public BasicBullet(int x, int y){
		hitbox = new CollisionObject(x, y, 8, 8, 16, 16);
		image = new TextureRegion(DataManager.getManager().graphics.bullets, 0, 0, 32, 32);
	}
	
	@Override
	public boolean render(float d) {
		hitbox.setY(hitbox.getY() - d * 50);
		
		SpriteBatch batch = DataManager.getManager().batch;
		batch.draw(image, hitbox.getX(), hitbox.getY());
		return hitbox.getY() <= 0;
	}

	@Override
	public boolean collidesWith(CollisionObject rec) {
		return hitbox.collidesWith(rec);
	}

	public void renderHitbox(){
		this.hitbox.render();
	}
}
