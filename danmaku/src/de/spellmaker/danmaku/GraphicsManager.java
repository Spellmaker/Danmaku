package de.spellmaker.danmaku;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GraphicsManager {
	public Texture bullets;
	public Texture characters;
	public Texture levelbackground;
	
	public GraphicsManager(){
		bullets = new Texture(Gdx.files.internal("images\\bullets.png"));
		characters = new Texture(Gdx.files.internal("images\\characters.png"));
		levelbackground = new Texture(Gdx.files.internal("images\\game_back.png"));
	}
}
