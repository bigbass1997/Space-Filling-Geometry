package com.bigbass.spacefilling;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.bigbass.spacefilling.fonts.FontManager;
import com.bigbass.spacefilling.panel.PanelGroup;
import com.bigbass.spacefilling.panel.TwoDimPanel;
import com.bigbass.spacefilling.panel.MenuPanel;

public class Main extends ApplicationAdapter {
	
	public static final InputMultiplexer inputMultiplexer = new InputMultiplexer();
	
	private PanelGroup panels;
	
	private boolean isScreenshotReady = false;
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		FontManager.addFont("fonts/computer.ttf", new int[]{16,24});
		
		panels = new PanelGroup();
		
		panels.panels.add(new MenuPanel());
		panels.panels.get(0).isVisible(false);
		panels.panels.get(0).isActive(false);
		
		panels.panels.add(new TwoDimPanel());
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		panels.render();
		
		//UPDATE
		update();
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	private void update(){
		float delta = Gdx.graphics.getDeltaTime();
		
		panels.update(delta);
		
		Input input = Gdx.input;
		if(input.isKeyPressed(Keys.S) && isScreenshotReady){
			ScreenshotFactory.saveScreen();
			isScreenshotReady = false;
		} else if(!input.isKeyPressed(Keys.S) && !isScreenshotReady){
			isScreenshotReady = true;
		}
		
		if(input.isKeyJustPressed(Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose(){
		panels.dispose();
	}
}
