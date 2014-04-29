package org.dieubware.etts.model;

import org.dieubware.etts.Observable;

import org.dieubware.etts.Constants;

import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.bcel.internal.classfile.Constant;

public class ScoreManager extends Observable {

	private int score;
	private int highscore;
	
	public ScoreManager() {
	}
	
	public void addScore(int score) {
		this.score += score;
		if(this.score > highscore)
			highscore = this.score;
		setChanged();
		notifyObservers();
	}
	
	public void resetScore() {
		this.score = 0;
		setChanged();
		notifyObservers();
	}
	
	public int getScore() {
		return this.score/Constants.textureSize;
	}

	public int getHighscore() {

		return highscore/Constants.textureSize;
	}
	
}
