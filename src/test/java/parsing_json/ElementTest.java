package parsing_json;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ElementTest {
    private Element element;

    @Before
    public void setUp() {
        element = new Element(
                "Hydrogen",
                "colorless gas",
                1.008,
                20.271,
                "diatomic nonmetal",
                "colorless",
                0.08988,
                "Henry Cavendish",
                13.99,
                28.836,
                "Antoine Lavoisier",
                1,
                1,
                "Gas",
                "https://en.wikipedia.org/wiki/Hydrogen",
                "https://en.wikipedia.org/wiki/File:Hydrogen_Spectra.jpg",
                "Hydrogen is a chemical element with chemical symbol H and atomic number 1. With an atomic weight of 1.00794 u, hydrogen is the lightest element on the periodic table. Its monatomic form (H) is the most abundant chemical substance in the Universe, constituting roughly 75% of all baryonic mass.",
                "H",
                1,
                1,
                new ArrayList<Integer>() {{add(1);}}
        );

    }

    @Test
    public void getName() {
        assertEquals("Hydrogen", element.getName());
    }

    @Test
    public void getAppearance() {
        assertEquals("colorless gas", element.getAppearance());
    }

    @Test
    public void getAtomic_mass() {
        assertEquals(1.008, element.getAtomicMass(), .01);
    }

    @Test
    public void getBoil() {
        assertEquals(20.271, element.getBoil(), .01);
    }

    @Test
    public void getCategory() {
        assertEquals("diatomic nonmetal", element.getCategory());
    }

    @Test
    public void getColor() {
        assertEquals("colorless", element.getColor());
    }

    @Test
    public void getDensity() {
        assertEquals(0.08988, element.getDensity(), .01);
    }

    @Test
    public void getDiscovered_by() {
        assertEquals("Henry Cavendish", element.getDiscoveredBy());
    }

    @Test
    public void getMelt() {
        assertEquals(13.99, element.getMelt(), .01);
    }

    @Test
    public void getMolar_heat() {
        assertEquals(28.836, element.getMolarHeat(), .01);
    }

    @Test
    public void getNamed_by() {
        assertEquals("Antoine Lavoisier", element.getNamedBy());
    }

    @Test
    public void getNumber() {
        assertEquals(1, (int)element.getNumber());
    }

    @Test
    public void getPeriod() {
        assertEquals(1, (int)element.getPeriod());
    }

    @Test
    public void getPhase() {
        assertEquals("Gas", element.getPhase());
    }

    @Test
    public void getSource() {
        assertEquals("https://en.wikipedia.org/wiki/Hydrogen", element.getSource());
    }

    @Test
    public void getSpectral_img() {
        assertEquals("https://en.wikipedia.org/wiki/File:Hydrogen_Spectra.jpg", element.getSpectralImage());
    }

    @Test
    public void getSummary() {
        assertEquals("Hydrogen is a chemical element with chemical symbol H and atomic number 1. With an atomic weight of 1.00794 u, hydrogen is the lightest element on the periodic table. Its monatomic form (H) is the most abundant chemical substance in the Universe, constituting roughly 75% of all baryonic mass.", element.getSummary());
    }

    @Test
    public void getSymbol() {
        assertEquals("H", element.getSymbol());
    }

    @Test
    public void getXpos() {
        assertEquals(1, (int)element.getXpos());
    }

    @Test
    public void getYpos() {
        assertEquals(1, (int)element.getYpos());
    }

    @Test
    public void getShells() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        assertEquals(expected, element.getShells());
    }

}