package org.dieubware.etts.model;

import org.dieubware.etts.model.Items.ItemType;

import com.badlogic.gdx.math.Rectangle;

public class Item extends Rectangle{
	private int id;
	private ItemType type;
	
	
	public Item(float x, float y, float width, float height, int id, ItemType type) {
		super(x, y, width, height);
		this.id = id;
		this.type = type;
	}
	public Item(Rectangle rect, int id, ItemType type) {
		super(rect);
		this.id = id;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}

}
