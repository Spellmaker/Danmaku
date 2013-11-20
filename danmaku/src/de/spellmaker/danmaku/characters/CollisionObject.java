package de.spellmaker.danmaku.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class CollisionObject {
	private Rectangle hitbox;
	private float x;
	private float y;
	private int hbx;
	private int hby;
	
	public CollisionObject(int x, int y, int hbx, int hby, int hbw, int hbh){
		this.x = x;
		this.y = y;
		this.hitbox = new Rectangle(x + hbx, y + hby, hbw, hbh);
		this.hbx = hbx;
		this.hby = hby;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
		this.hitbox.x = this.x + hbx;
	}
	
	public void setY(float f){
		this.y = f;
		this.hitbox.y = this.y + hby;
	}
	
	public void setPos(float x, float y){
		this.setX(x);
		this.setY(y);
	}
	
	public boolean collidesWith(CollisionObject co){
		return (this.hitbox.overlaps(co.hitbox));
	}
	
	public void render(SpriteBatch batch){
		//TODO: draw hitbox
	}
}
