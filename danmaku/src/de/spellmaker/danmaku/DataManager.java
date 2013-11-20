package de.spellmaker.danmaku;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DataManager {
	private static DataManager manager;
	public GraphicsManager graphics;
	public SpriteBatch batch;
	
	private DataManager(){
		graphics = new GraphicsManager();
		batch = new SpriteBatch();
	}
	
	public static DataManager getManager(){
		if(manager == null) manager = new DataManager();
		return manager;
	}
}
