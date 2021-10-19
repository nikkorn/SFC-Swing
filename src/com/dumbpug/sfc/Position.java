package com.dumbpug.sfc;

/**
 * Represents an x/y position.
 */
public class Position {
    /**
     * The x/y position.
     */
    private int x,y;

    /**
     * Creates a new instance of the Position class.
     * @param x The x position.
     * @param y The y position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new instance of the Position class with a default position of 0/0.
     */
    public Position() {
        this(0, 0);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int value) {
        this.x = value;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int value) {
        this.y = value;
    }
}
