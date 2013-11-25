package de.spellmaker.danmaku.level;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.patterns.Pattern;
import de.spellmaker.danmaku.patterns.SndBasicPattern;

public class TestLevel implements DanmakuLevel {
	private LevelHandler handler;
	private ArrayList<Pattern> patterns;
	private Texture background;
	
	public TestLevel(){
		background = DataManager.getManager().graphics.levelbackground;
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
		//draw background
		DataManager.getManager().batch.draw(background, 0, 0, 0, 0, Options.screen_width, Options.screen_height);
		//render patterns
		renderPatterns(delta);
		DataManager.getManager().batch.end();
	}
	
	private void renderPatterns(float f){
		for(int i = patterns.size() - 1; i >= 0; i--){
			Pattern p = patterns.get(i);
			p.render(f);
			if(!p.isAlive()){
				patterns.remove(i);
			}
		}
	}

	@Override
	public void renderHitboxes() {
		for(int i = 0; i < patterns.size(); i++){
			patterns.get(i).renderHitboxes();
		}
	}
}
