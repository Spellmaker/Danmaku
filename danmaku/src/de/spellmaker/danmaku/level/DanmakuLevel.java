package de.spellmaker.danmaku.level;

import de.spellmaker.danmaku.patterns.PatternListener;


/**
 * Basic interface for levels
 * A level triggers timing based events by
 * adding and removing patterns from the Levelscreen.
 * Every Level is also a PatternListener and listens
 * to changes in the currently active patterns
 * @author Spellmaker
 *
 */
public interface DanmakuLevel extends PatternListener{
	/**
	 * Starts the pattern
	 */
	public void start(LevelHandler handler);
	/**
	 * Processes a step in the game world
	 * @param delta The amount of time passed
	 */
	public void step(float delta);
	/**
	 * Every level has a name which will be displayed
	 * to the player in the beginning
	 * @return The name of the level
	 */
	public String getName();
	/**
	 * Renders the current Level
	 * @param delta the time passed
	 */
	public void renderLevel(float delta);
	/**
	 * Renders the hitboxes
	 */
	public void renderHitboxes();
}
