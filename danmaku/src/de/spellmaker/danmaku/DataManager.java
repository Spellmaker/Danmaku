package de.spellmaker.danmaku;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DataManager {
	private static DataManager manager;
	public GraphicsManager graphics;
	public SpriteBatch batch;
	public ShapeRenderer shape;
	public static final int leftborder = 200;
	public static final int rightborder = 1000;
	public static final int upperborder = 960;
	
	private DataManager(){
		graphics = new GraphicsManager();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
	}
	
	public static DataManager getManager(){
		if(manager == null) manager = new DataManager();
		return manager;
	}
}
