package anemirovsky.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * JSON-Speicher-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-06
 */
public class JSONSpeicher implements Speicher {

    public JSONSpeicher() {

    }

    /**
     * Diese Methode speichert den aktuellen Zustand des übergebenen Worttrainer-Objekts
     * @param worttrainer das Worttrainer-Objekt, dessen Zustand gespeichert werden soll
     */

    @Override
    public void speichern(Worttrainer worttrainer) {
        try {
            // Erstellen eines JSON-Objekts, um die Daten des Worttrainers zu speichern
            JSONObject object = new JSONObject();
            // Speichern der Gesamtzahl der Versuche
            object.put("versucheTotal", worttrainer.getVersucheTotal());
            // Speichern der Anzahl der richtigen Versuche
            object.put("versucheRichtig", worttrainer.getVersucheRichtig());
            // Erstellen eines JSON-Arrays für die Wort-Bild-Paare
            JSONArray woerter = new JSONArray();
            // Iterieren durch die Liste der Wort-Bild-Paare des Worttrainers
            for(WortBildPaar wort: worttrainer.getWoerterListe()) {
                // Erstellen eines JSON-Objekts für jedes Wort-Bild-Paar
                JSONObject wortObject = new JSONObject();
                try {
                    // Speichern des Worts
                    wortObject.put("wort", wort.getWort());
                    // Speichern der URL des Wortes
                    wortObject.put("wortURL", wort.getWortURL());
                    // Hinzufügen des Wort-Bild-Objekts zum JSON-Array
                    woerter.put(wortObject);
                } catch (JSONException jsonException) {
                    throw new RuntimeException("Fehler beim Hinzufügen des Wort-Bild-Paares zum JSON-Objekt", jsonException);
                }
            }
            // Speichern des Arrays der Wörter im Haupt-JSON-Objekt
            object.put("woerter", woerter);
            // Schreiben des JSON-Objekts als String in die Datei "worttrainer.json"
            Files.write(Paths.get("worttrainer.json"), object.toString(4).getBytes());

        } catch(IOException ioe) {
            throw new RuntimeException("Fehler beim Speichern der Daten", ioe);
        }
    }

    /**
     * Diese Methode lädt den aktuellen Zustand in das übergebene Worttrainer-Objekt
     * @param worttrainer das Worttrainer-Objekt, dessen Zustand geladen werden soll
     */

    @Override
    public void laden(Worttrainer worttrainer) {
        try {
            // Überprüfen, ob die Datei existiert und nicht leer ist
            if (!Files.exists(Paths.get("worttrainer.json"))) {
                throw new IOException("Die Datei 'worttrainer.json' existiert nicht.");
            }

            // Lesen des Inhalts der JSON-Datei als String
            String pfad = new String(Files.readAllBytes(Paths.get("worttrainer.json")));

            // Überprüfen, ob die Datei leer ist
            if (pfad.trim().isEmpty()) {
                throw new IOException("Die Datei 'worttrainer.json' ist leer.");
            }

            // Erstellen eines JSON-Objekts aus dem geladenen String
            JSONObject object = new JSONObject(pfad);

            // Laden der Gesamtzahl der Versuche
            int total;
            try {
                // Abrufen des Wertes "versucheTotal" aus dem JSON-Objekt
                total = object.getInt("versucheTotal");
            } catch (JSONException jsonException) {
                // falls "total" nicht im JSON vorhanden ist
                total = 0;
            }
            // Setzen der Gesamtzahl der Versuche im Worttrainer-Objekt
            worttrainer.setVersucheTotal(total);

            // Laden der Anzahl der richtigen Versuche
            int richtig;
            try {
                // Abrufen des Wertes "versucheRichtig" aus dem JSON-Objekt
                richtig = object.getInt("versucheRichtig");
            } catch (JSONException e) {
                // falls "richtig" nicht im JSON vorhanden ist
                richtig = 0;
            }
            // Setzen der Anzahl der richtigen Versuche im Worttrainer-Objekt
            worttrainer.setVersucheRichtig(richtig);

            // Laden der Liste der Wort-Bild-Paare
            JSONArray woerter = object.getJSONArray("woerter");
            // Erstellen einer neuen ArrayList für die Wort-Bild-Paare
            ArrayList<WortBildPaar> wordList = new ArrayList<>();
            // Iterieren durch das JSONArray der Wörter
            for (Object o : woerter) {
                // Konvertieren jedes Elements des Arrays in ein JSON-Objekt
                JSONObject wortObject = (JSONObject) o;
                // Abrufen des Wortes
                String word = wortObject.getString("wort");
                // Abrufen der URL des Wortes
                String url = wortObject.getString("wortURL");
                // Erstellen eines neuen WortBildPaar-Objekts und Hinzufügen zur Liste
                wordList.add(new WortBildPaar(word, url));
            }
            // Setzen der geladenen Wort-Bild-Liste im Worttrainer-Objekt
            worttrainer.setWoerterListe(wordList);
        } catch (IOException ioe) {
            throw new RuntimeException("Fehler beim Laden der Daten", ioe);
        }
    }
}
