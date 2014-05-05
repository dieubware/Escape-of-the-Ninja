package org.dieubware.etts.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.dieubware.etts.Constants;
import org.dieubware.etts.EttsGame;
import org.dieubware.etts.TimeManager;
import org.dieubware.etts.model.Items.ItemType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {

	private EttsGame game;
	private TimeManager timeManager;
	private PlayerActor playerActor;
	private Stage stage;
	float w,h;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Map<Integer, BorderActor> borders;
	private Map<Integer, ItemActor> items;
	private Group borderGroup;
	private Image tapToStart, lostImage;
	private Texture bg;
	private Texture[] earthTexture;
	private TextureRegion[] tornado = new TextureRegion[4];
	private TextureRegion[] spikes = new TextureRegion[4];
	private Texture itemsTexture;
	private Texture playerTexture;
	private Sound[] jumpSounds = new Sound[4];
	private Sound hitSound;
	private Music music;
	private boolean isSound = false;
	private FPSLogger fps = new FPSLogger();

	public enum State {
		WAITING, PLAYING, LOST

	}
	private State state = State.WAITING;
	private HUD hud;

	public GameScreen(EttsGame ettsGame) {
		stage = new Stage();

		playerTexture = new Texture(Gdx.files.internal("ninja_anim.png"));
		playerActor = new PlayerActor(0,0, playerTexture);
		hud = new HUD();
		borderGroup = new Group();
		playerActor.setZIndex(200);
		borders = new HashMap<Integer, BorderActor>();
		items = new HashMap<Integer, ItemActor>();
		earthTexture = new Texture[6];
		for(int i = 0; i < earthTexture.length; i++) {
			earthTexture[i] = new Texture(Gdx.files.internal("earth" + i + ".png"));
		}
		itemsTexture = new Texture(Gdx.files.internal("items.png"));
		for(int i= 0; i < 4; i++) {
			tornado[i] = new TextureRegion(itemsTexture, i*32, 0, 32,32);
			spikes[i] = new TextureRegion(itemsTexture, i*32, 32, 32,32);

		}

		for(int i=1; i < 4; i++) {
			jumpSounds[i] = Gdx.audio.newSound(Gdx.files.internal("sound/jump"+i+".mp3"));
		}
		hitSound = Gdx.audio.newSound(Gdx.files.internal("sound/hit.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));



	}

	@Override
	public void show() {
		stage.addActor(new Background());
		stage.addActor(borderGroup);
		stage.addActor(playerActor);

		camera = new OrthographicCamera();

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera.setToOrtho(false, w, h);
		batch = new SpriteBatch();
		tapToStart = new Image(new Texture(Gdx.files.internal("title.png")));
		lostImage = new Image(new Texture(Gdx.files.internal("youlost.png")));
		bg = new Texture(Gdx.files.internal("earth.png"));
		lostImage.setVisible(false);
		stage.addActor(tapToStart);
		stage.addActor(lostImage);
		stage.addActor(hud);
		music.setLooping(true);
		//music.play();


		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		fps.log();
		if(state == State.PLAYING) {
			timeManager.manage(delta);
		}
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Gdx.gl.glEnable(GL10.GL_BLEND);
		stage.act(delta);
		stage.draw();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		//for(int i = 0; i < Gdx.graphics.getWidth(); i+=Constants.textureSize) {
		//for(int j = 0; j < Gdx.graphics.getHeight(); j+=Constants.textureSize) {
		/*batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),0,
						Gdx.graphics.getWidth()/Constants.textureSize,
						Gdx.graphics.getHeight()/Constants.textureSize, 0);
		 */
		//batch.draw(bg, 400, 300);
		//}

		//}
		//if(state == State.WAITING)

		batch.end();


	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		for(int i = 0; i < earthTexture.length; i++) {
			earthTexture[i].dispose();
		}
		playerTexture.dispose();
		for(int i=1; i < 4; i++) {
			jumpSounds[i].dispose();
		}
		hitSound.dispose();
	}

	public void addBorder(int id, float x, float y, float height, boolean right) {
		BorderActor b = new BorderActor(x, y, height, right, earthTexture);
		b.setZIndex(1);
		borderGroup.addActor(b);
		borders.put(id, b);
	}

	public void setBorderY(int id, float y) {
		borders.get(id).setY(y);
	}
	public void clearBorders() {
		for(Integer i : borders.keySet()) {
			borders.get(i).remove();
		}
		borders.clear();
	}

	public void removeBorder(int i) {
		if(borders.containsKey(i)) {
			borders.get(i).remove();
			borders.remove(i);
		}

	}
	public void setItemY(int id, float y) {
		items.get(id).setY(y);
	}

	public void clearItems() {
		for(Integer i : items.keySet()) {
			items.get(i).remove();
		}
		items.clear();
	}
	public void addItem(int id, float x, float y, float width, float height,
			ItemType type) {
		TextureRegion[] trs = null;
		if(type == ItemType.KILL)
			trs = spikes;
		else
			trs = tornado;

		ItemActor i = new ItemActor(x, y, width, height, trs);
		stage.addActor(i);
		items.put(id, i);
	}
	public void removeItem(int i) {
		if(items.containsKey(i)) {
			items.get(i).remove();
			items.remove(i);
		}
	}

	public void playDeathAnimation() {
		float x = 50;
		if(playerActor.getDirection()) {
			x = -x;
		}
		MoveByAction moveUp = Actions.moveBy(x, 0, 0.1f + 0.002f*playerActor.getY());
		moveUp.setInterpolation(Interpolation.circleOut);

		MoveByAction moveDown = Actions.moveBy(0,-(playerActor.getY() + playerActor.getWidth()), 0.1f+0.002f*playerActor.getY());
		moveDown.setInterpolation(Interpolation.swingIn);
		Action deathAction = Actions.parallel(moveUp,moveDown);
		playerActor.addAction(deathAction);

	}

	public void setTimeManager(TimeManager tm) {
		this.timeManager = tm;
	}

	public void setPlayerPos(float x, float y, boolean b) {
		playerActor.setX(x);
		playerActor.setY(y);
		playerActor.setDirection(b);
	}

	public void playJumpSound() {
		if(isSound) {
			int soundId = (int)(Math.random() * 3) +1;
			jumpSounds[soundId].play();
		}
	}

	public void playHitSound() {
		if(isSound) {
			hitSound.play();
		}
	}

	public void toggleMusic() {
		if(isSound) {
			music.pause();
		}
		else {
			music.play();
		}
		isSound = !isSound;
	}

	public Stage getStage() {
		// TODO Auto-generated method stub
		return stage;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Image getTapToStart() {
		return tapToStart;
	}

	public Image getLostImage() {
		return lostImage;
	}

	public HUD getHud() {
		// TODO Auto-generated method stub
		return hud;
	}

	public PlayerActor getPlayerActor() {
		return playerActor;
	}



}
