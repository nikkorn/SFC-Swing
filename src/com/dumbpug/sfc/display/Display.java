package com.dumbpug.sfc.display;

import com.dumbpug.sfc.Position;

/**
 * The console display.
 */
public class Display {
    /**
     * The display panel.
     */
    private DisplayPanel displayPanel;

    /**
     * Creates a new instance of the Display class.
     * @param displayPanel The display panel.
     */
    public Display (DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public Position getMousePosition() {
        return this.displayPanel.getCurrentMousePosition();
    }

    public int getPixel(int x, int y) {
        return 0;
    }

    public void setPixel(int x, int y, int pixel) {

    }

    public void clear() {
        this.displayPanel.clear();
    }

    public void draw() {
        this.displayPanel.repaint();
    }
}
