package com.bigbass.spacefilling.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
	
	public abstract void render(ShapeRenderer sr);
	
	/**
	 * Retrieves the verti that make up the shape. Can vary in quantity.
	 * 
	 * @return array of Vector2, each representing one vertex of the shape
	 */
	public abstract Vector2[] getVerti();
	
	public abstract float area();
	
	/**
	 * Source: http://paulbourke.net/fractals/randomtile/
	 * 
	 * @param s
	 * @return
	 */
	public boolean intersects(Shape s){
		Vector2[] sVerti = s.getVerti();
		
		for(int i = 0; i < sVerti.length; i++){
			if(pointInside(sVerti[i])){
				return true;
			}
		}

		Vector2[] thisVerti = this.getVerti();
		for(int i = 0; i < thisVerti.length; i++){
			if(s.pointInside(thisVerti[i])){
				return true;
			}
		}

		int sVertiLen = sVerti.length;
		int thisVertiLen = thisVerti.length;
		for(int i = 0; i < sVerti.length; i++){
			for(int j = 0; j < thisVerti.length; j++){
				if(lineIntersect(sVerti[i], sVerti[(i + 1) % sVertiLen], thisVerti[j], thisVerti[(j + 1) % thisVertiLen])){
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Source: http://paulbourke.net/fractals/randomtile/
	 * 
	 * @param point
	 * @return
	 */
	private boolean pointInside(Vector2 point){
		float angle = 0;
		Vector2 p1 = new Vector2(0, 0);
		Vector2 p2 = new Vector2(0, 0);
		
		Vector2[] verti = this.getVerti();
		int n = verti.length;
		
		for(int i = 0; i < n; i++){
			p1.x = verti[i].x - point.x;
			p1.y = verti[i].y - point.y;
			p2.x = verti[(i + 1) % n].x - point.x;
			p2.y = verti[(i + 1) % n].y - point.y;
			angle += p1.angleRad(p2);
		}
		
		return !(Math.abs(angle) < Math.PI);
	}
	
	/**
	 * Source: http://paulbourke.net/fractals/randomtile/
	 * 
	 * @param v1
	 * @param v2
	 * @param v3
	 * @param v4
	 * @return
	 */
	private boolean lineIntersect(Vector2 v1, Vector2 v2, Vector2 v3, Vector2 v4){
		float denom = (v4.y - v3.y) * (v2.x - v1.x) - (v4.x - v3.x) * (v2.y - v1.y);
		float numera = (v4.x - v3.x) * (v1.y - v3.y) - (v4.y - v3.y) * (v1.x - v3.x);
		float numerb = (v2.x - v1.x) * (v1.y - v3.y) - (v2.y - v1.y) * (v1.x - v3.x);
		
		final float epsilon = 0.1f;
		
		if(Math.abs(numera) < epsilon && Math.abs(numerb) < epsilon && Math.abs(denom) < epsilon){
			return true;
		}
		
		if(Math.abs(denom) < epsilon){
			return false;
		}
		
		float mua = numera / denom;
		float mub = numerb / denom;
		if(mua < 0 || mua > 1 || mub < 0 || mub > 1){
			return false;
		}
		
		return true;
	}
}
