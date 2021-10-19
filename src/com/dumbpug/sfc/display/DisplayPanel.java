package com.dumbpug.sfc.display;

import com.dumbpug.sfc.Constants;
import com.dumbpug.sfc.Position;
import com.dumbpug.sfc.input.MouseInputListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The JPanel that will act as our console display.
 */
public class DisplayPanel extends JPanel {
    /**
     * The buffered image that is drawn to the display panel.
     */
    private BufferedImage bufferedImage;
    /**
     * The current mouse position, or null if the mouse is not over the panel.
     */
    private Position mousePosition = null;
    /**
     * The mouse listeners attached to the display.
     */
    private ArrayList<MouseInputListener> mouseInputListeners = new ArrayList<MouseInputListener>();

    /**
     * Creates a new instance of the DisplayPanel class.
     */
    public DisplayPanel() {
        // Set the preferred size of the display, this will most likely change to match the application frame size.
        this.setPreferredSize(new Dimension(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));

        // Create the buffered image that is drawn to the display panel.
        this.bufferedImage = new BufferedImage(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        // We need to add the mouse listeners to track mouse position and clicks on this panel.
        addPanelMouseListeners();

        // We should clear and repaint so that we have a fresh display.
        clear();
        repaint();
    }

    /**
     * Gets the current mouse position, or null if the mouse is not over the panel.
     * @return The current mouse position, or null if the mouse is not over the panel.
     */
    public Position getCurrentMousePosition() {
        return this.mousePosition;
    }

    public void addMouseListener(MouseInputListener listener) {
        if (!this.mouseInputListeners.contains(listener)) {
            this.mouseInputListeners.add(listener);
        }
    }

    public void removeMouseListener(MouseInputListener listener) {
        if (this.mouseInputListeners.contains(listener)) {
            this.mouseInputListeners.remove(listener);
        }
    }

    public void removeMouseListeners() {
        this.mouseInputListeners.clear();
    }

    /**
     * Add the mouse listeners to track mouse position and clicks on this panel.
     */
    private void addPanelMouseListeners() {
        ArrayList<MouseInputListener> mouseInputListeners = this.mouseInputListeners;

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                float pixelSize = (float)getWidth() / (float)Constants.DISPLAY_WIDTH;
                mousePosition.setX((int)(e.getX() / pixelSize));
                mousePosition.setY((int)(e.getY() / pixelSize));

                for (MouseInputListener listener : mouseInputListeners) {
                    listener.onMove(mousePosition.getX(), mousePosition.getY());
                }
            }
        });

        this.addMouseListener(new MouseListener() {
            ArrayList<MouseInputListener> mouseInputListeners = this.mouseInputListeners;

            @Override
            public void mouseClicked(MouseEvent e) {
                for (MouseInputListener listener : mouseInputListeners) {
                    listener.onClick(mousePosition.getX(), mousePosition.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (MouseInputListener listener : mouseInputListeners) {
                    listener.onPress(mousePosition.getX(), mousePosition.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (MouseInputListener listener : mouseInputListeners) {
                    listener.onRelease(mousePosition.getX(), mousePosition.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                float pixelSize = (float)getWidth() / (float)Constants.DISPLAY_WIDTH;
                mousePosition = new Position((int)(e.getX() / pixelSize), (int)(e.getY() / pixelSize));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePosition = null;
            }
        });
    }

    public void clear() {
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.setPaint ( Color.darkGray );
        graphics.fillRect ( 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        graphics.dispose();
    }

    /**
     * Handles the JPanel paint operation.
     * @param g The panel graphics object.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // The only thing we do when drawing the display is render the display image over the whole panel.
        g.drawImage(this.bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
