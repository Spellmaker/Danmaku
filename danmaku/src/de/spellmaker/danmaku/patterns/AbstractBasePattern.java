package de.spellmaker.danmaku.patterns;

import java.util.ArrayList;


import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.timing.TimerListener;
import de.spellmaker.danmaku.timing.Timer;

/**
 * Base pattern class, implements a lot of functions which
 * normally would have to be implemented anew for each pattern.
 * If possible, new patterns should inherit from this class
 * @author Spellmaker
 *
 */
public abstract class AbstractBasePattern implements Pattern, PatternListener, TimerListener {
	private ArrayList<PatternListener> listeners;
	private ArrayList<Pattern> patterns;
	private ArrayList<Timer> timers;
	private boolean isAlive;
	private int id;

	/**
	 * Process a step of length d of this pattern. Pattern specific
	 * actions like movement, bullet spawning etc happens in here
	 * @param d The amount of time passed
	 */
	protected abstract void step(float d);
	/**
	 * Is called each time a subpattern has ended. The subpattern
	 * is automatically removed from the list of patterns, but
	 * pattern specific actions can happen here (e.g. spawning
	 * new bullets at the position of the disappeared one)
	 * @param i The index of the disappeared bullet
	 */
	protected abstract void patternEnd(int i);
	/**
	 * A pattern might have additional collision constraints other
	 * than "player hitbox intersects one bullet of the pattern"
	 * These can be handled in here
	 * @param rec The CollisionObject of the player
	 * @return True, if there is a collision
	 */
	protected abstract boolean collision(CollisionObject rec);
	/**
	 * A pattern might still be active, after all bullets were removed.
	 * This function determines, whether this is the case
	 * @return Whether the pattern is still active
	 */
	protected abstract boolean hasEnded();
	/**
	 * A pattern can have it's own hitbox. This is rendered here
	 */
	protected abstract void patternHitbox();
	@Override
	public abstract String getName();
	@Override
	public abstract float timeRemaing();
	@Override
	public abstract void timerEvent(int id);
	
	protected void notifyListenersCreation(Pattern p){
		for(int i = 0; i < listeners.size(); i++) listeners.get(i).patternCreated(p);
	}

	protected void addTimer(float time, int id){
		this.timers.add(new Timer(time, id, this));
	}
	
	protected void addTimer(float offset, float time, int id){
		this.timers.add(new Timer(offset, time, id, this));
	}
	
	public AbstractBasePattern(){
		listeners = new ArrayList<PatternListener>();
		patterns = new ArrayList<Pattern>();
		timers = new ArrayList<Timer>();
		isAlive = true;
		id = -1;
	}
	
	@Override
	public void patternCreated(Pattern p) {
		p.addListener(this);
		patterns.add(p);
	}

	@Override
	public void render(float d) {
		if(isAlive && id >= 0){
			//process pattern specific steps
			step(d);
			//render subpatterns
			for(int i = 0; i < patterns.size(); i++){
				patterns.get(i).render(d);
			}
			isAlive = !hasEnded();
			//handle removed nodes
			for(int i = 0; i < patterns.size(); i++){
				if(!patterns.get(i).isAlive()){
					patternEnd(i);
					patterns.remove(i);
					i--;
				}
				else{
					isAlive = true;
				}
			}
			//process timers
			for(int i = 0; i < timers.size(); i++){
				timers.get(i).step(d);
			}
		}
	}

	@Override
	public boolean isAlive() {
		return isAlive || (id < 0);
	}

	@Override
	public void renderHitboxes() {
		if(id >= 0){
			for(int i = 0; i < patterns.size(); i++){
				patterns.get(i).renderHitboxes();
			}
			patternHitbox();
		}
	}

	@Override
	public void addListener(PatternListener pl) {
		if(!this.listeners.contains(pl)) this.listeners.add(pl);
	}

	@Override
	public void removeListener(PatternListener pl) {
		this.listeners.remove(pl);
	}
	
	@Override
	public boolean collidesWith(CollisionObject rec) {
		if(id < 0) return false;
		for(int i = 0; i < patterns.size(); i++){
			if(patterns.get(i).collidesWith(rec)) return true;
		}
		return collision(rec);
	}

	@Override
	public void start(int id){
		if(id < 0) throw new IllegalArgumentException("id has to be positive");
		this.id = id;
	}
	
	@Override
	public int getID(){
		return this.id;
	}
}
