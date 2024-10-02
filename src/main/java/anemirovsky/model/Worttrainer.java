package anemirovsky.model;

import java.util.List;
import java.util.Random;

/**
 * Worttrainer
 * @author Andrij Nemirovsky
 * @version 2024-10-02
 */

public class Worttrainer {

    private List<WortBildPaar> woerter;
    private int aktuellesWortIndex;
    private int versucheTotal;
    private int versucheRichtig;

    /**
     * Konstruktor für die Worttrainer-Klasse
     */
    public Worttrainer() {}

    /**
     * Diese Methode setzt die Liste von Wörtern für den Worttrainer
     * @param woerter die Liste der Wort-Bild-Paare
     */
    public void setWoerterListe(List<WortBildPaar> woerter) {
        if(woerter == null || woerter.isEmpty()) {
            throw new IllegalArgumentException("Wörterliste darf nicht null oder leer sein.");
        }
        this.woerter = woerter;
    }

    /**
     * Diese Methode gibt die aktuelle Liste der Wort-Bild-Paare zurück.
     * @return die Liste der Wort-Bild-Paare
     */
    public List<WortBildPaar> getWoerterListe() {
        return woerter;
    }

    /**
     * Diese Methode überprüft, ob das eingegebene Wort mit dem aktuellen Wort übereinstimmt.
     * @param wortEingabe das vom Benutzer eingegebene Wort
     * @return true, wenn das eingegebene Wort korrekt ist, ansonsten false
     */
    public boolean checkWort(String wortEingabe) {
        versucheTotal++;
        if(woerter.get(aktuellesWortIndex).getWort().toLowerCase().equals(wortEingabe.toLowerCase())) {
            versucheRichtig++;
            return true;
        }
        return false;
    }

    /**
     * Diese Methode wählt ein zufälliges Wort-Bild-Paar aus der Liste aus
     * @return das zufällig ausgewähle Wort-Bild-Paar
     */
    public WortBildPaar getZufaelligesWort() {
        int indexZuffaellig;
        do {
            // Generiert eine Zufallszahl zwischen 0 (inklusive) und der Größe der Woerter-Liste (exklusive).
            indexZuffaellig = new Random().nextInt(woerter.size());
        } while(indexZuffaellig == aktuellesWortIndex); // Wiederholt, falls der Index gleich dem aktuellen Wort ist.
        aktuellesWortIndex = indexZuffaellig;
        return woerter.get(aktuellesWortIndex);
    }

    /**
     * Diese Methode setzt die Anzahl aller Versuche
     * @param versucheTotal die Anzahl aller Versuche
     */
    public void setVersucheTotal(int versucheTotal) {
        if(versucheTotal < 0) {
            throw new IllegalArgumentException("Versuche können nicht weniger als 0 sein");
        }
        this.versucheTotal = versucheTotal;
    }

    /**
     * Diese Methode gibt die Anzahl aller Versuche zurück
     * @return die Anzahl der richtigen Versuche
     */
    public int getVersucheTotal() {
        return this.versucheTotal;
    }

    /**
     * Diese Methode setzt die Anzahl der richtigen Versuche
     * @param versucheRichtig die Anzahl der richtigen Versuche
     */
    public void setVersucheRichtig(int versucheRichtig) {
        if(versucheRichtig < 0) {
            throw new IllegalArgumentException("Versuche können nicht weniger als 0 sein");
        }
        this.versucheRichtig = versucheRichtig;
    }

    /**
     * Diese Methode gibt die Anzahl der richtigen Versuche zurück
     * @return die Anzahl der richtigen Versuche
     */
    public int getVersucheRichtig() {
        return this.versucheRichtig;
    }




}
