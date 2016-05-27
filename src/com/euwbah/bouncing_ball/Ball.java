package com.euwbah.bouncing_ball;

public class Ball extends Entity {

    private Vector2 velocity;

    public Ball (String ID, Vector2 position, String text, Screen screen) {
        super(ID, position, text, screen);

        velocity = Vector2.Zero();
    }

    @Override
    public void update(double seconds) {
        this.position.inc(velocity.times(seconds));

        if(position.x <= 0 || position.x >= this.getScreen().WIDTH - 4) {
            this.setVelocity(new Vector2(velocity.x * -0.5, velocity.y));
        }

        if(position.y >= this.getScreen().HEIGHT - 4) {
            this.setVelocity(new Vector2(velocity.x, velocity.y * -0.9));
            this.position.y = getScreen().HEIGHT - 4;
        }
    }

    public void setVelocity(Vector2 v) {
        velocity.set(v);
    }

    public void incVelocity(double x, double y) { velocity.inc(new Vector2(x, y)); }
    public void incVelocity(Vector2 v) { velocity.inc(v); }
}
