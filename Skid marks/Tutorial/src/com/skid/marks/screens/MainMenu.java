package com.skid.marks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.skid.marks.screens.Game;
import com.skid.marks.tutorial.TutorialGame;

public class MainMenu implements Screen, InputProcessor {
	
	private final TutorialGame game;
	
	private float sw;
	private float sh;
	
	private float cx;
	private float cy;
	
	private Texture texPlay;
	private Texture texPlayHL;
	private Texture texHighscores;
	private Texture texHighscoresHL;
	private Texture texSettings;
	private Texture texSettingsHL;
	
	private Sprite backgroundSprite;
	private Sprite menuSprite;
	private Sprite playSprite;
	private Sprite highscoreSprite;
	private Sprite settingsSprite;
	private Sprite soundSprite;
	private Sprite quitSprite;
	
	private final float BUTTON_SIZE = 80f;
	
	public MainMenu(final TutorialGame game){
		this.game = game;
		Gdx.input.setInputProcessor(this);
		
		sw = Gdx.graphics.getWidth();
		sh = Gdx.graphics.getHeight();
		
		cx = sw/2;
		cy = sh/2;
		
		game.Sounds.play("menu", true);
		
		texPlay = game.Localization.getTexture("play.png");
		texPlayHL = game.Localization.getTexture("play_HL.png");
		texHighscores = game.Localization.getTexture("highscores.png");
		texHighscoresHL = game.Localization.getTexture("highscores_HL.png");
		texSettings = game.Localization.getTexture("settings.png");
		texSettingsHL = game.Localization.getTexture("settings_HL.png");
		
		backgroundSprite = game.Textures.getSprite("data/gfx/background_A.png");
		backgroundSprite.setSize(sw, sh);
		backgroundSprite.setPosition(0, 0);
		
		menuSprite = game.Textures.getSprite("data/gfx/background_sheet.png");
		menuSprite.setRegion(0, 0, 256, 512);
		menuSprite.setSize(sw * 0.6f, sh * 0.8f);
		menuSprite.setPosition(cx - (menuSprite.getWidth() / 2) , cy - (menuSprite.getHeight() / 2));
		
		// Button size och position
		float bsx = menuSprite.getWidth();
		float bsy = menuSprite.getHeight() / 3;
		float box = menuSprite.getX();
		float boy = menuSprite.getY();
		
		playSprite = new Sprite(texPlay);
		playSprite.setSize(bsx, bsy);
		playSprite.setPosition(box, boy);
		playSprite.flip(false, true);
		
		highscoreSprite = new Sprite(texHighscores);
		highscoreSprite.setSize(bsx, bsy);
		highscoreSprite.setPosition(box, boy + bsy);
		highscoreSprite.flip(false, true);
		
		settingsSprite = new Sprite(texSettings);
		settingsSprite.setSize(bsx, bsy);
		settingsSprite.setPosition(box, boy + bsy * 2);
		settingsSprite.flip(false, true);

		soundSprite = game.Textures.getSprite("data/gfx/background_sheet.png");
		soundSprite.setRegion(256, 0, 64, 64);
		soundSprite.setSize(0, 0);
		soundSprite.setPosition(0, 0);
		
		quitSprite = game.Textures.getSprite("data/gfx/background_sheet.png");
		quitSprite.setRegion(256, 64, 64, 64);
		quitSprite.setSize(0, 0);
		quitSprite.setPosition(0, 0);
	}
	
	
	void reset() {
		playSprite.setRegion(300, 0, 80, 80);
		playSprite.flip(false, true);
		
		highscoreSprite.setRegion(300, 80, 80, 80);
		highscoreSprite.flip(false, true);
		
		settingsSprite.setRegion(300, 160, 80, 80);
		settingsSprite.flip(false, true);	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100/255f, 100/255f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.Batch.setProjectionMatrix(game.Camera.combined);
		game.Batch.begin();

		backgroundSprite.draw(game.Batch);
		menuSprite.draw(game.Batch);
		playSprite.draw(game.Batch);
		highscoreSprite.draw(game.Batch);
		settingsSprite.draw(game.Batch);
		soundSprite.draw(game.Batch);
		quitSprite.draw(game.Batch);
		
		game.Batch.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(playSprite.getBoundingRectangle().contains(screenX, screenY)) {
//			playSprite.setRegion(380, 0, 80, 80);
//			playSprite.flip(false, true);
		} else if(highscoreSprite.getBoundingRectangle().contains(screenX, screenY)) {
//			highscoreSprite.setRegion(380, 80, 80, 80);
//			highscoreSprite.flip(false, true);
		} else if(settingsSprite.getBoundingRectangle().contains(screenX, screenY)) {
//			settingsSprite.setRegion(380, 160, 80, 80);
//			settingsSprite.flip(false, true);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//		reset();
		if(playSprite.getBoundingRectangle().contains(screenX, screenY)) {
			game.setScreen(new Game(game));
		} else if(highscoreSprite.getBoundingRectangle().contains(screenX, screenY)) {
			game.setScreen(new Highscore(game));
		} else if(settingsSprite.getBoundingRectangle().contains(screenX, screenY)) {
			game.setScreen(new Settings(game));
		}
		return false;
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
