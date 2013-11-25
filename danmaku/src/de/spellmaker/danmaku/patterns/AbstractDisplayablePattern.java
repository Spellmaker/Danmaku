package de.spellmaker.danmaku.patterns;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.characters.CollisionObject;

/**
 * Represents a pattern with a displayable core.
 * The constructor must fuel image and hitbox with
 * content
 * @author Spellmaker
 *
 */
public abstract class AbstractDisplayablePattern extends AbstractBasePattern {
	protected TextureRegion image;
	protected CollisionObject hitbox;
	
	/**
	 * Substitute for the step function of AbstractBasePattern
	 * @param d The time passed
	 */
	protected abstract void stepPattern(float d);
	
	public AbstractDisplayablePattern(){
		super();
	}
	
	
	@Override
	protected void step(float d) {
		stepPattern(d);
		SpriteBatch batch = DataManager.getManager().batch;
		batch.draw(image, hitbox.getX(), hitbox.getY());
	}

	@Override
	protected boolean collision(CollisionObject rec) {
		return hitbox.collidesWith(rec);
	}

	@Override
	protected void patternHitbox() {
		hitbox.render();
	}
}
