import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TestMena {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testNames1() {
        {
            List<String> first = List.of("Jan", "Peter");
            List<String> last = List.of("Novak", "Kovac");

            List<String> result = Mena.names(first, last);

            List<String> expected = List.of(
                    "Jan Kovac",
                    "Peter Kovac",
                    "Jan Novak",
                    "Peter Novak"
            );

            assertEquals(expected, result);
        }
        {
            List<String> first = List.of("Peter", "Jan");
            List<String> last = List.of("Novak", "Kovac");

            List<String> result = Mena.names(first, last);

            List<String> expected = List.of(
                    "Jan Kovac",
                    "Peter Kovac",
                    "Jan Novak",
                    "Peter Novak"
            );

            assertEquals(expected, result);
        }
        {
            List<String> first = List.of();
            List<String> last = List.of("Novak");

            List<String> result = Mena.names(first, last);

            assertEquals(List.of(), result);
        }
        {
            List<String> first = List.of("Jan");
            List<String> last = List.of();

            List<String> result = Mena.names(first, last);

            assertEquals(List.of(), result);
        }
        {
            List<String> first = List.of("Jan");
            List<String> last = List.of("Novak");

            List<String> result = Mena.names(first, last);

            assertEquals(List.of("Jan Novak"), result);
        }
        {
            List<String> first = List.of("Zoe", "Anna", "Mike", "Bob");
            List<String> last = List.of("Xavier", "Clark", "Brown");

            List<String> result = Mena.names(first, last);

            // expected size = Cartesian product
            assertEquals(first.size() * last.size(), result.size());

            // verify full correctness (order + content)
            List<String> expected = sortManually(first, last);
            assertEquals(expected, result);
        }

        {
            List<String> first = List.of("Jan", "Jan");
            List<String> last = List.of("Novak");

            List<String> result = Mena.names(first, last);

            // duplicates should be preserved
            assertEquals(List.of("Jan Novak", "Jan Novak"), result);
        }
        TestMena.scoring.updateScore("lang:common_list_test_scoring_name",2*20);
    }


// -------- Tests for second method --------

    @Test
    public void testNames2() {
        {
            List<List<String>> input = List.of(
                    List.of("Jan", "Peter"),
                    List.of("Novak", "Kovac")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
                    "Jan Kovac",
                    "Peter Kovac",
                    "Jan Novak",
                    "Peter Novak"
            );

            assertEquals(expected, result);
        }
        {
            List<List<String>> input = List.of(
                    List.of("Jan"),
                    List.of("Novak"),
                    List.of("Sr")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
                    "Jan Novak Sr"
            );

            assertEquals(expected, result);
        }
        {
            List<List<String>> input = List.of(
                    List.of("Jan"),
                    List.of(),
                    List.of("Sr")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(

            );

            assertEquals(expected, result);
        }
        {
            List<List<String>> input = List.of(
                    List.of("A", "B"),
                    List.of("1", "2"),
                    List.of("X"),
                    List.of("K", "L")
            );

            List<String> result = Mena.names(input);

            // expected size = product of sizes
            int expectedSize = 2 * 2 * 1 * 2;
            assertEquals(expectedSize, result.size());
        }
        {
            List<List<String>> input = List.of(
                    List.of("A", "B", "C"),
                    List.of("1", "2"),
                    List.of("X", "Y")
            );

            List<String> result = Mena.names(input);

            // 3 * 2 * 2 = 12
            assertEquals(12, result.size());

            // ensure all combinations are unique
            Set<String> unique = new HashSet<>(result);
            assertEquals(result.size(), unique.size());
        }

        {
            List<List<String>> input = List.of(
                    List.of("A", "A"),
                    List.of("B"),
                    List.of("C", "C")
            );

            List<String> result = Mena.names(input);

            // 2 * 1 * 2 = 4 combinations (with duplicates)
            assertEquals(4, result.size());

            // duplicates must exist
            Map<String, Long> freq = result.stream()
                    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

            assertTrue(freq.values().stream().anyMatch(c -> c > 1));
        }
        {
            List<List<String>> input = List.of(
                    List.of("A", "B"),
                    List.of("1"),
                    List.of(), // kills combinations
                    List.of("X")
            );

            List<String> result = Mena.names(input);

            assertTrue(result.isEmpty());
        }
        {
            List<List<String>> input = List.of(
                    List.of("Jan"),
                    List.of("Novak", "Kovac"),
                    List.of("Sr")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
                    "Jan Kovac Sr",
                    "Jan Novak Sr"
            );

            assertEquals(expected, result);
        }

        {
            List<List<String>> input = List.of(
                    List.of("Jan"),
                    List.of("Novak"),
                    List.of("Sr", "Jr")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
                    "Jan Novak Jr",
                    "Jan Novak Sr"
            );

            assertEquals(expected, result);
        }

        {
            List<List<String>> input = List.of(
                    List.of(),
                    List.of(),
                    List.of()
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
            );

            assertEquals(expected, result);
        }

        {
            List<List<String>> input = List.of(
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
            );

            assertEquals(expected, result);
        }

        {
            List<List<String>> input = List.of(
                    List.of("Jan", "Peter"),
                    List.of("Novak"),
                    List.of("Sr", "Jr")
            );

            List<String> result = Mena.names(input);

            List<String> expected = List.of(
                    "Jan Novak Jr",
                    "Peter Novak Jr",
                    "Jan Novak Sr",
                    "Peter Novak Sr"
            );

            assertEquals(expected, result);
        }
        {
            List<List<String>> input = List.of(
                    List.of("Peter", "Jan")
            );

            List<String> result = Mena.names(input);

            assertEquals(List.of("Jan", "Peter"), result);
        }
        {
            List<List<String>> input = List.of(
                    List.of("Jan"),
                    List.of()
            );

            List<String> result = Mena.names(input);

            assertEquals(List.of(), result);
        }
        TestMena.scoring.updateScore("lang:common_list_test_scoring_name",3*20);
    }

    private List<String> sortManually(List<String> first, List<String> last) {
        List<String> f = new ArrayList<>(first);
        List<String> l = new ArrayList<>(last);
        Collections.sort(f);
        Collections.sort(l);

        List<String> result = new ArrayList<>();
        for (String ln : l) {
            for (String fn : f) {
                result.add(fn + " " + ln);
            }
        }
        return result;
    }
}
