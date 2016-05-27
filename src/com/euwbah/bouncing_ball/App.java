package com.euwbah.bouncing_ball;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App implements KeyListener {

    private JFrame keyListener;

    public Screen world;

    private Runnable gameLoop;

    public boolean isRunning;


    public App(int width, int height) {
        keyListener = new JFrame("Key Listener");
        keyListener.setSize(400, 240);
        keyListener.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        keyListener.addKeyListener(this);
        keyListener.setFocusable(true);
        keyListener.setFocusTraversalKeysEnabled(false);

        isRunning = false;

        world = new Screen(width, height);
    }

    public void start() {
        keyListener.setVisible(true);

        isRunning = true;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //Set the isRunning flag to false to end the infinite game loop.
                isRunning = false;
                keyListener.dispose();
            }
        });


        init();

        gameLoop = () -> {
            double prevSeconds;
            double currSeconds;
            double elaspedSeconds = 0.07;//Just fake it as 25fps for first iter
            while(isRunning) {
                prevSeconds = System.nanoTime() / Math.pow(10, 9);
                try {
                    update(elaspedSeconds);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                currSeconds = System.nanoTime() / Math.pow(10, 9);
                elaspedSeconds = currSeconds - prevSeconds;
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    //Move along, nothing to see here...
                }
            }
        };
        gameLoop.run();
    }

    public void end() {
        isRunning = false;
    }

    /**
     * Can also use Entity.addToWorld
     * @param e The entity to add
     */
    public void add(Entity e) {
        world.entities.add(e);
    }

    public void init() {}

    public void update(double deltaTime) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
