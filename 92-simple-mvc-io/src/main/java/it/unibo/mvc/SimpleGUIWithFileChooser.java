package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 4;
    private final JFrame frame;
    private final Controller controller;

    /**
     * Initialize a new graphical interface.
     */
    public SimpleGUIWithFileChooser() {
        this.controller = new Controller();
        frame = new JFrame("My second Java graphical interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        final JPanel browsePanel = new JPanel();
        panel.add(browsePanel, BorderLayout.NORTH);
        browsePanel.setLayout(new BoxLayout(browsePanel, BoxLayout.LINE_AXIS));
        final JTextField filePath = new JTextField(controller.getFilePath());
        filePath.setEditable(false);
        browsePanel.add(filePath);
        final JButton browseButton = new JButton("Browse");
        browsePanel.add(browseButton);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        /*
         * Events handler
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
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getFile());
                switch (chooser.showSaveDialog(frame)) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(chooser.getSelectedFile().getAbsolutePath());
                        filePath.setText(controller.getFilePath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(chooser, "An error occured while choosing the file",
                                                "Error", JOptionPane.ERROR_MESSAGE);
                        break;
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
     * Create a new SimpleGUIWithFileChooser and launch the application.
     * @param args Unused 
     */
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser view = new SimpleGUIWithFileChooser();
        view.start();
    }
}
