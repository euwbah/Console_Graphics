package com.euwbah.bouncing_ball;


public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2 round() {
        return new Vector2(Math.round(x), Math.round(y));
    }
    public int roundX() {
        return (int) Math.round(x);
    }
    public int roundY() {
        return (int) Math.round(y);
    }

    public Vector2 plus(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }
    public Vector2 plus(double x, double y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 minus(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }
    public Vector2 minus(double x, double y) {
        return new Vector2(this.x - x, this.y - y);
    }

    public Vector2 times(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    public void inc(Vector2 v) {
        inc(v.x, v.y);
    }

    public void inc(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void set(Vector2 v) {
        set(v.x, v.y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }
}
