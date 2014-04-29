package org.dieubware.etts.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HUD extends Actor {
	
	private int textureSize = 32;
	private BitmapFont font;
	private int score = 0, highscore = 0;
	private boolean lostHud = false;
	public HUD() {
		super();
		setX(0);
		setY(0);
		font = new BitmapFont();
	}



	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);
		batch.setColor(1f,0.8f,0.8f,0.7f);
		font.draw(batch, "Score : " + score,20,20);
		if(lostHud) {
			font.draw(batch, "Highscore : " + highscore,Gdx.graphics.getWidth()/2 - 50,Gdx.graphics.getHeight()/2-100);
		}
		
	}

	public void setScore(int s) {
		this.score = s;
	}
	public void setHighScore(int s) {
		this.highscore = s;
	}



	public void setLost(boolean b) {
		lostHud = b;
	}


}
