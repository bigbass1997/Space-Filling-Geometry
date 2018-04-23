package com.bigbass.spacefilling.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Triangle implements Shape {
	
	public Vector2 pos;
	public Color color;
	private float sideLength;
	
	private Vector2[] vertiOffsets;

	public Triangle(float x, float y, float area){
		this(x, y, area, Color.WHITE);
	}
	
	public Triangle(float x, float y, float area, Color color){
		pos = new Vector2(x, y);
		
		float h = (float) (area / Math.sqrt(3));
		vertiOffsets = new Vector2[3];
		for(int i = 0; i < 3; i++){
			vertiOffsets[i] = new Vector2(
					h * MathUtils.cosDeg((120 * i) + 210),
					h * MathUtils.sinDeg((120 * i) + 210)
			);
		}
		
		this.sideLength = (float) ((2f * Math.sqrt(area)) / Math.pow(3, 0.25));
		this.color = color;
	}
	
	@Override
	public void render(ShapeRenderer sr){
		sr.setColor(color);
		Vector2[] arr = getVerti();
		sr.triangle(
				arr[0].x, arr[0].y,
				arr[1].x, arr[1].y,
				arr[2].x, arr[2].y
		);
	}

	@Override
	public boolean intersects(Shape shape){
		Polygon poly = new Polygon(getVertiExpanded());
		
		for(Vector2 vec : shape.getVerti()){
			if(poly.contains(vec)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Vector2[] getVerti() {
		Vector2[] arr = new Vector2[3];
		
		for(int i = 0; i < 3; i++){
			arr[i] = new Vector2(pos.x + vertiOffsets[i].x, pos.y + vertiOffsets[i].y);
		}
		
		return arr;
	}
	
	public float[] getVertiExpanded(){
		Vector2[] verti = getVerti();
		float[] arr = new float[6];
		
		for(int i = 0; i < 3; i++){
			arr[i * 2] = verti[i].x;
			arr[(i * 2) + 1] = verti[i].y;
		}
		
		return arr;
	}

	@Override
	public float area() {
		return (float) ((Math.sqrt(3f) / 4f) * sideLength * sideLength);
	}
}
