import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRekurzia {

    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void fooMemoizacia() {
        assertEquals(0L, Rekurzia.fooMemoizacia(0L));
        assertEquals(1L, Rekurzia.fooMemoizacia(1L));
        assertEquals(2027L, Rekurzia.fooMemoizacia(2L));
        assertEquals(4052L, Rekurzia.fooMemoizacia(3L));
        assertEquals(4051L, Rekurzia.fooMemoizacia(4L));
        assertEquals(2025L, Rekurzia.fooMemoizacia(5L));

        for (long n = 2; n <= 20; n++) {
            long expected = 2026L
                    + Rekurzia.fooMemoizacia(n - 1)
                    - Rekurzia.fooMemoizacia(n - 2);

            assertEquals(expected, Rekurzia.fooMemoizacia(n));
        }

        assertEquals("fooMemoizacia(6) = " + 0L, 0L, Rekurzia.fooMemoizacia(6L));
        assertEquals("fooMemoizacia(16) = " + 4051L, 4051L, Rekurzia.fooMemoizacia(16L));
        assertEquals("fooMemoizacia(225) = " + 4052, 4052, Rekurzia.fooMemoizacia(225L));
        assertEquals("fooMemoizacia(617) = " + 2025, 2025, Rekurzia.fooMemoizacia(617L));
        assertEquals("fooMemoizacia(639) = " + 4052, 4052, Rekurzia.fooMemoizacia(639L));
        assertEquals("fooMemoizacia(724) = " + 4051, 4051, Rekurzia.fooMemoizacia(724L));
        assertEquals("fooMemoizacia(594) = " + 0, 0, Rekurzia.fooMemoizacia(594L));
        assertEquals("fooMemoizacia(249) = " + 4052, 4052, Rekurzia.fooMemoizacia(249L));
        assertEquals("fooMemoizacia(777) = " + 4052, 4052, Rekurzia.fooMemoizacia(777L));
        assertEquals("fooMemoizacia(520) = " + 4051, 4051, Rekurzia.fooMemoizacia(520L));
        assertEquals("fooMemoizacia(786) = " + 0, 0, Rekurzia.fooMemoizacia(786L));
        assertEquals("fooMemoizacia(689) = " + 2025, 2025, Rekurzia.fooMemoizacia(689L));
        assertEquals("fooMemoizacia(602) = " + 2027, 2027, Rekurzia.fooMemoizacia(602L));
        assertEquals("fooMemoizacia(774) = " + 0, 0, Rekurzia.fooMemoizacia(774L));
        assertEquals("fooMemoizacia(592) = " + 4051, 4051, Rekurzia.fooMemoizacia(592L));
        assertEquals("fooMemoizacia(498) = " + 0, 0, Rekurzia.fooMemoizacia(498L));
        assertEquals("fooMemoizacia(206) = " + 2027, 2027, Rekurzia.fooMemoizacia(206L));
        assertEquals("fooMemoizacia(493) = " + 1, 1, Rekurzia.fooMemoizacia(493L));
        assertEquals("fooMemoizacia(685) = " + 1, 1, Rekurzia.fooMemoizacia(685L));
        assertEquals("fooMemoizacia(374) = " + 2027, 2027, Rekurzia.fooMemoizacia(374L));
        assertEquals("fooMemoizacia(692) = " + 2027, 2027, Rekurzia.fooMemoizacia(692L));
        assertEquals("fooMemoizacia(916) = " + 4051, 4051, Rekurzia.fooMemoizacia(916L));
        assertEquals("fooMemoizacia(46) = " + 4051, 4051, Rekurzia.fooMemoizacia(46L));

        TestRekurzia.scoring.updateScore("lang:common_list_test_scoring_name",20);
    }

    @Test
    public void fooBezRekurzie() {
        assertEquals(0L, Rekurzia.fooBezRekurzie(0L));
        assertEquals(1L, Rekurzia.fooBezRekurzie(1L));
        assertEquals(2027L, Rekurzia.fooBezRekurzie(2L));
        assertEquals(4052L, Rekurzia.fooBezRekurzie(3L));
        assertEquals(4051L, Rekurzia.fooBezRekurzie(4L));
        assertEquals(2025L, Rekurzia.fooBezRekurzie(5L));

        for (long n = 2; n <= 20; n++) {
            long expected = 2026L
                    + Rekurzia.fooBezRekurzie(n - 1)
                    - Rekurzia.fooBezRekurzie(n - 2);

            assertEquals(expected, Rekurzia.fooBezRekurzie(n));
        }

        assertEquals("fooBezRekurzie(804682) = " + 4051, 4051, Rekurzia.fooBezRekurzie(804682L));
        assertEquals("fooBezRekurzie(243247) = " + 1, 1, Rekurzia.fooBezRekurzie(243247L));
        assertEquals("fooBezRekurzie(339559) = " + 1, 1, Rekurzia.fooBezRekurzie(339559L));
        assertEquals("fooBezRekurzie(250285) = " + 1, 1, Rekurzia.fooBezRekurzie(250285L));
        assertEquals("fooBezRekurzie(461266) = " + 4051, 4051, Rekurzia.fooBezRekurzie(461266L));
        assertEquals("fooBezRekurzie(150443) = " + 2025, 2025, Rekurzia.fooBezRekurzie(150443L));
        assertEquals("fooBezRekurzie(307361) = " + 2025, 2025, Rekurzia.fooBezRekurzie(307361L));
        assertEquals("fooBezRekurzie(92342) = " + 2027, 2027, Rekurzia.fooBezRekurzie(92342L));
        assertEquals("fooBezRekurzie(532880) = " + 2027, 2027, Rekurzia.fooBezRekurzie(532880L));
        assertEquals("fooBezRekurzie(701464) = " + 4051, 4051, Rekurzia.fooBezRekurzie(701464L));
        assertEquals("fooBezRekurzie(329618) = " + 2027, 2027, Rekurzia.fooBezRekurzie(329618L));
        assertEquals("fooBezRekurzie(390191) = " + 2025, 2025, Rekurzia.fooBezRekurzie(390191L));
        assertEquals("fooBezRekurzie(632568) = " + 0, 0, Rekurzia.fooBezRekurzie(632568L));
        assertEquals("fooBezRekurzie(154857) = " + 4052, 4052, Rekurzia.fooBezRekurzie(154857L));
        assertEquals("fooBezRekurzie(245757) = " + 4052, 4052, Rekurzia.fooBezRekurzie(245757L));
        assertEquals("fooBezRekurzie(244062) = " + 0, 0, Rekurzia.fooBezRekurzie(244062L));
        assertEquals("fooBezRekurzie(960171) = " + 4052, 4052, Rekurzia.fooBezRekurzie(960171L));
        assertEquals("fooBezRekurzie(161289) = " + 4052, 4052, Rekurzia.fooBezRekurzie(161289L));
        assertEquals("fooBezRekurzie(767470) = " + 4051, 4051, Rekurzia.fooBezRekurzie(767470L));
        assertEquals("fooBezRekurzie(425124) = " + 0, 0, Rekurzia.fooBezRekurzie(425124L));
        assertEquals("fooBezRekurzie(36020) = " + 2027, 2027, Rekurzia.fooBezRekurzie(36020L));

        TestRekurzia.scoring.updateScore("lang:common_list_test_scoring_name",40);
    }

    @Test
    public void fooBezLimitov() {
        assertEquals(0L, Rekurzia.fooBezLimitov(0L));
        assertEquals(1L, Rekurzia.fooBezLimitov(1L));
        assertEquals(2027L, Rekurzia.fooBezLimitov(2L));
        assertEquals(4052L, Rekurzia.fooBezLimitov(3L));
        assertEquals(4051L, Rekurzia.fooBezLimitov(4L));
        assertEquals(2025L, Rekurzia.fooBezLimitov(5L));

        for (long n = 2; n <= 20; n++) {
            long expected = 2026L
                    + Rekurzia.fooBezLimitov(n - 1)
                    - Rekurzia.fooBezLimitov(n - 2);

            assertEquals(expected, Rekurzia.fooBezLimitov(n));
        }

        assertEquals("fooBezLimitov(129979814106) = " + 0, 0, Rekurzia.fooBezLimitov(129979814106L));
        assertEquals("fooBezLimitov(341342681106) = " + 0, 0, Rekurzia.fooBezLimitov(341342681106L));
        assertEquals("fooBezLimitov(403526307574) = " + 4051, 4051, Rekurzia.fooBezLimitov(403526307574L));
        assertEquals("fooBezLimitov(99983799650) = " + 2027, 2027, Rekurzia.fooBezLimitov(99983799650L));
        assertEquals("fooBezLimitov(340747428173) = " + 2025, 2025, Rekurzia.fooBezLimitov(340747428173L));
        assertEquals("fooBezLimitov(315902577276) = " + 0, 0, Rekurzia.fooBezLimitov(315902577276L));
        assertEquals("fooBezLimitov(165925577845) = " + 1, 1, Rekurzia.fooBezLimitov(165925577845L));
        assertEquals("fooBezLimitov(250358551572) = " + 0, 0, Rekurzia.fooBezLimitov(250358551572L));
        assertEquals("fooBezLimitov(863165199376) = " + 4051, 4051, Rekurzia.fooBezLimitov(863165199376L));
        assertEquals("fooBezLimitov(375744866832) = " + 0, 0, Rekurzia.fooBezLimitov(375744866832L));
        assertEquals("fooBezLimitov(117890362821) = " + 4052, 4052, Rekurzia.fooBezLimitov(117890362821L));
        assertEquals("fooBezLimitov(622141918227) = " + 4052, 4052, Rekurzia.fooBezLimitov(622141918227L));
        assertEquals("fooBezLimitov(66581535191) = " + 2025, 2025, Rekurzia.fooBezLimitov(66581535191L));
        assertEquals("fooBezLimitov(449764826279) = " + 2025, 2025, Rekurzia.fooBezLimitov(449764826279L));
        assertEquals("fooBezLimitov(175530517343) = " + 2025, 2025, Rekurzia.fooBezLimitov(175530517343L));
        assertEquals("fooBezLimitov(826553242885) = " + 1, 1, Rekurzia.fooBezLimitov(826553242885L));
        assertEquals("fooBezLimitov(211303660074) = " + 0, 0, Rekurzia.fooBezLimitov(211303660074L));
        assertEquals("fooBezLimitov(436451711876) = " + 2027, 2027, Rekurzia.fooBezLimitov(436451711876L));
        assertEquals("fooBezLimitov(784263485489) = " + 2025, 2025, Rekurzia.fooBezLimitov(784263485489L));
        assertEquals("fooBezLimitov(787867281053) = " + 2025, 2025, Rekurzia.fooBezLimitov(787867281053L));
        assertEquals("fooBezLimitov(93431219377) = " + 1, 1, Rekurzia.fooBezLimitov(93431219377L));

        TestRekurzia.scoring.updateScore("lang:common_list_test_scoring_name",40);
    }
}