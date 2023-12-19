package common.iterator;

import client.particles.Particle;

import java.util.Vector;

public class ParticleCollection implements IterableCollection {
    private final Vector<Particle> particles;

    public ParticleCollection(Vector<Particle> particles) {
        this.particles = particles;
    }

    public void add(Particle particle) {
        particles.add(particle);
    }

    public void removeAll(Vector<Particle> toRemove) {
        particles.removeAll(toRemove);
    }

    public int size() {
        return particles.size();
    }

    @Override
    public ParticleIterator createIterator() {
        return new ParticleIterator(particles);
    }
}
