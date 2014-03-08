package com.skid.marks.tutorial;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface BaseParticle {
	
	void update(float delta);
	
	void draw(SpriteBatch batch);
	
	boolean isAlive();

}
