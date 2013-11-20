package de.spellmaker.danmaku.patterns.bullets;

import com.badlogic.gdx.math.Rectangle;

/**
 * Basic bullet interface
 * Provides methods for stepping and collision detection
 * @author Spellmaker
 *
 */
public interface Bullet {
	/**
	 * Renders the bullet
	 * @param d Passed time
	 * @return True, if the bullet should be removed
	 */
	public boolean render(float d);
	/**
	 * Checks for collisions with this bullet
	 * @param rec The bounding box for which collisions should be detected
	 * @return True, if there is a collision with this bullet
	 */
	public boolean collidesWith(Rectangle rec);
}
