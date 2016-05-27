package com.euwbah.bouncing_ball;

import java.io.*;
import java.util.ArrayList;

/**
 * Assume screen is 40x20 ASCII text monospace
 */
public class Screen {

    public final int WIDTH, HEIGHT;
    /**
     * 40x20
     */

    public ArrayList<Entity> entities;

    private ArrayList<String> display;
    private BufferedWriter output;

    public Screen(int x, int y) {
        WIDTH = x;
        HEIGHT = y;
        this.display = new ArrayList<>();

        entities = new ArrayList<>();

        try {
            output = new BufferedWriter(new OutputStreamWriter(new
                    FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 1024);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private int getIndex(int x, int y) {
        return y * WIDTH + x;
    }

    private String getLine(int y) {
        return display.get(y);
    }

    private void display() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            for (String str : display) {
                output.write(str);
                output.newLine();
            }
            output.flush();
        } catch (IOException e) {
            //Do nothing...
        } catch (InterruptedException e) {
            //Do nothing...
        }
    }

    private void clearBuffer() {
        String s = "";

        for(int i = 0; i < WIDTH; i++) {
            s += " ";
        }

        for(int i = 0; i < HEIGHT; i++) {
            display.add(s);
        }
    }

    /**
     * Replace a part of the line in the display.
     * @param position Vector2 position to start replacing from
     * @param text Text to replace with
     */
    public void replace(Vector2 position, String text) {
        Vector2 v = new Vector2(position);

        if(v.roundY() > HEIGHT - 1)
            return;

        int maxWidth = WIDTH - v.roundX();
        if (maxWidth < text.length())
            text = text.substring(0, maxWidth);

        if(v.x <= 0.5) {
            if (-v.roundX() >= text.length())
                return;
            text = text.substring(0 - v.roundX());
            v.x = 0.5;
        }

        display = Graphics.replace(display, position, text);
    }

    public void update() {
        clearBuffer();
        for(Entity e : entities) {
            e.drawEntity();
        }
        display();
        display.clear();//reset display
    }

    public Entity getEntity(String ID) {
        for(Entity e : entities) {
            if(e.getID().equals(ID))
                return e;
        }

        return null;
    }
}
