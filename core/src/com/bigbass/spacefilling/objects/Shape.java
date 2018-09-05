package com.bigbass.spacefilling.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
	
	public abstract void render(ShapeRenderer sr);
	
	public abstract Vector2[] getVerti();
	
	public abstract float area();
	
	public boolean intersects(Shape b){
		return false;
	}
}
