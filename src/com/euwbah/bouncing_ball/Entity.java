package com.euwbah.bouncing_ball;

public abstract class Entity {

    private String ID;

    public Vector2 position;
    private Screen screen;

    /**
     * Relative to position when printed. Top left = (0, 0) + position
     */
    private String text;

    public Entity(String ID, Vector2 position, String text, Screen screen) {
        this.ID = ID;
        this.position = position;
        this.text = text;
        this.screen = screen;
    }

    /**
     * Entities can also be added using App.add()
     */
    public final void addToWorld() {
        screen.entities.add(this);
    }

    private final String getLine(int y) {
        return text.split("\\n")[y];
    }

    private final int getNubmerOfLines() {
        return text.split("\\n").length;
    }

    /**
     * Use this to "draw" the entity on the console
     * @return Returns a string of the entire console buffer, but with the entity placed in it.
     */
    public final void drawEntity() {
        for(int i = 0; i < this.getNubmerOfLines(); i++) {

            if(i + position.roundY() > screen.HEIGHT - 1 ||
                    i + position.roundY() < 0)
                break;

            String line = this.getLine(i);

            screen.replace(position.plus(0, i), line);
        }
    }

    public final String getID() {
        return ID;
    }

    public final Screen getScreen() {
        return this.screen;
    }

    public abstract void update(double milli);
}
