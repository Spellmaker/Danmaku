package de.spellmaker.danmaku.patterns.bullets;


import de.spellmaker.danmaku.characters.CollisionObject;

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
	 */
	public void render(float d);
	/**
	 * Checks for collisions with this bullet
	 * @param rec The bounding box for which collisions should be detected
	 * @return True, if there is a collision with this bullet
	 */
	public boolean collidesWith(CollisionObject rec);
	/**
	 * Render Hitboxes
	 */
	public void renderHitbox();
	/**
	 * @return True, if the bullet is still active
	 */
	public boolean isAlive();
}
