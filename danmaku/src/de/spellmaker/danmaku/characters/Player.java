package de.spellmaker.danmaku.characters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.spellmaker.danmaku.DataManager;

public class Player {
	private CollisionObject hitbox;
	private TextureRegion sprite;
	
	public Player(){
		int middle = DataManager.leftborder + (DataManager.rightborder - DataManager.leftborder) / 2;
		hitbox = new CollisionObject(middle, 50, 8, 16, 24, 16);
	}
	
	public void moveLeft(float delta){
		
	}
	
	public void moveRight(float delta){
		
	}
}
