package org.dieubware.etts;


import org.dieubware.etts.model.GameModel;
import org.dieubware.etts.view.GameScreen;

import com.badlogic.gdx.Gdx;


public class TimeManager {

	private GameModel model;
	private GameScreen screen;
	private float time = 0f;
	public TimeManager(GameModel model, GameScreen screen) {
		this.model = model;
		this.screen = screen;
	}
	public void manage(float delta) {
		model.movePlayer(delta);
		
		
	}
	
}
