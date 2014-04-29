package org.dieubware.etts.controller;


import org.dieubware.etts.model.GameModel;
import org.dieubware.etts.view.GameScreen;
import org.dieubware.etts.view.GameScreen.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class StageListener extends InputListener{

	private GameModel model;
	private GameScreen screen;

	public StageListener(GameModel model, GameScreen screen) {
		this.model = model;
		this.screen = screen;
	}


	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		act();
		return true;
	}


	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		switch(keycode) {
		case Input.Keys.S:
			screen.toggleMusic();
			break;
		default:
			act();
			break;
		}

		return super.keyDown(event, keycode);
	}

	private void act() {
		if(screen.getState() == State.PLAYING) {

			model.jump();
			screen.getPlayerActor().setPushAnimation();
			screen.playJumpSound();

		}
		else if(screen.getState() == State.WAITING) {
			screen.setState(State.PLAYING);
			screen.getTapToStart().addAction(Actions.alpha(0f,0.3f));
			screen.getPlayerActor().setJumpAnimation();
			model.jump();
		}
		else if(screen.getState() == State.LOST) {
			screen.getLostImage().setVisible(false);
			screen.clearBorders();
			screen.clearItems();
			model.setLost(false);
			screen.getPlayerActor().setJumpAnimation();
			screen.setState(State.PLAYING);
			model.jump();
		}
	}



}
