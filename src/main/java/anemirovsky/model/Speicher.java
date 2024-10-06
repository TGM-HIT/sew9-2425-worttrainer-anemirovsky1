package anemirovsky.model;

/**
 * Speicher-Interface
 * @author Andrij Nemirovsky
 * @version 2024-10-06
 */
public interface Speicher {

    /**
     * Diese Methode speichert den aktuellen Zustand des übergebenen Worttrainer-Objekts
     * @param worttrainer das Worttrainer-Objekt, dessen Zustand gespeichert werden soll
     */
    void speichern(Worttrainer worttrainer);

    /**
     * Diese Methode lädt den aktuellen Zustand in das übergebene Worttrainer-Objekt
     * @param worttrainer das Worttrainer-Objekt, dessen Zustand geladen werden soll
     */
    void laden(Worttrainer worttrainer);
}
