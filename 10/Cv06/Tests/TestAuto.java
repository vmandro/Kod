import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAuto {
    private static LISTTestScoring scoring = null;
    private static final List<Auto> autaList = List.of(
            new Auto("Fabia", 1996),
            new Auto("Octavia", 1996),
            new Auto("Octavia", 2017),
            new Auto("Octavia", 2017),
            new Auto("Octavia", 2017),
            new Auto("Golf", 2003),
            new Auto("Golf", 2003),
            new Auto("Passat", 2015),
            new Auto("Tatrovka", 1984)
    );

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testHashMap() {
        Set<Auto> autaMnozina = new HashSet<>(autaList);
        assertEquals("new HashSet<>(autaList) -- zla velkost mnoziny", 6, autaMnozina.size());

        scoring.updateScore("lang:common_list_test_scoring_name", 50);
    }

    @Test
    public void testTreeMap() {
        SortedSet<Auto> autaUsporiadanaMnozina = new TreeSet<>(autaList);
        assertEquals("new TreeSet<>(autaList) -- zla velkost mnoziny", 6, autaUsporiadanaMnozina.size());

        Auto prev = new Auto("", Integer.MIN_VALUE);
        for (Auto a : autaUsporiadanaMnozina) {
            assertTrue("new TreeSet<>(autaList) -- nie je usporiadana (auto " + prev + " je pred " + a + ")",
                       prev.getRocnik() <= a.getRocnik());
        }

        scoring.updateScore("lang:common_list_test_scoring_name", 50);
    }

}
