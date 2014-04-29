package org.dieubware.etts.controller;

import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;

import org.dieubware.etts.model.Borders;
import org.dieubware.etts.model.Player;
import org.dieubware.etts.view.GameScreen;

import com.badlogic.gdx.math.Rectangle;




public class BordersObserver implements Observer {
	
	private GameScreen gameScreen;
	private int lastId = 0;
	private int lastRemoved = 0;

	public BordersObserver(GameScreen gs) {
		this.gameScreen = gs;
	}

	@Override
	public void update(Observable o, Object arg1) {
		Borders borders =(Borders)o;
		if(lastId != borders.getLastBorderId()) {
			lastId = borders.getLastBorderId();
			Rectangle r = borders.getLastBorder();
			gameScreen.addBorder(lastId, r.x, r.y, r.height, borders.getLastBorderSide());
		}
		if(lastRemoved != borders.getLastRemovedId()) {
			lastRemoved = borders.getLastRemovedId();
			gameScreen.removeBorder(lastRemoved);
		}
		for(Integer i : borders.leftKeySet()) {
			gameScreen.setBorderY(i, borders.get(i).y);
		}
		for(Integer i : borders.rightKeySet()) {
			gameScreen.setBorderY(i, borders.get(i).y);
		}
		
	}

}
