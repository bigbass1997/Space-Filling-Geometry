package com.bigbass.spacefilling;

import com.badlogic.gdx.graphics.Color;

public class SimOptions {
	
	private static SimOptions instance;
	
	public boolean isInverse = false;
	public float inverseRate = -0.5f;
	
	public boolean isChangingSize = false;
	public float changingSizeRate = 0.25f;
	public float changingSizeLimit = 10;
	
	public float particleSize = 10f;
	
	public ColorGenOption colorGen = ColorGenOption.HSV;
	public float colorAlpha = 1;
	
	public Color backgroundColor = new Color(0x002222FF);
	
	public float rotateRate = 0.001f;
	
	public int particlesPerGen = 4;
	public float genRate = 0.01f;
	
	// Not an actual "constant" in this program. Refer to Phyllotaxis algorythm terms.
	public int expandConstant = 6;
	public float rotateTheta = 137.3f;
	
	private SimOptions(){
		
	}
	
	public static SimOptions getInstance(){
		if(instance == null){
			instance = new SimOptions();
		}
		
		return instance;
	}
	
	public static enum ColorGenOption {
		BLACKWHITE,
		HSV,
		RANDOM;
	}
}
