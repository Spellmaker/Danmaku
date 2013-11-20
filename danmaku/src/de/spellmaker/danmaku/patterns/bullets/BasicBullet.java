package de.spellmaker.danmaku.patterns.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import de.spellmaker.danmaku.DataManager;

public class BasicBullet implements Bullet {
	private TextureRegion image;
	private Rectangle hitbox;
	
	public BasicBullet(int x, int y){
		hitbox = new Rectangle(x + 8, y + 8, 32 - 16, 32 - 16);
		image = new TextureRegion(DataManager.getManager().graphics.bullets, 0, 0, 32, 32);
	}
	
	@Override
	public boolean render(float d) {
		hitbox.y -= d * 50;
		
		SpriteBatch batch = DataManager.getManager().batch;
		batch.draw(image, hitbox.x - 8, hitbox.y - 8);
		return hitbox.y <= 0;
	}

	@Override
	public boolean collidesWith(Rectangle rec) {
		return rec.overlaps(rec);
	}

}
