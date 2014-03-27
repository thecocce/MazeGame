package com.skid.marks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.skid.marks.screens.MainMenu;
import com.skid.marks.screens.Game;
import com.skid.marks.tutorial.Level;
import com.skid.marks.tutorial.Player;
import com.skid.marks.tutorial.TutorialGame;

public class GameOver implements Screen, InputProcessor {
	
	private final TutorialGame game;
	
	private Level level;

	private int score;
	
	private Sprite backSprite;
	private Sprite playSprite;
	private Sprite score_background;
	
	private float buttonSize;
	
	private float sw;
	private float sh;
	
	private ParticleEffect explosion;
	
	public GameOver(final TutorialGame game, Level level, Player player, int score) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		
		this.level = level;
		this.score = score;
		
		sw = Gdx.graphics.getWidth();
		sh = Gdx.graphics.getHeight();
		
		buttonSize = sh * 0.15f;
		
		backSprite = game.Textures.getSprite("data/gfx/background_sheet.png");
		backSprite.setSize(buttonSize, buttonSize);
		backSprite.setPosition(sw - buttonSize * 1.2f, sh - buttonSize * 1.2f);
		backSprite.setRegion(256, 128, 64, 64);
		backSprite.flip(false, true);
		
		playSprite = game.Textures.getSprite("data/gfx/background_sheet.png");
		playSprite.setSize(buttonSize, buttonSize);
		playSprite.setPosition(buttonSize * 0.2f, sh - buttonSize * 1.2f);
		playSprite.setRegion(256, 192, 64, 64);
		playSprite.flip(false, true);
		
		explosion = new ParticleEffect();
		explosion.load(Gdx.files.internal("data/gfx/particle/explosion2.p"),
				Gdx.files.internal("data/gfx/particle/"));
		explosion.setPosition(player.getPosition().x, player.getPosition().y);
		explosion.reset();
		
		score_background = game.Textures.getSprite("data/gfx/score_background.png");
		score_background.setBounds(0, 0, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6);
	}
	
	@Override
	public void dispose() {
		explosion.dispose();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100/255f, 100/255f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.Camera.update();
		game.Batch.setProjectionMatrix(game.Camera.combined);
		game.Batch.begin();
		level.draw(game.Batch);
		score_background.draw(game.Batch);
		game.ingame_font.draw(game.Batch, String.format("Score: %d", score), 20, 20);
		game.ingame_font.draw(game.Batch, String.format("Level: %d", Level.currentLevel), 20, 70);
		
		explosion.draw(game.Batch, delta);
		
		playSprite.draw(game.Batch);
		backSprite.draw(game.Batch);

		game.Batch.end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(backSprite.getBoundingRectangle().contains(screenX, screenY)) {
			backSprite.setRegion(320, 128, 64, 64);
			backSprite.flip(false, true);
		} else if(playSprite.getBoundingRectangle().contains(screenX, screenY)) {
			playSprite.setRegion(320, 192, 64, 64);
			playSprite.flip(false, true);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		backSprite.setRegion(256, 128, 64, 64);
		backSprite.flip(false, true);
		
		playSprite.setRegion(256, 192, 64, 64);
		playSprite.flip(false, true);
		
		if(backSprite.getBoundingRectangle().contains(screenX, screenY)) {
			game.ingame_font.setColor(Color.GRAY);
			game.Sounds.play("menu", true);
			game.setScreen(new MainMenu(game));
			this.dispose();
		} else if(playSprite.getBoundingRectangle().contains(screenX, screenY)) {
			game.ingame_font.setColor(Color.GRAY);
			game.setScreen(new Game(game));
			this.dispose();
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
