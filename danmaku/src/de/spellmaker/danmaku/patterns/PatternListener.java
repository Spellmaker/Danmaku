package de.spellmaker.danmaku.patterns;


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
	//public void patternEnded(Pattern p);
	/**
	 * Notifies the listener of the creation of a
	 * new pattern
	 * @param p The created pattern
	 */
	public void patternCreated(Pattern p);
}
