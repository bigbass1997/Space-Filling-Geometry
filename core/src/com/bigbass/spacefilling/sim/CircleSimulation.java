package com.bigbass.spacefilling.sim;

import com.badlogic.gdx.graphics.Color;
import com.bigbass.spacefilling.objects.Circle;
import com.bigbass.spacefilling.objects.Shape;

public class CircleSimulation extends Simulation {
	
	public CircleSimulation(float initArea, float constant, int limitN, float x, float y, float width, float height){
		super(initArea, constant, limitN, x, y, width, height);
	}
	
	@Override
	public void iterate(){
		// If too many shapes exist, do not create any more
		if(shapes.size() >= limitN){
			return;
		}
		
		// Prepare size (area) of shape
		float area = 0;
		if(shapes.size() == 0){
			area = initArea;
		} else {
			area = calcNextArea();
		}
		
		// Prepare color of shape
		Color color = new Color();
		color.fromHsv(120f + (rand.nextFloat() * 60f) + (50f * (1-(shapes.size() / 5000f))), 1, 1);
		color.a = 1;
		
		// Start shape creation process
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
				mostTries = Math.max(mostTries, tries); // here for debugging, not actually used in simulation
				break;
			}
			
			tries++;
			if(tries > maxTries){
				return;
			}
		}
	}
}
