package de.spellmaker.danmaku.patterns;

import de.spellmaker.danmaku.characters.CollisionObject;


/**
 * Basic pattern interface
 * represents a pattern of bullets
 * @author Spellmaker
 *
 */
public interface Pattern {
	/**
	 * Starts the pattern by giving it an id.
	 * Id needs to be positive
	 * @param id The id of the pattern
	 */
	public void start(int id);
	/**
	 * Method to find out the pattern id
	 * @return The pattern id
	 */
	public int getID();
	/**
	 * Draw the pattern on screen
	 * this will draw all bullets and effects belonging to this pattern
	 * @param d The time passed since the last render
	 */
	public void render(float d);
	/**
	 * @return True if the pattern is still active
	 */
	public boolean isAlive();
	
	/**
	 * Render hitboxes of all bullets in this pattern
	 */
	public void renderHitboxes();
	/**
	 * Method to obtain the name of the pattern for display purposes
	 * @return The name of the pattern (if it has one) or an empty string
	 */
	public String getName();
	/**
	 * Method to find out the remaining time of the pattern
	 * @return The time this pattern remains or -1 if not applicable
	 */
	public float timeRemaing();
	/**
	 * Registers a pattern listener which will listen to events
	 * @param pl The listener
	 */
	public void addListener(PatternListener pl);
	/**
	 * Removes a listener
	 * @param pl The listener
	 */
	public void removeListener(PatternListener pl);
	/**
	 * Finds out, if a part of the pattern collides with
	 * the given rectangle
	 * @param rec The bounding collision object
	 * @return
	 */
	public boolean collidesWith(CollisionObject rec);
}
