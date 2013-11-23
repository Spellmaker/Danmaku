package de.spellmaker.danmaku.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.spellmaker.danmaku.DataManager;
import de.spellmaker.danmaku.Options;

public class Player {
	private CollisionObject hitbox;
	private TextureRegion sprite;
	private boolean focus;
	
	public Player(){
		int middle = DataManager.leftborder + (DataManager.rightborder - DataManager.leftborder) / 2;
		hitbox = new CollisionObject(middle, 50, 8, 16, 16, 24);
		sprite = new TextureRegion(DataManager.getManager().graphics.characters, 0, 0, 32, 64);
	}
	
	public void setFocus(boolean f){
		this.focus = f;
	}
	
	public void moveLeft(float delta){
		move(delta, -1, 0);
	}
	
	public void moveRight(float delta){
		move(delta, 1, 0);
	}
	
	public void moveUp(float delta){
		move(delta, 0, 1);
	}
	
	public void moveDown(float delta){
		move(delta, 0, -1);
	}
	
	private void move(float delta, float dx, float dy){
		float v = 1;
		if(focus) v *= 0.5;
		
		hitbox.setX(hitbox.getX() + v * delta * dx * Options.player_movspeed);
		hitbox.setY(hitbox.getY() + v * delta * dy * Options.player_movspeed);
		
		if(hitbox.getX() > DataManager.rightborder - 32){
			hitbox.setX(DataManager.rightborder - 32);
		}
		else if(hitbox.getX() < DataManager.leftborder){
			hitbox.setX(DataManager.leftborder);
		}
		
		if(hitbox.getY() > Options.screen_height){
			hitbox.setY(Options.screen_height);
		}
		else if(hitbox.getY() < 0){
			hitbox.setY(0);
		}
	}
	
	public void render(float delta){
		SpriteBatch batch = DataManager.getManager().batch;
		batch.draw(sprite, hitbox.getX(), hitbox.getY(), 32, 64);
	}
	
	public void renderHitbox(){
			hitbox.render();
	}
	
	public float getX(){
		return this.hitbox.getX();
	}
	
	public float getY(){
		return this.hitbox.getY();
	}
}
