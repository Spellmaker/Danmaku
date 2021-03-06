package de.spellmaker.danmaku.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import de.spellmaker.danmaku.DataManager;

public class CollisionObject {	
	private Rectangle hitbox;
	private float x;
	private float y;
	private int hbx;
	private int hby;
	
	public CollisionObject(float f, float g, int hbx, int hby, int hbw, int hbh){
		this.x = f;
		this.y = g;
		this.hitbox = new Rectangle(f + hbx, g + hby, hbw, hbh);
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
	
	public void render(){
		ShapeRenderer shape = DataManager.getManager().shape;
		shape.setColor(Color.BLUE);
		shape.begin(ShapeType.FilledRectangle);
		shape.filledRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		shape.end();
	}
}
