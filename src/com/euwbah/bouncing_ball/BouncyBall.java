package com.euwbah.bouncing_ball;

import java.awt.event.KeyEvent;

public class BouncyBall extends App {

    private static BouncyBall g;

    private BouncyBall () {
        super(70, 30);

    }

    public static void main(String[] args) {

        System.out.println("It's working....");

        try {
            g = new BouncyBall();
            g.start();

            while (g.isRunning) Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        String ball =
                " ### \n" +
                "#####\n" +
                " ###";
        g.add(new Ball("ball", new Vector2(20, 5), ball, g.world));
    }

    @Override
    public void update(double deltaTime) {
        for(Entity e : g.world.entities) {
            e.update(deltaTime);
            if (e instanceof Ball) {
                Ball b = (Ball) e;
                b.incVelocity(0, 9);
            }
        }

        g.world.update();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                ((Ball) g.world.getEntity("ball")).incVelocity(0, -18);
                break;
            case KeyEvent.VK_LEFT:
                ((Ball) g.world.getEntity("ball")).incVelocity(-4, 0);
                break;
            case KeyEvent.VK_RIGHT:
                ((Ball) g.world.getEntity("ball")).incVelocity(4, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
