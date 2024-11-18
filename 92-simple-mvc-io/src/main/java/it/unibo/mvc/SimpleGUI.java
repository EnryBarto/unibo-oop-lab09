package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 4;
    private final JFrame frame;
    private final Controller controller;

    /**
     * Initialize a new graphical interface.
     */
    public SimpleGUI() {
        this.controller = new Controller();
        frame = new JFrame("My first Java graphical interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        /*
         * Event handler
         */
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeToFile(textArea.getText());
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(frame, exception, "I/O Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Shows the interface.
     */
    public void start() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Create a new SimpleGUI and launch the application.
     * @param args Unused 
     */
    public static void main(final String[] args) {
        final SimpleGUI view = new SimpleGUI();
        view.start();
    }
}
