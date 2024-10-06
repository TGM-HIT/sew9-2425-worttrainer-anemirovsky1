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
    private JButton speichern, laden;

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
            mitte.add(bild);
            this.add(mitte, BorderLayout.CENTER);
        } catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Unteres Panel für Statusanzeigen und Buttons erstellen
        JPanel unten = new JPanel();
        unten.setLayout(new GridLayout(2,3));
        this.versucheTotal = new JLabel(String.valueOf(controller.getVersucheTotal()));
        this.versucheRichtig = new JLabel(String.valueOf(controller.getVersucheRichtig()));
        this.speichern = new JButton("Speichern");
        this.laden = new JButton("Laden");

        // Hinzufügen der Labels, Statusanzeigen und Buttons zum unteren Panel
        unten.add(new JLabel("Richtig erratene Wörter:"));
        unten.add(new JLabel("Gesamtanzahl:"));
        unten.add(versucheRichtig);
        unten.add(versucheTotal);
        unten.add(speichern);
        unten.add(laden);
        this.add(unten, BorderLayout.PAGE_END);

        // ActionListener  für Eingabefelder und Buttons
        this.eingabe.addActionListener(this.controller);
        this.eingabe.setActionCommand("Eingabe");

        this.speichern.addActionListener(this.controller);
        this.speichern.setActionCommand("Speichern");

        this.laden.addActionListener(this.controller);
        this.laden.setActionCommand("Laden");
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
     * @param url die URL des neues Bildes, das angezeigt werden soll
     */
    public void naechtesWort(String url) {
        // Textfeld leeren
        this.eingabe.setText("");
        // Labels mit aktuellen Werten für richtige und gesamte Versuche aktualisieren
        this.versucheRichtig.setText(String.valueOf(this.controller.getVersucheRichtig()));
        this.versucheTotal.setText(String.valueOf(this.controller.getVersucheTotal()));
        // Altes Bild entfernen
        this.remove(bild);
        JPanel mitte = new JPanel();
        ImageIcon imageIcon;
        try {
            // Neue URL für das Bild abrufen
            imageIcon = new ImageIcon(new URL(controller.getAktuelleUrl()));
        }catch(MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(250,250,Image.SCALE_SMOOTH);
        this.bild = new JLabel(new ImageIcon(image));
        mitte.add(bild);
        this.add(mitte, BorderLayout.CENTER);
        // Layout neu berechnen und die Ansicht aktualisieren
        this.revalidate(); // Layout neu berechnen
        this.repaint();    // Ansicht aktualisieren
    }
}
