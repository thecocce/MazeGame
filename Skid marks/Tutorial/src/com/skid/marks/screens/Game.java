package com.skid.marks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.skid.marks.manager.particle.Star;
import com.skid.marks.tutorial.Debug;
import com.skid.marks.tutorial.Level;
import com.skid.marks.tutorial.Player;
import com.skid.marks.tutorial.TutorialGame;

public class Game implements Screen, InputProcessor {

	private final TutorialGame game;
	
	private Level level;
	private Player player;
	private float score = 0;
	
	private Sprite score_background;
	
	public Game(TutorialGame game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		Highscore.LoadPrefs();
		
		game.Particles.clear();
		player = new Player(game);
		player.init();

		level = new Level(game);
		
		score_background = game.Textures.getSprite("data/gfx/menu/button.png");
		score_background.setBounds(0, 0, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/6);
		if(MainMenu.hasSound)
			game.Sounds.play("music", true);
	}
	
	@Override
	public void dispose() {
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(100/255f, 100/255f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float time = Gdx.graphics.getDeltaTime();
		
		// UPDATE
		if(!Level.isBetweenLevels)
			score += delta*60;
		game.Particles.add(new Star(game));
		
		player.update(time);
		
		level.update(time);
		
		if(level.HasCollided(player.getBounds()))
		{
			game.Sounds.play("explosion");
			game.setScreen(new GameOver(game, level, player, (int)score));
			
			Highscore.InitHighscore();
			isHighscore((int)score);
		}
		
		// DRAW
		game.Camera.update();
		game.Batch.setProjectionMatrix(game.Camera.combined);
		game.Batch.begin();
		level.draw(game.Batch);
		score_background.draw(game.Batch);
		game.ingame_font.draw(game.Batch, String.format("Score: %d", (int)score), score_background.getWidth()*0.12f, score_background.getHeight()*0.2f);
		game.ingame_font.draw(game.Batch, String.format("Level: %d", Level.currentLevel), score_background.getWidth()*0.12f, score_background.getHeight()*0.6f);
		player.draw(game.Batch);

		Debug.render(game.Batch);
		game.Batch.end();
	}
	
	private void isHighscore(int newscore) {
		int checker = Highscore.CheckScore(newscore);
		
		if(checker == 1) {
			Highscore.SaveScore(1, newscore);
		} else if(checker == 2) {
			Highscore.SaveScore(2, newscore);
		} else if(checker==3) {
			Highscore.SaveScore(3, newscore);
		}
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
