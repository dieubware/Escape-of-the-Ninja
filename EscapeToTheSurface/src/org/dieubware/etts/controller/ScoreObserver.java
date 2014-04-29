package org.dieubware.etts.controller;

import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;

import org.dieubware.etts.GameInterface;
import org.dieubware.etts.model.GameModel;
import org.dieubware.etts.model.Player;
import org.dieubware.etts.model.ScoreManager;
import org.dieubware.etts.view.GameScreen;
import org.dieubware.etts.view.GameScreen.State;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;




public class ScoreObserver implements Observer {
	
	private GameScreen gameScreen;
	public ScoreObserver(GameScreen gs) {
		this.gameScreen = gs;
	}

	@Override
	public void update(Observable o, Object arg1) {
		ScoreManager p =(ScoreManager)o;
		gameScreen.getHud().setScore(p.getScore());
		
	}

}
