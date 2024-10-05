package anemirovsky;

import anemirovsky.model.WortBildPaar;
import anemirovsky.model.Worttrainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Worttrainertest-Klasse
 * @author Andrij Nemirovsky
 * @version 2024-10-05
 */
public class WorttrainerTest {

    @Test
    @DisplayName("Test 1: Wörterliste darf nicht leer oder null sein")
    void testSetWoerterListeNichtNullOderLeer() {
        Worttrainer trainer = new Worttrainer();
        assertThrows(IllegalArgumentException.class, () -> trainer.setWoerterListe(null));
        assertThrows(IllegalArgumentException.class, () -> trainer.setWoerterListe(new ArrayList<>()));
    }

    @Test
    @DisplayName("Test 2: Wörterliste korrekt gesetzt")
    void testSetWoerterListeKorrekt() {
        Worttrainer worttrainer = new Worttrainer();
        List<WortBildPaar> woerter = new ArrayList<>();
        woerter.add(new WortBildPaar("Hund", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg"));
        woerter.add(new WortBildPaar("Katze", "https://www.tierschutzbund.de/fileadmin/_processed_/d/1/csm_Katzen_auf_Seite_liegend_getigert_c_dtschb-sabine-muench.de_2e70d12553.jpg"));
        worttrainer.setWoerterListe(woerter);
        assertEquals(woerter, worttrainer.getWoerterListe());
    }

    @Test
    @DisplayName("Test 3: Zufälliges Wort wird korrekt ausgewählt")
    void testZufaelligesWort() {
        Worttrainer trainer = new Worttrainer();
        List<WortBildPaar> woerter = new ArrayList<>();
        woerter.add(new WortBildPaar("Hund", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg"));
        woerter.add(new WortBildPaar("Katze", "https://www.tierschutzbund.de/fileadmin/_processed_/d/1/csm_Katzen_auf_Seite_liegend_getigert_c_dtschb-sabine-muench.de_2e70d12553.jpg"));
        trainer.setWoerterListe(woerter);
        WortBildPaar zufaelligesWort = trainer.getZufaelligesWort();
        assertNotNull(zufaelligesWort);
        assertTrue(woerter.contains(zufaelligesWort));
    }

    @Test
    @DisplayName("Test 4: Wort wird korrekt überprüft")
    void testCheckWortKorrekt() {
        Worttrainer trainer = new Worttrainer();
        List<WortBildPaar> woerter = new ArrayList<>();
        woerter.add(new WortBildPaar("Hund", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg"));
        woerter.add(new WortBildPaar("Katze", "https://www.tierschutzbund.de/fileadmin/_processed_/d/1/csm_Katzen_auf_Seite_liegend_getigert_c_dtschb-sabine-muench.de_2e70d12553.jpg"));
        trainer.setWoerterListe(woerter);
        WortBildPaar zufaelligesWort = trainer.getZufaelligesWort();
        boolean isCorrect = trainer.checkWort(zufaelligesWort.getWort());
        assertTrue(isCorrect);
    }

    @Test
    @DisplayName("Test 5: Wort wird als falsch erkannt")
    void testCheckWortFalsch() {
        Worttrainer trainer = new Worttrainer();
        List<WortBildPaar> woerter = new ArrayList<>();
        woerter.add(new WortBildPaar("Hund", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst.jpg"));
        woerter.add(new WortBildPaar("Katze", "https://www.tierschutzbund.de/fileadmin/_processed_/d/1/csm_Katzen_auf_Seite_liegend_getigert_c_dtschb-sabine-muench.de_2e70d12553.jpg"));
        trainer.setWoerterListe(woerter);
        trainer.getZufaelligesWort();
        boolean isCorrect = trainer.checkWort("Fisch");
        assertFalse(isCorrect);
    }

    @Test
    @DisplayName("Test 6: Anzahl der richtigen Versuche wird korrekt gesetzt")
    void testSetVersucheRichtig() {
        Worttrainer trainer = new Worttrainer();
        trainer.setVersucheRichtig(5);
        assertEquals(5, trainer.getVersucheRichtig());
    }

    @Test
    @DisplayName("Test 7: Anzahl der richtigen Versuche darf nicht negativ sein")
    void testSetVersucheRichtigNichtNegativ() {
        Worttrainer trainer = new Worttrainer();
        assertThrows(IllegalArgumentException.class, () -> trainer.setVersucheRichtig(-1));
    }

    @Test
    @DisplayName("Test 8: Anzahl der totalen Versuche wird korrekt gesetzt")
    void testSetVersucheTotal() {
        Worttrainer trainer = new Worttrainer();
        trainer.setVersucheTotal(10);
        assertEquals(10, trainer.getVersucheTotal());
    }

    @Test
    @DisplayName("Test 7: Anzahl der totalen Versuche darf nicht negativ sein")
    void testSetVersucheTotalNichtNegativ() {
        Worttrainer trainer = new Worttrainer();
        assertThrows(IllegalArgumentException.class, () -> trainer.setVersucheTotal(-1));
    }


}
