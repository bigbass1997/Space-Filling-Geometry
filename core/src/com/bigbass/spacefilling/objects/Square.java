package com.bigbass.spacefilling.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Square extends Shape {
	
	private final float area;
	
	public Vector2 pos;
	public Vector2 dim;
	
	public Color color;
	
	private static Random rand;
	
	private float theta;
	
	public Square(float x, float y, float area, Color color){
		this.area = area;
		
		pos = new Vector2(x, y);
		dim = new Vector2((float) Math.sqrt(area), (float) Math.sqrt(area));
		pos.x -= (dim.x * 0.5f);
		pos.y -= (dim.y * 0.5f);
		
		this.color = color;
		
		if(rand == null){
			rand = new Random();
		}
		
		theta = rand.nextFloat() * 90;
	}

	@Override
	public void render(ShapeRenderer sr) {
		sr.setColor(color);
		
		Vector2[] verti = getVerti();
		
		ImmediateModeRenderer rend = sr.getRenderer();
		
		rend.color(color);
		rend.vertex(verti[0].x, verti[0].y, 0);
		rend.color(color);
		rend.vertex(verti[1].x, verti[1].y, 0);
		rend.color(color);
		rend.vertex(verti[3].x, verti[3].y, 0);

		rend.color(color);
		rend.vertex(verti[1].x, verti[1].y, 0);
		rend.color(color);
		rend.vertex(verti[2].x, verti[2].y, 0);
		rend.color(color);
		rend.vertex(verti[3].x, verti[3].y, 0);
	}

	@Override
	public Vector2[] getVerti() {
		Vector2[] arr = new Vector2[4];
		
		arr[0] = new Vector2(pos.x, pos.y);
		arr[1] = new Vector2(pos.x, pos.y + dim.y);
		arr[2] = new Vector2(pos.x + dim.x, pos.y + dim.y);
		arr[3] = new Vector2(pos.x + dim.x, pos.y);
		
		//rotate each point about center of square
		Vector2 center = new Vector2(pos.x + (dim.x * 0.5f), pos.y + (dim.y * 0.5f));
		
		for(int i = 0; i < arr.length; i++){
			arr[i].sub(center);
			
			arr[i].rotate(theta);
			
			arr[i].add(center);
		}
		
		return arr;
	}

	@Override
	public float area() {
		return area;
	}
}
