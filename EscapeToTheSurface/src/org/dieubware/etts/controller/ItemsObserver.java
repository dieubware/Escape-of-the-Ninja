package org.dieubware.etts.controller;

import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;

import org.dieubware.etts.model.Borders;
import org.dieubware.etts.model.Item;
import org.dieubware.etts.model.Items;
import org.dieubware.etts.model.Player;
import org.dieubware.etts.view.GameScreen;

import com.badlogic.gdx.math.Rectangle;




public class ItemsObserver implements Observer {
	
	private GameScreen gameScreen;
	private int lastItemId = -1;
	private int lastRemovedId = -1;

	public ItemsObserver(GameScreen gs) {
		this.gameScreen = gs;
	}

	@Override
	public void update(Observable o, Object arg1) {
		Items items =(Items)o;
		if(items.getLastItem() != null && lastItemId != items.getLastItem().getId()) {

			Item item = items.getLastItem();
			lastItemId = item.getId();
			gameScreen.addItem(item.getId(),
					item.x,
					item.y,
					item.width,
					item.height,
					item.getType()
					);
		}
		if(lastRemovedId != items.getLastRemovedId()) {
			lastRemovedId = items.getLastRemovedId();
			gameScreen.removeItem(lastRemovedId);
		}
		
		for(Item i : items) {
			
			gameScreen.setItemY(i.getId(), i.y);
		}
		
	}

}
