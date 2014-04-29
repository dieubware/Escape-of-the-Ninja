package org.dieubware.etts.controller;

import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;

import org.dieubware.etts.model.Player;
import org.dieubware.etts.view.GameScreen;




public class PlayerObserver implements Observer {
	
	private GameScreen gameScreen;

	public PlayerObserver(GameScreen gs) {
		this.gameScreen = gs;
	}

	@Override
	public void update(Observable o, Object arg1) {
		Player p =(Player)o;
		gameScreen.setPlayerPos(p.x(), p.y(), p.getDirection());
		
		
	}

}
