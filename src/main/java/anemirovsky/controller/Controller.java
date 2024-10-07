package anemirovsky.controller;

import anemirovsky.model.Worttrainer;
import anemirovsky.view.Frame;
import anemirovsky.view.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-05
 */
public class Controller implements ActionListener {
    private final Frame frame;
    private Panel panel;
    private Worttrainer worttrainer;

    /**
     * Konstruktor für Controller-Klasse
     */
    public Controller() {
        this.worttrainer = new Worttrainer();
        this.worttrainer.laden();
        this.panel = new Panel(this);
        this.frame = new Frame(panel);
    }

    /**
     * Diese Methode gibt die URL des aktuellen Wort-Bild-Paares zurück
     * @return Url des aktuellen Wort-Bild-Paares
     */
    public String getAktuelleUrl() {
        return worttrainer.getZufaelligesWort().getWortURL();
    }

    /**
     * Diese Methode gibt die Anzahl der gesamten Versuche zurück
     * @return Anzahl der gesamten Versuche
     */
    public int getVersucheTotal() {
        return worttrainer.getVersucheTotal();
    }

    /**
     * Diese Methode gibt die Anzahl der richtigen Versuche zurück
     * @return Anzahl der richtigen Versuche
     */
    public int getVersucheRichtig() {
        return worttrainer.getVersucheRichtig();
    }

    /**
     * Diese Methode reagiert auf Aktionen des Benutzers, wie z.B. das Klicken auf einen Button
     * @param actionEvent das ActionEvent, das ausgelöst wird (z.B. klicken auf Buttons)
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        switch(actionCommand) {
            case "Eingabe":
                // Überprüfen, ob das eingegebene Wort korrekt ist
                if (this.worttrainer.checkWort(panel.getEingabe())) {
                    // Panel zurücksetzen, Labels aktualisieren und Bild laden
                    this.panel.naechtesWort();
                }
                // Unabhängig vom Ergebnis, die Ansicht aktualisieren
                this.panel.weiter();
                if(getVersucheTotal() == 10) {
                    JOptionPane.showMessageDialog(frame, "Spiel zu Ende! Gesamte Versuche: " + this.worttrainer.getVersucheTotal() + " Davon Richtig: " + this.worttrainer.getVersucheRichtig(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    this.frame.setVisible(false);
                    this.frame.dispose();
                }
                break;
            case "Speichern":
                this.worttrainer.speichern();
                break;
            case "Laden":
                this.worttrainer.laden();
                // Panel zurücksetzen, Labels aktualisieren und Bild laden
                this.panel.naechtesWort();
                break;
        }
    }

    /**
     * Main-Methode
     * @param args Argument
     */
    public static void main(String[] args) {
        new Controller(); //startet die Anwendung durch Erzeugen eines Controllers
    }
}
