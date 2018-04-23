package com.bigbass.spacefilling.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bigbass.spacefilling.Main;
import com.bigbass.spacefilling.skins.SkinManager;

public class MenuPanel extends Panel {
	
	private Camera cam;
	private Stage stage;
	private ShapeRenderer sr;
	
	private Label infoLabel;
	
	public MenuPanel() {
		super();
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();
		
		stage = new Stage();
		Main.inputMultiplexer.addProcessor(stage);
		
		infoLabel = new Label("", SkinManager.getSkin("fonts/computer.ttf", 24));
		infoLabel.setColor(Color.BLACK);
		stage.addActor(infoLabel);
		
		sr = new ShapeRenderer(500000);
		sr.setAutoShapeType(true);
		sr.setProjectionMatrix(cam.combined);
	}
	
	public void render() {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(-(cam.viewportWidth * 0.5f), -(cam.viewportHeight * 0.5f), Gdx.graphics.getWidth() * 2, Gdx.graphics.getHeight() * 2);
		sr.end();
		
		panelGroup.render();
		
		stage.draw();
		
		/*sr.begin(ShapeType.Filled);
		sr.setColor(Color.FIREBRICK);
		renderDebug(sr);
		sr.end();*/
	}
	
	public void update(float delta) {
		stage.act(delta);
		
		panelGroup.update(delta);
		
		String info = String.format("Data:%n  FPS: %s",
				Gdx.graphics.getFramesPerSecond()
			);
		
		infoLabel.setText(info);
		infoLabel.setPosition(10, Gdx.graphics.getHeight() - (infoLabel.getPrefHeight() / 2) - 5);
	}
	
	public void dispose(){
		stage.dispose();
		sr.dispose();
		panelGroup.dispose();
	}
}