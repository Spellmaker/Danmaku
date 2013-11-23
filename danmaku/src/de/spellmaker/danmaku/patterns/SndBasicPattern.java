package de.spellmaker.danmaku.patterns;

import de.spellmaker.danmaku.characters.CollisionObject;
import de.spellmaker.danmaku.patterns.familiars.SndbasicFairy;

public class SndBasicPattern extends BasePattern {
	public SndBasicPattern(){
		addTimer(3, 0);
		addTimer(1.5f, 3, 1);
	}
	
	@Override
	protected void step(float d) {
		//do nothing
	}

	@Override
	protected void patternEnd(int i) {
		//do nothing
	}

	@Override
	protected boolean collision(CollisionObject rec) {
		return false;
	}

	@Override
	protected boolean hasEnded() {
		return false;
	}

	@Override
	public String getName() {
		return "Testing sign - Master debugger";
	}

	@Override
	public float timeRemaing() {
		return -1;
	}

	@Override
	public void timerEvent(int id) {
		if(id == 0){
			patternCreated(new SndbasicFairy(500, 100, 250, 1f));
			
		}
		else if(id == 1){
			patternCreated(new SndbasicFairy(500, 100, 100, 0.5f));
		}
	}

	@Override
	protected void patternHitbox() {
		//do nothing
	}
}
