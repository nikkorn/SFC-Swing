package com.dumbpug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {

    public static void main(String[] args) {
        final Display display = new Display();

        final JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.GRAY);
        container.add(display);
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(display, container);
            }
        });

        final JFrame frame = new JFrame("AspectRatio");
        frame.setBackground( Color.BLUE );
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);
        frame.getContentPane().add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private static void resizePreview(Display display, JPanel container) {
        float displayAspectRatio   = (float)Constants.DISPLAY_WIDTH / (float)Constants.DISPLAY_HEIGHT;
        float containerAspectRatio = (float)container.getWidth() / (float)container.getHeight();

        if (displayAspectRatio < containerAspectRatio) {
            // The container is wide and the display should stretch to match the container vertical space.
            display.setPreferredSize(new Dimension((int)(container.getHeight() * displayAspectRatio), container.getHeight()));
        } else {
            // The container is tall and the display should stretch to match the container horizontal space.
            display.setPreferredSize(new Dimension(container.getWidth(), (int)(container.getWidth() / displayAspectRatio)));
        }

        container.revalidate();
    }
}
