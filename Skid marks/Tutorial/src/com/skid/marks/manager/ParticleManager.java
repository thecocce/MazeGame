package com.skid.marks.manager;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skid.marks.tutorial.BaseParticle;
import com.skid.marks.tutorial.Logger;
import com.skid.marks.tutorial.PedjaStars;

public class ParticleManager {
	
	private ArrayList<BaseParticle> particles;
	
	public ParticleManager() {
		particles = new ArrayList<BaseParticle>();
	}
	
	public void add(BaseParticle particle) {
		particles.add(particle);
	}
	
	public void update(float time) {
		int pedjas = 0;
		
		for(int i = 0; i < particles.size(); i++) {
			BaseParticle part = particles.get(i);
			part.update(time);
			if(part instanceof PedjaStars)
				pedjas++;
			if(part.isAlive() == false) {
				particles.remove(i);
			}
		}
	}
	
	public void draw(SpriteBatch batch) {
		for(BaseParticle part : particles) {
			part.draw(batch);
		}
	}
	
}