package org.dieubware.etts.controller;

import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;
import org.dieubware.etts.GameInterface;
import org.dieubware.etts.model.GameModel;
import org.dieubware.etts.model.Player;
import org.dieubware.etts.view.GameScreen;
import org.dieubware.etts.view.GameScreen.State;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;




public class ModelObserver implements Observer {
	
	private GameScreen gameScreen;
	private GameInterface gameInterface;

	public ModelObserver(GameScreen gs, GameInterface gi) {
		this.gameScreen = gs;
		this.gameInterface = gi;
	}

	@Override
	public void update(Observable o, Object arg1) {
		GameModel p =(GameModel)o;
		if(p.isLost()) {
			p.saveScore();
			gameScreen.playHitSound();
			gameScreen.setState(State.LOST);
			gameScreen.getPlayerActor().setIddleAnimation();
			gameScreen.getHud().setHighScore(p.getHighscore());
			gameScreen.getHud().setLost(true);
			gameScreen.getLostImage().setVisible(true);
			gameScreen.playDeathAnimation();
		}
		else{
			gameInterface.setTestLevel();
			p.resetScore();
			gameScreen.getHud().setLost(false);
		}
		
		
	}

}
