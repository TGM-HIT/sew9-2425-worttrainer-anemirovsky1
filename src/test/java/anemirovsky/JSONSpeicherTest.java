package anemirovsky;

import anemirovsky.model.JSONSpeicher;
import anemirovsky.model.WortBildPaar;
import anemirovsky.model.Worttrainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JSON-Speicher-Test-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-06
 */
public class JSONSpeicherTest {

    private Worttrainer worttrainer;
    private JSONSpeicher jsonSpeicher;

    @BeforeEach
    public void setUp() {
        // Initialisieren des JSON-Speichers und des Worttrainers
        jsonSpeicher = new JSONSpeicher();
        worttrainer = new Worttrainer();

        // Erstellen einer Liste von Wort-Bild-Paaren und Setzen der Werte für den Worttrainer
        List<WortBildPaar> woerter = Arrays.asList(
                new WortBildPaar("Hund", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg"),
                new WortBildPaar("Katze", "https://www.tierschutzbund.de/fileadmin/_processed_/d/1/csm_Katzen_auf_Seite_liegend_getigert_c_dtschb-sabine-muench.de_2e70d12553.jpg"),
                new WortBildPaar("Hai", "https://upload.wikimedia.org/wikipedia/commons/5/56/White_shark.jpg")
        );
        worttrainer.setWoerterListe(woerter);
        worttrainer.setVersucheTotal(10);
        worttrainer.setVersucheRichtig(8);
    }

    @DisplayName("Testet, ob das Speichern und Laden eines Worttrainers korrekt funktioniert")
    @Test
    public void testSpeichernUndLaden() {
        // Speichern des Worttrainers
        jsonSpeicher.speichern(worttrainer);

        // Neuer Worttrainer, um das Laden zu testen
        Worttrainer geladenerWorttrainer = new Worttrainer();
        jsonSpeicher.laden(geladenerWorttrainer);

        // Vergleichen der geladenen Daten mit den ursprünglichen Daten
        assertEquals(worttrainer.getWoerterListe().size(), geladenerWorttrainer.getWoerterListe().size());
        assertEquals(worttrainer.getVersucheTotal(), geladenerWorttrainer.getVersucheTotal());
        assertEquals(worttrainer.getVersucheRichtig(), geladenerWorttrainer.getVersucheRichtig());

        // Vergleichen einzelner Wort-Bild-Paare
        for (int i = 0; i < worttrainer.getWoerterListe().size(); i++) {
            WortBildPaar original = worttrainer.getWoerterListe().get(i);
            WortBildPaar geladen = geladenerWorttrainer.getWoerterListe().get(i);
            assertEquals(original.getWort(), geladen.getWort());
            assertEquals(original.getWortURL(), geladen.getWortURL());
        }
    }

}
