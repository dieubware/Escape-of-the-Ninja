package org.dieubware.etts;

import org.dieubware.etts.view.GameScreen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EttsGame extends Game {

	private GameScreen game;
	private GameInterface gameInterface;
	
	public EttsGame() {
		gameInterface = new GameInterface();
	}
	
	@Override
	public void create() {
		Constants.initConstants(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setCatchBackKey(true);
		game = new GameScreen(this);
		gameInterface.initGame(game);
		setScreen(game);
		
	}

	
	
	
	public void exit() {
		Gdx.app.exit();
	}
}
