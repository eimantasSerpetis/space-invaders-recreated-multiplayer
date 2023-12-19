package common.iterator;

import client.particles.Particle;

import java.util.Vector;

public class ParticleIterator implements Iterator {
    private int currentIndex;
    private final Particle[] arr;

    public ParticleIterator(Vector<Particle> particles) {
        this.arr = particles.toArray(new Particle[0]);
    }

    @Override
    public Particle getNext() {
        if (hasNext()) {
            return arr[currentIndex++];
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < arr.length;
    }
}
