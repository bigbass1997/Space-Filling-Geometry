package com.bigbass.spacefilling.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public interface Shape {
	
	void render(ShapeRenderer sr);
	
	boolean intersects(Shape b);
	
	Vector2[] getVerti();
	
	float area();

}
