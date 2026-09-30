//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class TestStreams {
//    private static LISTTestScoring scoring = null;
//
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }

    @Test
    public void jednotkovaMatica() {
        // n = 0 -> empty
        assertEquals(List.of(), Streams.jednotkovaMatica(0).boxed().toList());

        // n = 1 -> single 1
        assertEquals(List.of(1), Streams.jednotkovaMatica(1).boxed().toList());

        // n = 3 -> 3x3 identity flattened row-major
        assertEquals(List.of(
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        ), Streams.jednotkovaMatica(3).boxed().toList());

        // n = 4 -> 4x4 identity flattened
        assertEquals(List.of(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        ), Streams.jednotkovaMatica(4).boxed().toList());

       // scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void cifSum9() {
        // numbers divisible by 9 should be kept
        var input = IntStream.of(9, 18, 10, 27, 1, 0, 45, 100);
        var result = Streams.cifSum9(input).boxed().toList();
        assertEquals(List.of(9, 18, 27, 0, 45), result);

        // empty input
        assertEquals(List.of(), Streams.cifSum9(IntStream.empty()).boxed().toList());

       // scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void cifry1_9() {
        // keep numbers that contain digits 1..9 each exactly once
        var input = IntStream.of(987654321, 112345, 1023456789, 192837465, 123456789);
        var result = Streams.cifry1_9(input).boxed().toList();
        // expected: 987654321, 192837465, 123456789 (order preserved)
        assertEquals(List.of(987654321, 192837465, 123456789), result);

        // none match
        assertEquals(List.of(), Streams.cifry1_9(IntStream.of(111, 22, 333)).boxed().toList());

      //  scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void dokonale() {
        assertFalse(Streams.dokonale.test(0));
        assertTrue(Streams.dokonale.test(6));
        assertFalse(Streams.dokonale.test(11));
        assertFalse(Streams.dokonale.test(2));
        assertFalse(Streams.dokonale.test(3));
        assertFalse(Streams.dokonale.test(13));
        assertTrue(Streams.dokonale.test(28));
        assertFalse(Streams.dokonale.test(19));
        assertFalse(Streams.dokonale.test(55));
        assertFalse(Streams.dokonale.test(765));
        assertTrue(Streams.dokonale.test(496));
        assertTrue(Streams.dokonale.test(8128));
        assertFalse(Streams.dokonale.test(5421));
        assertFalse(Streams.dokonale.test(19231));
        assertFalse(Streams.dokonale.test(1231));
        assertTrue(Streams.dokonale.test(33550336));
        assertFalse(Streams.dokonale.test(1213231231));
        assertFalse(Streams.dokonale.test(23423424));
        assertFalse(Streams.dokonale.test(123131));
        assertFalse(Streams.dokonale.test(21312313));
        assertFalse(Streams.dokonale.test(45323452));
        assertFalse(Streams.dokonale.test(563456456));

       // scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }

    @Test
    public void spriatelene() {
        // amicable pairs: (220,284) and (1184,1210) are known examples
        var input = IntStream.of(220, 284, 1184, 1210, 10, 5);
        var result = Streams.spriatelene(input).boxed().toList();
        assertEquals(List.of(220, 284, 1184, 1210), result);

        // empty input
        assertEquals(List.of(), Streams.spriatelene(IntStream.empty()).boxed().toList());

        assertEquals(
                Arrays.asList(new Integer[]{
                        220, 284, 1184, 1210, 2620, 2924, 5020, 5564, 6232, 6368, 10744, 10856, 12285, 14595, 17296, 18416}
                ),
                Streams.spriatelene(IntStream.range(0,33_333)).boxed().toList());

       // scoring.updateScore("lang:common_list_test_scoring_name",   20.0D);
    }
}