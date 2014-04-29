package org.dieubware.etts.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dieubware.etts.Observable;
import java.util.Set;

import org.dieubware.etts.Constants;
import org.dieubware.etts.model.Items.ItemType;

import com.badlogic.gdx.math.Rectangle;

public class Items extends Observable implements Iterable<Item> {

	private List<Item> items;
	private Item lastItem;
	private int lastRemovedId;
	private static int ID = 1;
	
	public enum ItemType {
		KILL, FLY;

		public static ItemType random() {
			int val = (int)(Math.random() *2);
			if(val == 0)
				return KILL;
			else
				return FLY;
		}
	}

	public Items() {
		items = new ArrayList<Item>();
	}

	public void addItem(float x, float y,float width, float height, ItemType type) {
		
		int id = ID++;
		Item item = new Item(x,y,width,height,id,type);
		
		lastItem = item;
		items.add(item);
		setChanged();
		notifyObservers();
	}

	public void scroll(float y) {
		for(Item i : items) {
			i.y += y;
		}

		
		setChanged();
		notifyObservers();
	}


	public Item get(int i) {
		return items.get(i);
	}
	
	public void remove(Item i) {
		lastRemovedId = i.getId();
		items.remove(i);
		
	}

	public int getLastRemoved() {
		return lastRemovedId;
	}
	public Item getLastItem() {
		return lastItem;
	}
	

	public void clear() {
		items.clear();
	}

	@Override
	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public int getLastRemovedId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void clearOld() {
		Item toRm = null;
		for(Item i : items) {
			if(i.y < 0)
				toRm = i;
				
		}
		if(toRm != null ) {
			items.remove(toRm);
			lastRemovedId = toRm.getId();
			setChanged();
			notifyObservers();
		}
	}


}
