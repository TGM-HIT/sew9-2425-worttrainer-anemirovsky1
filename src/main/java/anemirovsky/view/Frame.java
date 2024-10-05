package anemirovsky.view;

import javax.swing.*;

/**
 * Frame-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-05
 */
public class Frame extends JFrame {

    /**
     * Konstruktor für die Frame-Klasse
     * @param jPanel das JPanel, das in diesem Frame angezeigt werden soll
     */
    public Frame(JPanel jPanel) {
        super("Worttrainer Reloaded - Andrij Nemirovsky");
        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
