package org.dieubware.etts.model;

import java.util.List;
import org.dieubware.etts.Observable;
import org.dieubware.etts.Observer;

import org.dieubware.etts.Constants;
import org.dieubware.etts.controller.ItemsObserver;
import org.dieubware.etts.controller.ScoreObserver;
import org.dieubware.etts.model.Items.ItemType;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameModel extends Observable {

	private Player player;
	private Borders borders;
	private float velocityX, velocityY;
	private float gravity = -Constants.gravity;
	private float screenHeight, screenWidth;
	private boolean flap = true;
	private boolean lost = false;
	private ScoreManager scoreManager;
	private float lastBorderLeftY, lastBorderRightY;
	private float borderWidth = Constants.borderSize;
	private float playerWidth = Constants.playerSize;
	private Items items;
	private float lastItemY;

	private List<Rectangle> bordersRight, bordersLeft;

	public GameModel(float screenHeight, float screenWidth) {

		player = new Player(0,0,playerWidth,playerWidth);
		borders = new Borders();
		items = new Items();
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		init();
		scoreManager = new ScoreManager();


	}

	public void init() {
		player.pos(0, 0);
		borders.clear();
		items.clear();
		velocityX = -Constants.velocityX;
	}

	public void initBorders() {
		lastBorderRightY = 0;
		lastBorderLeftY = 0;

		float height = 3*Constants.textureSize + (int)((float)Math.random()*12f)*Constants.textureSize -1;

		borders.addBorder(player.x() - 5, lastBorderLeftY, height, false);
		lastBorderLeftY += height;

		while(lastBorderRightY < screenHeight +100) {
			generateRightBorder();
		}
		while(lastBorderLeftY < screenHeight +100) {
			generateLeftBorder();
		}
	}

	public void movePlayer(float delta) {

		player.x(player.x() + velocityX*delta);// * time;      // Apply horizontal velocity to X position

		if(player.y() < screenHeight/2 || velocityY < 0) {
			player.y(player.y() + velocityY*delta); //* time;      // Apply vertical velocity to X position
		}
		else {

			borders.scroll(-velocityY*delta);
			items.scroll(-velocityY*delta);
			lastBorderLeftY -= velocityY*delta;
			lastBorderRightY -= velocityY*delta;
			lastItemY -= velocityY*delta;
			scoreManager.addScore((int)(velocityY*delta));
			if(lastBorderLeftY < screenHeight +100) {
				generateLeftBorder();
			}
			if(lastBorderRightY < screenHeight +100) {
				generateRightBorder();
			}
			if(lastItemY < screenHeight +100) {
				generateItem();
			}
		}
		velocityY += gravity*delta;// * time;


		if(isBouncing()) {
			System.out.println("Bounce");

			velocityY *= -0.9f;
		}
		else if(player.y() < 0 || isBehindBorders())
			setLost(true);
		checkItems();
	}


	public void checkItems() {
		for(Item i : items) {
			if(Intersector.overlaps(i,player.getR())) {
				switch(i.getType()) {
				case KILL:
					setLost(true);
					break;
				case FLY:
					velocityY = Constants.velocityY*2f;
					/*if(velocityX >0)
							velocityX = Constants.velocityX;
						else
							velocityX = -Constants.velocityX;*/
					break;
				}
			}
		}

	}

	public boolean isBehindBorders() {

		for(Integer i : borders.leftKeySet()) {
			Rectangle r = borders.get(i);
			if(player.y() > r.y && player.y() < (r.y + r.height)
					&& player.x() < r.x) {
				return true;
			}
		}
		for(Integer i : borders.rightKeySet()) {
			Rectangle r = borders.get(i);
			if(player.y() > r.y && player.y() < (r.y + r.height)
					&& player.x()+playerWidth > r.x + borderWidth) {

				return true;
			}

		}
		return false;
	}

	public boolean isBouncing() {

		for(Integer i : borders.leftKeySet()) {
			Rectangle r = borders.get(i);
			if(
					//When the player jump into the border from below
					velocityY >= 0 //If falling, dies
					&& player.y() + playerWidth >= r.y //is on border
					&& player.y() + .75*playerWidth < r.y //Only a quarter of player is touching
					&& player.x() + .25*playerWidth <= r.x

					//When the player lands on the border
					|| velocityY <= 0 //If jumping, dies
					&& player.y() <= r.y + r.height
					&& player.y() + .25*playerWidth > r.y + r.height
					&& player.x() + .25*playerWidth <= r.x) { //Down bounce

				return true;
			}
		}
		for(Integer i : borders.rightKeySet()) {
			Rectangle r = borders.get(i);
			if(
				//When the player jump into the border from below
				velocityY >= 0 //If falling, dies
				&& player.y() + playerWidth >= r.y //is on border
				&& player.y() + .75*playerWidth < r.y //Only a quarter of player is touching
				&& player.x() + .75*playerWidth >= r.x + r.width

				//When the player lands on the border
				|| velocityY <= 0 //If jumping, dies
				&& player.y() <= r.y + r.height
				&& player.y() + .25*playerWidth > r.y + r.height
				&& player.x() + .75*playerWidth >= r.x + r.width) { //Down bounce

					return true;
				}

		}
		return false;
	}

	public void generateLeftBorder() {
		float height = 3*Constants.textureSize + (int)((float)Math.random()*12f)*Constants.textureSize -1;
		borders.addBorder(
				screenWidth/2 - Constants.insideBorder - (float)Math.random()*Constants.outsideBorder
				, lastBorderLeftY, height, false);
		lastBorderLeftY += height;
		borders.clearRight();
	}

	public void generateRightBorder() {
		float height = 3*Constants.textureSize + (int)((float)Math.random()*12f)*Constants.textureSize -1;
		//float height = 96+Constants.textureSize*(float)Math.random()*12.5f;
		borders.addBorder(
				screenWidth/2 + Constants.insideBorder  + (float)Math.random()*Constants.outsideBorder
				, lastBorderRightY, height, true);
		lastBorderRightY += height;
		borders.clearLeft();
	}

	public void generateItem() {
		lastItemY = screenHeight*2 + (float)Math.random()*600f ;
		items.addItem(
				screenWidth/2 + (float)Math.random()*50f,
				lastItemY,
				Constants.playerSize,
				Constants.playerSize,
				ItemType.random());
		items.clearOld();
	}


	public void jump() {

		float dist = getBorderDist();
		if(dist > 0 == velocityX > 0
				&& dist < 0 == velocityX < 0) {

			dist = Math.abs(dist);
			velocityY = Constants.velocityY ;
			velocityX *= -1;
			if(velocityX> 0) {
				player.setDirection(true);
				velocityX = Constants.velocityX-Constants.velocityX*(dist/Constants.borderSize);
			}
			else {
				player.setDirection(false);
				velocityX = (Constants.velocityX-Constants.velocityX*(dist/Constants.borderSize))*(-1);
			}
			flap = true;
		}
		else if(flap) {
			velocityY = Constants.velocityY;
			if(velocityX >0)
				velocityX = Constants.velocityX/2;
			else
				velocityX = -Constants.velocityX/2;
			flap = false;
		}
	}

	public float getBorderDist() {

		for(Integer i : borders.leftKeySet()) {
			if(Intersector.overlaps(borders.get(i), player.getR())) {
				return (player.x() - borders.get(i).x)*(-1);
			}
		}
		for(Integer i : borders.rightKeySet()) {
			if(Intersector.overlaps(borders.get(i), player.getR())) {
				return (borders.get(i).x + Constants.borderSize) - (player.x()+playerWidth) ;
			}
		}
		return 0;
	}

	public void setPlayerPos(float x, float y) {
		player.pos(x, y);
	}

	public void setPlayerObserver(Observer playerObserver) {
		player.addObserver(playerObserver);

	}

	public void setBordersObserver(Observer bordersObserver) {
		borders.addObserver(bordersObserver);

	}

	public void addItem(int x, int y, int width, int height, ItemType type) {
		items.addItem(x, y, width, height, type);
	}

	public void addBorder(int x, int y, int height, boolean right) {
		borders.addBorder(x, y, height, right);
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean b) {
		lost = b;
		setChanged();
		notifyObservers();
	}

	public int getScore() {
		return scoreManager.getScore();
	}

	public void resetScore() {
		scoreManager.resetScore();
	}

	public void setScoreObserver(ScoreObserver scoreObserver) {
		scoreManager.addObserver(scoreObserver);
	}

	public void addItemObserver(ItemsObserver itemsObserver) {
		items.addObserver(itemsObserver);
	}

	public int getHighscore() {
		return scoreManager.getHighscore();
	}

}
