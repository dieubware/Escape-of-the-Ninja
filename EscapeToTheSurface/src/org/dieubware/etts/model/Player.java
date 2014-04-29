package org.dieubware.etts.model;

import org.dieubware.etts.Observable;

import com.badlogic.gdx.math.Rectangle;

public class Player extends Observable {

	private Rectangle r;
	private boolean direction = true;
	
	public Player(float x, float y, float width, float height) {
		this.r = new Rectangle(x,y,width,height);
	}
	
	public float x() {
		return r.x;
		
	}
	
	public float y() {
		return r.y;
		
	}
	
	public void x(float x) {
		this.r.x = x;
		setChanged();
		notifyObservers();
	}
	
	public void y(float y) {
		this.r.y = y;
		setChanged();
		notifyObservers();
	}
	
	public void pos(float x, float y) {
		this.x(x);
		this.y(y);
	}

	public Rectangle getR() {
		return this.r;
	}
	
	public boolean getDirection() {
		return direction;
	}
	public void setDirection(boolean b) {
		this.direction = b;
	}
}
