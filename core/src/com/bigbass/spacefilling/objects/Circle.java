package com.bigbass.spacefilling.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Circle extends Shape {
	
	public float r;
	public Vector2 pos;
	public Color color;

	public Circle(float x, float y, float area){
		this(x, y, area, Color.WHITE);
	}
	
	public Circle(float x, float y, float area, Color color){
		pos = new Vector2(x, y);
		r = (float) Math.sqrt(area / MathUtils.PI);
		
		this.color = color;
	}
	
	@Override
	public void render(ShapeRenderer sr){
		sr.setColor(color);
		sr.circle(pos.x, pos.y, r, Math.max(1, (int)(30 * (float)Math.cbrt(r))));
	}
/*
	@Override
	public boolean intersects(Shape shape){
		if(shape instanceof Circle){
			Circle circle = (Circle) shape;
			return pos.dst(circle.pos) < (r + circle.r);
		} else {
			for(Vector2 point : shape.getVerti()){
				if(pos.dst(point) < r){
					return true;
				}
			}
			
			return false;
		}
	}
*/
	@Override
	public Vector2[] getVerti() {
		Vector2[] arr = new Vector2[Math.max(1, (int)(6 * (float)Math.cbrt(r)))];
		float angle = 360F / arr.length;
		
		for(int i = 0; i < arr.length; i++){
			arr[i] = new Vector2(
					r * MathUtils.cosDeg(angle * i),
					r * MathUtils.sinDeg(angle * i)
			);
		}
		
		return arr;
	}

	@Override
	public float area() {
		return (float) (MathUtils.PI * Math.pow(r, 2));
	}
}
