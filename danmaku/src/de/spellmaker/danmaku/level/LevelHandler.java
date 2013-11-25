package de.spellmaker.danmaku.level;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.characters.Player;

public class LevelHandler {
	private ArrayList<DanmakuLevel> levels;
	private OrthographicCamera camera;
	private static Player player;
	
	private enum LevelState{
		LEVEL,
		SCORE,
		NEXT,
		FINISHED
	}
	
	private LevelState state;
	
	public LevelHandler(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.screen_width, Options.screen_height);	
		player = new Player();
		levels = new ArrayList<DanmakuLevel>();
		state = LevelState.FINISHED;
		//add levels here
		levels.add(new TestLevel());
		nextLevel();
	}
	
	public void endLevel(){
		state = LevelState.SCORE;
	}
	
	public void nextLevel(){
		if(levels.size() > 0){
			state = LevelState.LEVEL;
			levels.get(0).start(this);
		}
		else{
			state = LevelState.FINISHED;
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
			case FINISHED:
				//TODO: Handle end of game here
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
		//render the actual level
		levels.get(0).renderLevel(delta);
		if(Options.showHitboxes){
			levels.get(0).renderHitboxes();
			player.renderHitbox();
		}
	}
}
