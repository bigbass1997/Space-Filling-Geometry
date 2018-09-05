package com.bigbass.spacefilling.panel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bigbass.spacefilling.Main;
import com.bigbass.spacefilling.SimOptions;
import com.bigbass.spacefilling.sim.CircleSimulation;
import com.bigbass.spacefilling.skins.SkinManager;

public class TwoDimPanel extends Panel {

	private Camera cam;
	private Stage stage;
	private ShapeRenderer sr;
	
	private Label infoLabel;
	
	private float scalar = 1;
	
	private CircleSimulation sim;
	
	public TwoDimPanel() {
		super();
		
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();
		
		stage = new Stage();
		Main.inputMultiplexer.addProcessor(stage);
		Main.inputMultiplexer.addProcessor(new ScrollwheelInputAdapter(){
			@Override
			public boolean scrolled(int amount) {
				if(amount == 1){
					changeCameraViewport(0.1f);
				} else if(amount == -1){
					changeCameraViewport(-0.1f);
				}
				return true;
			}
		});
		
		infoLabel = new Label("", SkinManager.getSkin("fonts/computer.ttf", 24));
		infoLabel.setColor(Color.WHITE);
		stage.addActor(infoLabel);
		
		sr = new ShapeRenderer(500000);
		sr.setAutoShapeType(true);
		sr.setProjectionMatrix(cam.combined);
		
		sim = new CircleSimulation(
				200000, 1.4f, 10000, 200, 50, 800, 800
		);
	}
	
	public void render() {
		sr.begin(ShapeType.Filled);
		sr.setColor(SimOptions.getInstance().backgroundColor);
		sr.rect(-(cam.viewportWidth * 0.5f), -(cam.viewportHeight * 0.5f), Gdx.graphics.getWidth() * scalar * 2, Gdx.graphics.getHeight() * scalar * 2);
		
		sim.render(sr);

		sr.set(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.rect(200, 50, 800, 800);
		
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
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			sim.iterate();
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			sim.iterate();
		}
		
		String info = String.format("Data:%n  FPS: %s%n  N: %s%n  %%Try: %.5f%%%n  #Try: %s",
				Gdx.graphics.getFramesPerSecond(),
				sim.getCount(),
				sim.getTryPercentage(),
				sim.getMostTries()
			);
		
		infoLabel.setText(info);
		infoLabel.setPosition(10, Gdx.graphics.getHeight() - (infoLabel.getPrefHeight() / 2) - 5);
	}
	
	public boolean isActive() {
		return true; // Always active
	}
	
	public void dispose(){
		stage.dispose();
		sr.dispose();
		panelGroup.dispose();
	}
	
	private void changeCameraViewport(float dscalar){
		scalar += dscalar;
		
		cam.viewportWidth = Gdx.graphics.getWidth() * scalar;
		cam.viewportHeight = Gdx.graphics.getHeight() * scalar;
		cam.update();

		sr.setProjectionMatrix(cam.combined);
	}
}
