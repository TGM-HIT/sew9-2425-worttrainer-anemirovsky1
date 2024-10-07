package anemirovsky.view;

import javax.swing.*;
import anemirovsky.controller.Controller;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Panel-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-05
 */
public class Panel extends JPanel {

    private Controller controller;
    private JLabel versucheRichtig, versucheTotal, bild;
    private JTextField eingabe;
    private JButton speichern, laden, reset;

    /**
     * Konstruktor für die Panel-Klasse
     * @param controller Controller
     */
    public Panel(Controller controller) {
        // Layout des Panels setzen
        this.setLayout(new BorderLayout());
        this.controller = controller;

        // Oberes Panel für die Eingabe erstellen
        JPanel oben = new JPanel();
        oben.setLayout(new GridLayout(2,1));
        JLabel eingabeLabel = new JLabel("Welches Bild wird angezeigt?");
        oben.add(eingabeLabel);
        this.eingabe = new JTextField();
        oben.add(this.eingabe);
        this.add(oben, BorderLayout.PAGE_START);

        // Mittleres Panel für das Bild Erstellen
        JPanel mitte = new JPanel();
        mitte.setLayout(new GridLayout(1,1));
        try {
            // URL für das Bild vom Controller abrufen
            ImageIcon imageIcon = new ImageIcon(new URL(controller.getAktuelleUrl()));
            Image image = imageIcon.getImage();
            image = image.getScaledInstance(250,250,Image.SCALE_SMOOTH);
            this.bild = new JLabel(new ImageIcon(image));
            mitte.add(bild, BorderLayout.CENTER);
            this.add(mitte, BorderLayout.CENTER);
        } catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Unteres Panel für Statusanzeigen und Buttons erstellen
        JPanel unten = new JPanel(new GridLayout(2, 1));

        // Panel für die Statusanzeigen (Richtig erratene Wörter und Gesamtanzahl)
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        statusPanel.add(new JLabel("Richtig erratene Woerter:"));
        this.versucheRichtig = new JLabel(String.valueOf(controller.getVersucheRichtig()));
        statusPanel.add(this.versucheRichtig);
        statusPanel.add(new JLabel("Gesamtanzahl:"));
        this.versucheTotal = new JLabel(String.valueOf(controller.getVersucheTotal()));
        statusPanel.add(this.versucheTotal);
        unten.add(statusPanel);

        // Panel für die Buttons (Speichern und Laden)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.speichern = new JButton("Speichern");
        this.laden = new JButton("Laden");
        this.reset = new JButton("Versuche zurücksetzen");
        buttonPanel.add(speichern);
        buttonPanel.add(laden);
        unten.add(buttonPanel);
        unten.add(reset);
        this.add(unten, BorderLayout.PAGE_END);

        // ActionListener  für Eingabefelder und Buttons
        this.eingabe.addActionListener(this.controller);
        this.eingabe.setActionCommand("Eingabe");

        this.speichern.addActionListener(this.controller);
        this.speichern.setActionCommand("Speichern");

        this.laden.addActionListener(this.controller);
        this.laden.setActionCommand("Laden");

        this.reset.addActionListener(this.controller);
        this.reset.setActionCommand("Reset");
    }

    /**
     * Diese Methode gibt den Text zurück, den der Benutzer in das Eingabefeld eingegeben hat
     * @return der eingegebene Text
     */
    public String getEingabe() {
        return this.eingabe.getText();
    }

    /**
     * Diese Methode setzt das Panel zurück, aktualisiert die Labels und lädt ein neues Bild
     */
    public void naechtesWort() {
        // Textfeld leeren
        this.eingabe.setText("");
        // Labels mit aktuellen Werten für richtige und gesamte Versuche aktualisieren
        this.versucheRichtig.setText(String.valueOf(this.controller.getVersucheRichtig()));
        this.versucheTotal.setText(String.valueOf(this.controller.getVersucheTotal()));
        // Altes Bild entfernen
        this.remove(bild);
        JPanel mitte = new JPanel();
        ImageIcon imageIcon = null;
        try {
            // Neue URL für das Bild abrufen
            imageIcon = new ImageIcon(new URL(controller.getAktuelleUrl()));
        }catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(400,300,Image.SCALE_SMOOTH);
        this.bild = new JLabel(new ImageIcon(image));
        mitte.add(bild);
        this.add(mitte, BorderLayout.CENTER);
    }

    /**
     * Diese Methode aktualisiert das Eingabefeld und die Anzeige der richtigen und gesamten Versuche
     */
    public void weiter() {
        this.eingabe.setText("");
        this.versucheRichtig.setText(String.valueOf(this.controller.getVersucheRichtig()));
        this.versucheTotal.setText(String.valueOf(this.controller.getVersucheTotal()));
    }
}
