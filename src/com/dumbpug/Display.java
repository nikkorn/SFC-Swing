package com.dumbpug;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display extends JPanel {
    /**
     * The buffered image that is drawn to the display panel.
     */
    private BufferedImage _bufferedImage;
    /**
     * The current mouse position.
     */
    private Position mousePosition;

    /**
     *
     */
    public Display() {
        this.setPreferredSize(new Dimension(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));
        this.setBackground(Color.darkGray);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                float pixelSize = (float)getWidth() / (float)Constants.DISPLAY_WIDTH;
                mousePosition.x = (int)(e.getX() / pixelSize);
                mousePosition.y = (int)(e.getY() / pixelSize);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                float pixelSize = (float)getWidth() / (float)Constants.DISPLAY_WIDTH;
                mousePosition = new Position();
                mousePosition.x = (int)(e.getX() / pixelSize);
                mousePosition.y = (int)(e.getY() / pixelSize);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePosition = null;
            }
        });

        File testImage = new File("TestImage.png");
        try {
            _bufferedImage = ImageIO.read(testImage);
        } catch (IOException e) {
            System.out.println("Cant read image: " + testImage.getAbsolutePath());
        }
    }

    public void Clear() {
        Graphics2D graphics = _bufferedImage.createGraphics();

        graphics.setPaint ( Color.darkGray );
        graphics.fillRect ( 0, 0, _bufferedImage.getWidth(), _bufferedImage.getHeight());

        graphics.dispose();

        repaint();
    }

    /**
     * Handles the JPanel paint operation.
     * @param g The panel graphics object.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // The only thing we do when drawing the display is render the display image over the whole panel.
        g.drawImage(this._bufferedImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
