package client.particles;

import common.iterator.ParticleCollection;
import common.iterator.ParticleIterator;

import java.awt.*;
import java.util.Vector;

abstract class ParticleSystem {
    private final ParticleCollection particles;
    private float x, y;
    private int count;

    public ParticleSystem(float x, float y) {
        this.x = x;
        this.y = y;
        this.count = 0;
        particles = new ParticleCollection(new Vector<>());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getCount() {
        return count;
    }

    public void addParticle(Particle p) {
        particles.add(p);
        count++;
    }

    public void removeParticles(Vector<Particle> prtcls) {

        particles.removeAll(prtcls);
        count -= particles.size();
    }

    public abstract void initializeParticles();

    abstract void updateSelf(double deltaTime);

    public void draw(Graphics2D graphics) {
        ParticleIterator particleIterator = particles.createIterator();
        while (particleIterator.hasNext()) {
            Particle particle = particleIterator.getNext();
            particle.draw(graphics);
        }
    }

    public final void update(double deltaTime) {
        updateSelf(deltaTime);
        Vector<Particle> toRemove = new Vector<>();
        ParticleIterator particleIterator = particles.createIterator();
        while (particleIterator.hasNext()) {
            Particle particle = particleIterator.getNext();
            if (isParticleDone(particle)) {
                toRemove.add(particle);
                continue;
            }
            move(particle, deltaTime);
            updateColors(particle, deltaTime);
            updateSize(particle, deltaTime);
        }
        if (toRemove.size() > 0)
            removeParticles(toRemove);
    }

    abstract void move(Particle p, double deltaTime);

    abstract void updateColors(Particle p, double deltaTime);

    abstract void updateSize(Particle p, double deltaTime);

    abstract boolean isParticleDone(Particle p);

    public boolean isFinished() {
        return getCount() <= 0;
    }
}
