package com.dumbpug.sfc;

import com.dumbpug.sfc.display.Display;
import com.dumbpug.sfc.display.DisplayPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Represents the Super Fantasy Console.
 */
public class SFC {
    /**
     * The SFC options.
     */
    private SFCOptions options;
    /**
     * The display.
     */
    private static Display display;

    /**
     * Creates a new instance of the SFC class.
     * @param options The SFC options.
     */
    public SFC(SFCOptions options) {
        this.options = options;
        createApplicationFrame();
    }

    /**
     * Gets the SFC display.
     * @return The SFC display.
     */
    public static Display getDisplay() {
        return SFC.display;
    }

    /**
     * Creates the application frame.
     */
    private static void createApplicationFrame() {
        final DisplayPanel displayPanel = new DisplayPanel();

        final JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.GRAY);
        container.add(displayPanel);
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeDisplayPanel(displayPanel, container);
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

        SFC.display = new Display(displayPanel);
    }

    /**
     * Handles a resize of the application frame and updates the display panel.
     * @param displayPanel The display panel.
     * @param container The display panel container.
     */
    private static void resizeDisplayPanel(DisplayPanel displayPanel, JPanel container) {
        float displayAspectRatio   = (float)Constants.DISPLAY_WIDTH / (float)Constants.DISPLAY_HEIGHT;
        float containerAspectRatio = (float)container.getWidth() / (float)container.getHeight();

        if (displayAspectRatio < containerAspectRatio) {
            // The container is wide and the display should stretch to match the container vertical space.
            displayPanel.setPreferredSize(new Dimension((int)(container.getHeight() * displayAspectRatio), container.getHeight()));
        } else {
            // The container is tall and the display should stretch to match the container horizontal space.
            displayPanel.setPreferredSize(new Dimension(container.getWidth(), (int)(container.getWidth() / displayAspectRatio)));
        }

        container.revalidate();
    }
}
