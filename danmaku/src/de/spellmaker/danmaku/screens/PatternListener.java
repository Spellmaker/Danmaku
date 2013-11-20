package de.spellmaker.danmaku.screens;

/**
 * Listener for pattern events
 * @author Spellmaker
 *
 */
public interface PatternListener {
	/**
	 * Notifies the listener of the end of a pattern
	 * @param p The pattern which has ended
	 */
	public void patternEnded(Pattern p);
}
