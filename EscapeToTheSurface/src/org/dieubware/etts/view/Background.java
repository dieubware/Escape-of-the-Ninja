package org.dieubware.etts.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
	
	private int textureSize = 32;
	private Texture text;
	public Background() {
		super();
		setX(0);
		setY(0);
		text = new Texture(Gdx.files.internal("earth.png"));

		text.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
	}



	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);
		batch.setColor(1f,0.8f,0.8f,0.7f);
		batch.draw(text, 0, 0,
		         text.getWidth() * 50, 
		         text.getHeight() * 50, 
		         0, 50, 
		         50, 0);
	}



}
