package com.bigbass.spacefilling.sim;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.bigbass.spacefilling.objects.Circle;
import com.bigbass.spacefilling.objects.Shape;

public class CircleSimulation {
	
	private ArrayList<Shape> shapes;
	
	private final float initArea;
	private final float constant;
	private final int limitN;
	
	private final int maxTries = 100000000;
	private int mostTries = 0;
	
	private Vector2 pos;
	private Vector2 dim;
	
	private Random rand;
	
	public CircleSimulation(float initArea, float constant, int limitN, float x, float y, float width, float height){
		shapes = new ArrayList<Shape>();
		
		this.initArea = initArea;
		this.constant = constant;
		this.limitN = limitN;
		this.pos = new Vector2(x, y);
		this.dim = new Vector2(width, height);
		
		rand = new Random();
	}
	
	public void render(ShapeRenderer sr){
		sr.set(ShapeType.Filled);
		for(Shape shape : shapes){
			shape.render(sr);
		}
	}
	
	public void iterate(){
		if(shapes.size() >= limitN){
			return;
		}
		
		for(int bbb = 0; bbb < 10; bbb++){
			float area = 0;
			if(shapes.size() == 0){
				area = initArea;
			} else {
				area = (float) (initArea * Math.max(0, Math.pow(shapes.size(), -constant)));
			}
			
			Color color = new Color();
			color.fromHsv(120f + (rand.nextFloat() * 60f) + (50f * (1-(shapes.size() / 5000f))), 1, 1);
			color.a = 1;
			
			int tries = 0;
			while(true){
				float x = rand.nextFloat() * dim.x;
				float y = rand.nextFloat() * dim.y;
				
				Circle c = new Circle(pos.x + x, pos.y + y, area, color);
				
				boolean intersects = false;
				for(Shape other : shapes){
					if(other.intersects(c)){
						intersects = true;
						break;
					}
				}
				
				if(!intersects){
					shapes.add(c);
					mostTries = Math.max(mostTries, tries);
					break;
				}
				
				tries++;
				if(tries > maxTries){
					return;
				}
			}
		}
	}
	
	public int getCount(){
		return shapes.size();
	}
	
	public float getTryPercentage(){
		return (float) mostTries / (float) maxTries;
	}
	
	public int getMostTries(){
		return mostTries;
	}
}
