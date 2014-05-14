package org.dieubware.etts.model;

import java.util.HashMap;
import java.util.Map;
import org.dieubware.etts.Observable;
import java.util.Set;

import org.dieubware.etts.Constants;

import com.badlogic.gdx.math.Rectangle;

public class Borders extends Observable {

	private Map<Integer, Rectangle> rightRectangles;
	private Map<Integer, Rectangle> leftRectangles;
	private Rectangle lastBorder;
	private int lastRemovedId;
	private int lastBorderId;
	private boolean lastBorderSide;
	private static int ID = 1;

	public Borders() {
		rightRectangles = new HashMap<Integer, Rectangle>();
		leftRectangles =  new HashMap<Integer, Rectangle>();
	}

	public void addBorder(float x, float y, float width,float height, boolean right) {
		Rectangle r = new Rectangle(x,y,width, height);
		int id = ID++;
		if(!right) {
			id = -id;
		}
		if(right) {
			rightRectangles.put(id, r);
		}
		else {
			leftRectangles.put(id, r);
		}
		lastBorder = r;
		lastBorderId = id;
		lastBorderSide = right;
		setChanged();
		notifyObservers();
	}

	public void scroll(float y) {
		for(Integer i : rightRectangles.keySet()) {
			Rectangle r = rightRectangles.get(i);
			r.y += y;
		}
		for(Integer i : leftRectangles.keySet()) {
			Rectangle r = leftRectangles.get(i);
			r.y += y;
		}
		setChanged();
		notifyObservers();
	}

	public Set<Integer> leftKeySet() {
		return leftRectangles.keySet();
	}
	public Set<Integer> rightKeySet() {
		return rightRectangles.keySet();
	}

	public Rectangle get(int i) {
		if(i > 0)
			return rightRectangles.get(i);
		else
			return leftRectangles.get(i);
	}

	public Rectangle getLastBorder() {
		return lastBorder;
	}

	public int getLastBorderId() {
		return lastBorderId;
	}

	public void clear() {
		rightRectangles.clear();
		leftRectangles.clear();
	}

	public void clearRight() {
		int toRm = 0;
		for(Integer i : rightRectangles.keySet()) {
			Rectangle r = rightRectangles.get(i);
			if(r.y + r.height < 0) {
				toRm = i;
			}
		}
		
		rightRectangles.remove(toRm);
		lastRemovedId = toRm;
		setChanged();
		notifyObservers();
		
	}
	public void clearLeft() {
		int toRm = 0;
		for(Integer i : leftRectangles.keySet()) {
			Rectangle r = leftRectangles.get(i);
			if(r.y + r.height < 0) {
				toRm = i;
			}
		}
		leftRectangles.remove(toRm);
		lastRemovedId = toRm;
		setChanged();
		notifyObservers();
	}

	public int getLastRemovedId() {
		return lastRemovedId;
	}

	public boolean getLastBorderSide() {
		return lastBorderSide;
	}

}
