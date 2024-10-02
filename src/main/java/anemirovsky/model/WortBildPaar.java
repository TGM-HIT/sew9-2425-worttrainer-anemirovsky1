package anemirovsky.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * WortBildPaar
 * @author Andrij Nemirovsky
 * @version 2024-10-02
 */
public class WortBildPaar {

    private String wort;
    private String wortURL;

    /**
     * Konstruktor für die WortBildPaar-Klasse
     * @param wort Wort
     * @param wortURL UL
     */
    public WortBildPaar(String wort, String wortURL) {
        this.setWort(wort);
        this.setWortURL(wortURL);
    }

    /**
     * setter-Methode für das Wort
     * @param wort Wort
     */
    public void setWort(String wort) {
        if(wort == null || wort.isEmpty()) {
            throw new IllegalArgumentException("Wort darf nicht null oder leer sein");
        }
        this.wort = wort;
    }

    /**
     * getter-Methode für das Wort
     * @return das aktuell gesetzte Wort
     */
    public String getWort() {
        return this.wort;
    }

    /**
     * Diese Methode setzt die URL des Wortes, nachdem sie auf Korrektheit überprüft wurde
     * @param wortURL die URL des Wortes
     */
    public void setWortURL(String wortURL) {
        if(wortURL == null || wortURL.isEmpty()) {
            throw new IllegalArgumentException("Wort-URL darf nicht null oder leer sein");
        }

        try {
            new URL(wortURL);  // URL validieren
            this.wortURL = wortURL;  // URL zuweisen wenn sie gültig ist
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("ungültiges URL-Format", e);
        }

    }

    /**
     * getter-Methode für die URL des Wortes
     * @return die URL des aktuell gesetzten Wortes
     */
    public String getWortURL() {
        return this.wortURL;
    }
}
