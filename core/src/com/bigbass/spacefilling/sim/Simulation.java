package com.bigbass.spacefilling.sim;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.bigbass.spacefilling.objects.Shape;

public abstract class Simulation {

	protected float initArea;
	protected float constant;
	protected int limitN;
	
	protected Vector2 pos;
	protected Vector2 dim;
	
	protected ArrayList<Shape> shapes;
	
	protected int maxTries;
	protected int mostTries;
	
	protected Random rand;
	
	public Simulation(float initArea, float constant, int limitN, float x, float y, float width, float height){
		this.initArea = initArea;
		this.constant = constant;
		this.limitN = limitN;
		
		this.pos = new Vector2(x, y);
		this.dim = new Vector2(width, height);
		
		shapes = new ArrayList<Shape>();
		
		maxTries = 100000000;
		mostTries = 0;
		
		rand = new Random();
	}
	
	public void render(ShapeRenderer sr){
		sr.set(ShapeType.Filled);
		for(Shape shape : shapes){
			shape.render(sr);
		}
	}
	
	public abstract void iterate();
	
	/**
	 * @return the area of the next shape
	 */
	public float calcNextArea(){
		return (float) (initArea * Math.max(0, Math.pow(shapes.size(), -constant)));
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
