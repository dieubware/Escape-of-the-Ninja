package org.dieubware.etts;

import org.dieubware.etts.Observer;

import org.dieubware.etts.controller.BordersObserver;
import org.dieubware.etts.controller.ItemsObserver;
import org.dieubware.etts.controller.ModelObserver;
import org.dieubware.etts.controller.PlayerObserver;
import org.dieubware.etts.controller.ScoreObserver;
import org.dieubware.etts.controller.StageListener;
import org.dieubware.etts.model.GameModel;
import org.dieubware.etts.model.Items.ItemType;
import org.dieubware.etts.view.GameScreen;

import com.badlogic.gdx.Gdx;

public class GameInterface {

	private TimeManager timeManager;
	private GameModel model;
	private GameScreen screen;
	
	
	
	private Observer playerObserver, bordersObserver, modelObserver;
	
	private float screenWidth = 0;
	private float screenHeight = 0;
	private ScoreObserver scoreObserver;
	private ItemsObserver itemsObserver;

	
	
	public void initGame(GameScreen game) {

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		screen = game;
		model = new GameModel(screenHeight, screenWidth);
		timeManager = new TimeManager(model, screen);
		screen.setTimeManager(timeManager);
		
		
		createObservers();
		
		model.setPlayerObserver(playerObserver);
		model.setBordersObserver(bordersObserver);
		model.setScoreObserver(scoreObserver);
		model.addObserver(modelObserver);
		model.addItemObserver(itemsObserver);
		screen.getStage().addListener(new StageListener(model, screen));

		setTestLevel();
	}
	
	public void createObservers() {
		playerObserver = new PlayerObserver(screen);
		bordersObserver = new BordersObserver(screen);
		modelObserver = new ModelObserver(screen, this);
		scoreObserver = new ScoreObserver(screen);
		itemsObserver = new ItemsObserver(screen);
		
	}
	
	public void setTestLevel() {
		model.init();
		int center = (int)(screenWidth/2);
		float pX = center - 90, pY = 5;
		model.setPlayerPos(pX, pY);
		screen.setPlayerPos(pX, pY, true);
		
		model.initBorders();
	}

	


}
