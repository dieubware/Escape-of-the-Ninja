package org.dieubware.etts.model;

import org.dieubware.etts.Observable;

import org.dieubware.etts.Constants;

import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.bcel.internal.classfile.Constant;

public class ScoreManager extends Observable {

	private int score;
	private int highscore;
	private boolean beatenHS = false;
	private boolean beating= false;
	
	public ScoreManager() {
	}
	
	public void addScore(int score) {
		this.score += score;
		if(this.score > highscore) {
			if(this.highscore != 0 && !beatenHS && !beating) {
				beatenHS = true;
				beating = true;
			}
			highscore = this.score;
			
		}
		setChanged();
		notifyObservers();
	}
	
	public void resetScore() {
		this.score = 0;
		beating = false;
		beatenHS = false;
		setChanged();
		notifyObservers();
	}
	
	public int getScore() {
		return this.score/Constants.textureSize;
	}

	public int getHighscore() {

		return highscore/Constants.textureSize;
	}
	public void setBeatenHS(boolean b) {
		beatenHS = b;
	}
	public boolean isBeatenHS() {
		return beatenHS;
	}
	
}
