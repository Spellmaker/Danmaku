package de.spellmaker.danmaku.level;

import java.util.ArrayList;

import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.SndBasicPattern;

public class TestLevel implements DanmakuLevel {
	private LevelHandler handler;
	private ArrayList<Pattern> patterns;
	
	public TestLevel(){
		patterns = new ArrayList<Pattern>();
	}
	
	
	@Override
	public void start(LevelHandler handler) {
		this.handler = handler;	
		
		this.patternCreated(new SndBasicPattern());
	}

	@Override
	public void step(float delta) {
		// do nothing
	}

	@Override
	public String getName() {
		return "Testlevel";
	}

	@Override
	public void patternCreated(Pattern p) {
		this.patterns.add(p);
		p.addListener(this);
	}

	@Override
	public void renderLevel(float delta) {
		for(int i = 0; i < patterns.size(); i++){
			
		}
	}

	@Override
	public void renderHitboxes() {
		// TODO Auto-generated method stub
		
	}
}
