package de.tum.in.ase.eist;

public class PenguinAdapter implements Penguin {
    // TODO ; implement PenguinAdapter that implement Pinguin and take waddle() from bird
    private Bird bird;

    public PenguinAdapter(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void waddle() {
        bird.walk();
    }

    @Override
    public void eat(Fish fish) {
        bird.eat(null, null, fish);
    }
}
