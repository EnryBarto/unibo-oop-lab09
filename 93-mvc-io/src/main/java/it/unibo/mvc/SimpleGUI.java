package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 4;
    private final JFrame frame;
    private final SimpleController controller;

    /**
     * Initialize a new graphical interface.
     */
    public SimpleGUI() {
        controller = new SimpleController();
        frame = new JFrame("Simple GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        final JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        panel.add(scrollPane, BorderLayout.CENTER);
        final JButton print = new JButton("Print");
        final JButton showHistory = new JButton("Show history");
        final JPanel buttons = new JPanel();
        panel.add(buttons, BorderLayout.SOUTH);
        buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(showHistory);
        buttons.add(print);
        /*
         * Events handlers
         */
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNextString(textField.getText());
                controller.print();
                textField.setText("");
            }
        });
        showHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText("");
                for (final String s: controller.getHistory()) {
                    textArea.append(s + "\n");
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
     * Starts the application.
     * @param args Unusued
     */
    public static void main(final String[] args) {
        new SimpleGUI().start();
    }
}
