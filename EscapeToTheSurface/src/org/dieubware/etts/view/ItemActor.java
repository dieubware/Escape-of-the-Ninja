package org.dieubware.etts.view;

import java.util.ArrayList;
import java.util.List;

import org.dieubware.etts.Constants;
import org.dieubware.etts.model.Items.ItemType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private TextureRegion[] texRegions;
	private Animation animation;
	private float animTime = 0;


	private float textureSize = Constants.textureSize;
	private TextureRegion frame;
	public ItemActor(float x, float y, float width, float height, TextureRegion[] texRegions) {
		super();
		setHeight(height*1.25f);
		setWidth(width*1.25f);
		setX(x-getWidth()*0.25f);
		setY(y-getWidth()*0.25f);
		

		shapeRenderer = new ShapeRenderer();
		animation = new Animation(0.15f,texRegions);
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.setColor(1f,1f,1f,1f);
		
		frame = animation.getKeyFrame(animTime, true);
		batch.draw(frame,  getX(), getY(), getWidth(), getHeight());
		
	}

	
	@Override
	public void act(float delta) {
		this.animTime += delta;
		super.act(delta);
	}
}
