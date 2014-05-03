package org.dieubware.etts.view;

import java.util.ArrayList;
import java.util.List;

import org.dieubware.etts.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BorderActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private List<Texture> text;
	private boolean right;
	private Color color1, color2;


	private float textureSize = Constants.textureSize;
	public BorderActor(float x, float y, float height, boolean right, Texture[] earthTextures) {
		super();
		setX(x);
		setY(y);
		setHeight(height);

		setTextures(earthTextures);
		shapeRenderer = new ShapeRenderer();
		this.right = right;
		color1 = new Color(0.4f, 0.105f, 0.058f, 0.8f);

		color2 = new Color(0,0,0,0);

		setColor(Color.ORANGE);
	}

	public void setTextures(Texture[] earthTextures) {

		text = new ArrayList<Texture>();
		if(right) {
			for(int j = (int)getY(); j < getY() + getHeight(); j+= textureSize ) {
				for(int i = (int)getX()+Constants.borderSize; i < Gdx.graphics.getWidth();i+=textureSize) {
					text.add(getRandomTexture(earthTextures));
				}

			}
		}
		else {
			for(int j = (int)getY(); j < getY() + getHeight(); j+= textureSize ) {
				for(int i = (int)getX(); i > 0; i-=textureSize) {
					text.add(getRandomTexture(earthTextures));
				}
			}
		}

	}

	public Texture getRandomTexture(Texture[] earthTextures) {

		int textureIndex = (int)(Math.random()*82.0);
		if(textureIndex < 80) {
			return earthTextures[(int)(textureIndex/20)];
		}
		else {
			if(textureIndex < 81)
				return earthTextures[4];
			else
				return earthTextures[5];
		}

	}



	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(1f,1f,1f,1f);
		int index = 0;
		if(getY() <= Gdx.graphics.getHeight()) {
			if(right) {
				for(int j = (int)getY(); j < Math.min(getY() + getHeight(), Gdx.graphics.getHeight()); j+= textureSize ) {
					for(int i = (int)getX()+Constants.borderSize; i < Gdx.graphics.getWidth();i+=textureSize) {
						batch.draw(text.get(index), i, j);
						index++;
					}
				}
			}
			else {
				for(int j = (int)getY(); j < Math.min(getY() + getHeight(), Gdx.graphics.getHeight()); j+= textureSize ) {
					for(int i = (int)getX(); i > 0; i-=textureSize) {
						batch.draw(text.get(index), i-textureSize, j);
						index++;
					}
				}
			}


			batch.end();
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(getColor());
			if(right) {
				shapeRenderer.rect(getX(), getY(), Constants.borderSize,getHeight(), color2, color1, color1, color2);
			}
			else {
				shapeRenderer.rect(getX(), getY(), Constants.borderSize,getHeight(), color1, color2, color2, color1);
			}
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);
			batch.begin();
		}
	}

}
