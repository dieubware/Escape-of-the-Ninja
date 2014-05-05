package org.dieubware.etts.view;

/**
 * Mennozhioù
 * 
 * different sprites texture for walls, with same "branches"
 * highscore*
 * android compatibility
 * obstacles*
 * bonuses*
 * larger levels*
 * incearsing speed
 * 
 */
import org.dieubware.etts.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private Animation iddleAnimation, jumpAnimation, pushAnimation, currentAnimation;
	private boolean direction;
	private int textureSize = Constants.textureSize;
	private float animTime = 0;
	private TextureRegion frame;
	private Texture text;

	public PlayerActor(float x, float y, Texture text) {
		super();
		setX(x);
		setY(y);
		setWidth(Constants.playerSize);
		setHeight(Constants.playerSize);
		
		this.text = text;
		
		iddleAnimation = new Animation(0.15f,
				new TextureRegion(text, 0,0, 32,32),
				new TextureRegion(text, 32,0,32,32),
				new TextureRegion(text, 2*32, 0,32,32),
				new TextureRegion(text, 3*32, 0,32,32),
				new TextureRegion(text, 4*32, 0,32,32),
				new TextureRegion(text, 5*32, 0,32,32)
		);
		
		jumpAnimation = new Animation(0.1f,
				new TextureRegion(text, 0,32, 32,32),
				new TextureRegion(text, 32,32,32,32),
				new TextureRegion(text, 2*32, 32,32,32),
				new TextureRegion(text, 3*32, 32,32,32),
				new TextureRegion(text, 4*32, 32,32,32)
		);
		pushAnimation = new Animation(0.08f,
				new TextureRegion(text, 0    ,32*2, 32,32),
				new TextureRegion(text, 32   ,32*2,32,32),
				new TextureRegion(text, 2*32, 32*2,32,32),
				new TextureRegion(text, 3*32, 32*2,32,32)
		);
		iddleAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		jumpAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		pushAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		currentAnimation = iddleAnimation;
		shapeRenderer = new ShapeRenderer();
		setColor(Color.GREEN);
	}



	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);
		batch.setColor(1f,1f,1f,1f);
		frame = currentAnimation.getKeyFrame(animTime, true);
		if(currentAnimation == pushAnimation && pushAnimation.isAnimationFinished(animTime)) {
			setJumpAnimation();
		}
		if(direction) {
			batch.draw(frame,  getX(), getY(), getWidth(), getHeight());
		}
		else {
			batch.draw(frame,  getX()+getWidth(), getY(), -getWidth(), getHeight());
		}
		//batch.draw(text,  getX()+getWidth(), getY(), -getWidth(), getHeight());
	}
	
	



	@Override
	public void act(float delta) {
		this.animTime += delta;
		super.act(delta);
	}


	public void setJumpAnimation() {
		animTime = 0;
		currentAnimation = jumpAnimation;
	}
	public void setIddleAnimation() {
		animTime = 0;
		currentAnimation = iddleAnimation;
	}
	public void setPushAnimation() {
		animTime = 0;
		currentAnimation = pushAnimation;
	}

	public void setDirection(boolean b) {
		direction = b;
	}



	public boolean getDirection() {
		return direction;
	}

}
