//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestOpravMa1 {
//    private static LISTTestScoring scoring = null;
//
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }

    Random rnd = new Random();

    @Test
    public void testUloha1() {
        var rnd = new Random();
        for(int pokus = 0; pokus < 1000; pokus++) {
            int[] s = randomIntArray();
            Arrays.sort(s);
            int[] r = Arrays.copyOf(s, s.length);
            for (int i = 0; i < s.length; i++) r[i] = -r[i];
            for (int j : s) {
                var actual = OpravMa.binarySearch(r, -j);
                var expected = Arrays.binarySearch(s, j);
                assertEquals("U1-3 binarySearch vracia zly index pre " + (-j), expected, actual);
            }
            for (int j : s) {
                var random = rnd.nextInt(1000);
                var actual = OpravMa.binarySearch(r, -random);
                var expected = Arrays.binarySearch(s, random);
                if (expected < 0) expected = -1;
                assertEquals("U1-3 binarySearch vracia zly index pre " + (-random), expected, actual);
            }
        }
        //scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }
    private int[] randomIntArray() {
        int len = 1+rnd.nextInt(100);
        int[]s = new int[len];
        for(int i = 0; i<len; i++) s[i] = rnd.nextInt(255);
        return s;
    }
}