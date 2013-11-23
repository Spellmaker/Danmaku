package de.spellmaker.danmaku.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.characters.Player;
import de.spellmaker.danmaku.patterns.Pattern;

public class LevelHandler {
	private ArrayList<DanmakuLevel> levels;
	private Texture background;
	private OrthographicCamera camera;
	private ArrayList<Pattern> patterns;
	private static Player player;
	
	private enum LevelState{
		LEVEL,
		SCORE,
		NEXT
	}
	
	private LevelState state;
	
	public LevelHandler(){
		background = DataManager.getManager().graphics.levelbackground;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.screen_width, Options.screen_height);
		patterns = new ArrayList<Pattern>();		
		player = new Player();
		levels = new ArrayList<DanmakuLevel>();
		//add levels here
		
		nextLevel();
	}
	
	public void endLevel(){
		state = LevelState.SCORE;
	}
	
	public void nextLevel(){
		state = LevelState.LEVEL;
		if(levels.size() > 0){
			levels.get(0).start(this);
		}
		else{
			//display game end room
		}
	}
	
	public static Player getPlayer(){
		return player;
	}
	
	public void render(float delta){
		switch(state){
			case LEVEL: 
				handleInput(delta);
				renderLevel(delta);
				break;
			case SCORE: 
				//TODO: Insert a way to render the current score
				state = LevelState.NEXT;
				break;
			case NEXT: 
				levels.remove(0);
				nextLevel();
				break;
		}
	}
	
	public void handleInput(float delta){
		if(Gdx.input.isKeyPressed(Keys.LEFT)) player.moveLeft(delta);
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) player.moveRight(delta);
		if(Gdx.input.isKeyPressed(Keys.UP)) player.moveUp(delta);
		if(Gdx.input.isKeyPressed(Keys.DOWN)) player.moveDown(delta);
		
		player.setFocus(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT));
	}
	
	private void renderLevel(float delta){
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		DataManager.getManager().batch.setProjectionMatrix(camera.combined);
		DataManager.getManager().shape.setProjectionMatrix(camera.combined);
		DataManager.getManager().batch.begin();
		//render player
		player.render(delta);
		//render patterns
		levels.get(0).renderLevel(delta);
		//draw background
		DataManager.getManager().batch.draw(background, 0, 0, 0, 0, Options.screen_width, Options.screen_height);
		DataManager.getManager().batch.end();
		if(Options.showHitboxes) levels.get(0).renderHitboxes();
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
	
	private void renderHitboxes(){
		for(int i = patterns.size() - 1; i >= 0; i--){
			Pattern p = patterns.get(i);
			p.renderHitboxes();
		}
		player.renderHitbox();
	}

	public void addPattern(Pattern p){
		this.patterns.add(p);
	}
}
