package de.spellmaker.danmaku.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;
import de.spellmaker.danmaku.characters.Player;
import de.spellmaker.danmaku.patterns.BasicPattern;
import de.spellmaker.danmaku.patterns.Pattern;

public class LevelScreen implements Screen{
	private Texture background;
	private OrthographicCamera camera;
	private ArrayList<Pattern> patterns;
	private Player player;
	
	public LevelScreen(){
		background = DataManager.getManager().graphics.levelbackground;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.screen_width, Options.screen_height);
		
		patterns = new ArrayList<Pattern>();
	
		this.addPattern(new BasicPattern());
		
		player = new Player();
	}
	
	@Override
	public void render(float delta) {
		handleInput(delta);
		renderLevel(delta);
	}
	
	private void handleInput(float delta){
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
		DataManager.getManager().batch.begin();
		
		//render player
		this.player.render(delta);
		//render patterns
		this.renderPatterns(delta);
		//draw background
		DataManager.getManager().batch.draw(background, 0, 0, 0, 0, Options.screen_width, Options.screen_height);
		
		DataManager.getManager().batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void addPattern(Pattern p){
		this.patterns.add(p);
	}
	
	private void renderPatterns(float f){
		for(int i = patterns.size() - 1; i >= 0; i--){
			Pattern p = patterns.get(i);
			if(p.render(f)){
				patterns.remove(i);
			}
			
		}
	}
}
