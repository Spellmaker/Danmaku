package de.spellmaker.danmaku;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.spellmaker.danmaku.screens.LevelScreen;

public class Danmaku extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	
	@Override
	public void create() {
		this.setScreen(new LevelScreen());
	}

	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose(){
		
	}
}