package anemirovsky;

import anemirovsky.model.WortBildPaar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Andrij Nemirovsky
 * @version 2024-10-05
 */
public class WortBildPaarTest {

    @Test
    @DisplayName("Test 1: Wort darf nicht leer sein")
    void testWortDarfNichtLeerSein() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortBildPaar("", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg");
        });
    }

    @Test
    @DisplayName("Test 2: Wort darf nicht null sein")
    void testWortDarfNichtNullSein() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortBildPaar(null, "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg");
        });
    }

    @Test
    @DisplayName("Test 3: URL darf nicht leer sein")
    void testWortUrlDarfNichtLeerSein() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortBildPaar("Hund", "");
        });
    }

    @Test
    @DisplayName("Test 4: URL darf nicht null sein")
    void testWortUrlDarfNichtNullSein() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortBildPaar("Hund", null);
        });
    }

    @Test
    @DisplayName("Test 5: URL muss gültig sein")
    void testWortUrlMussGueltigSein() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WortBildPaar("Hund", "ungültige-url");
        });
    }

    @Test
    @DisplayName("Test 6: Wort und URL richtig gesetzt")
    void testWortUndUrlRichtigGesetzt() {
        WortBildPaar wortBildPaar = new WortBildPaar("Hund", "https://example.com/hund.jpg");
        assertEquals("Hund", wortBildPaar.getWort());
        assertEquals("https://example.com/hund.jpg", wortBildPaar.getWortURL());
    }
}
