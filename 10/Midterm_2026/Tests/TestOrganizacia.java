import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestOrganizacia {

    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    @Test
    public void testCeo() {
        {
            {
                var o = new Organizacia<>(Map.of(
                        "A", List.of("B", "C"),
                        "B", List.of("D"),
                        "C", List.of("E")
                ));

                assertEquals("A", o.ceo());
            }

            {
                var o = new Organizacia<>(Map.of(
                        "X", List.of("Z")
                ));

                assertEquals("X", o.ceo());
            }
            {
                var o = new Organizacia<>(Map.of(
                        1, List.of(2, 3),
                        2, List.of(4)
                ));

                assertEquals(Integer.valueOf(1), o.ceo());
            }
            {
                var o = new Organizacia<>(Map.of(
                        2, List.of(3, 4, 5),
                        4, List.of(6, 7, 8),
                        1, List.of(2),
                        7, List.of(9),
                        9, List.of(10, 11)

                        ));

                assertEquals(Integer.valueOf(1), o.ceo());
            }
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b","c"),
                            "b", List.of("d","e"),
                            "c", List.of("f","g"),
                            "d", List.of("h"),
                            "e", List.of("i","j"),
                            "f", List.of("k","l"),
                            "g", List.of("b","m")

                    ));
            assertEquals("a", o.ceo());
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b"),
                            "b", List.of("c"),
                            "c", List.of("d"),
                            "d", List.of("e"),
                            "e", List.of("f"),
                            "f", List.of("g"),
                            "g", List.of("h")

                    ));
            assertEquals("a", o.ceo());
        }


        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }
    @Test
    public void testPodriadeni() {
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E")
            ));

            assertEquals(Set.of("D", "E"), o.podriadeni());
        }

        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));

            assertEquals(Set.of("Z"), o.podriadeni());
        }
        {
            var o = new Organizacia<>(Map.of(
                    1, List.of(2, 3),
                    2, List.of(4)
            ));

            assertEquals(Set.of(3,4), o.podriadeni());
        }

        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)

            ));
            assertEquals(Set.of(3, 5, 6, 8, 10, 11), o.podriadeni());
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(4, 5),
                    3, List.of(6, 7),
                    1, List.of(2,3),
                    4, List.of(8,9),
                    5, List.of(10, 11)

            ));
            assertEquals(Set.of(6, 7, 8, 9, 10, 11), o.podriadeni());
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b","c"),
                            "b", List.of("d","e"),
                            "c", List.of("f","g"),
                            "d", List.of("h"),
                            "e", List.of("i","j"),
                            "f", List.of("k","l"),
                            "g", List.of("b","m")

                    ));
            assertEquals(Set.of("i", "j", "h", "k", "m", "l"), o.podriadeni());
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b"),
                            "b", List.of("c"),
                            "c", List.of("d"),
                            "d", List.of("e"),
                            "e", List.of("f"),
                            "f", List.of("g"),
                            "g", List.of("h")

                    ));
            assertEquals(Set.of("h"), o.podriadeni());
        }

        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }

    @Test
    public void infikonaveOddelenie() {
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E")
            ));

            assertEquals(1, o.infikonaveOddelenie("B"));
            assertEquals(2, o.infikonaveOddelenie("A"));
            assertEquals(0, o.infikonaveOddelenie("D"));
            assertEquals(0, o.infikonaveOddelenie("E"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikonaveOddelenie(1));
            assertEquals(4, o.infikonaveOddelenie(2));
            assertEquals(3, o.infikonaveOddelenie(4));
            assertEquals(2, o.infikonaveOddelenie(7));
            assertEquals(1, o.infikonaveOddelenie(9));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(0, o.infikonaveOddelenie("Z"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikonaveOddelenie("Solo"));
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b","c"),
                            "b", List.of("d","e"),
                            "c", List.of("f","g"),
                            "d", List.of("h"),
                            "e", List.of("i","j"),
                            "f", List.of("k","l"),
                            "g", List.of("m")

                    ));
            assertEquals(3, o.infikonaveOddelenie("a"));
            assertEquals(2, o.infikonaveOddelenie("b"));
            assertEquals(2, o.infikonaveOddelenie("c"));
            assertEquals(1, o.infikonaveOddelenie("d"));
            assertEquals(0, o.infikonaveOddelenie("i"));
            assertEquals(0, o.infikonaveOddelenie("m"));
        }

        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b"),
                            "b", List.of("c"),
                            "c", List.of("d"),
                            "d", List.of("e"),
                            "e", List.of("f"),
                            "f", List.of("g"),
                            "g", List.of("h")

                    ));
            assertEquals(7, o.infikonaveOddelenie("a"));
            assertEquals(6, o.infikonaveOddelenie("b"));
            assertEquals(5, o.infikonaveOddelenie("c"));
            assertEquals(4, o.infikonaveOddelenie("d"));
            assertEquals(3, o.infikonaveOddelenie("e"));
            assertEquals(2, o.infikonaveOddelenie("f"));
            assertEquals(1, o.infikonaveOddelenie("g"));
            assertEquals(0, o.infikonaveOddelenie("h"));
        }


        scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }

    @Test
    public void infikonavaOrganizacia() {
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E")
            ));

            assertEquals(2, o.infikonavaOrganizacia("B"));
            assertEquals(2, o.infikonavaOrganizacia("A"));
            assertEquals(3, o.infikonavaOrganizacia("D"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikonavaOrganizacia(1));
            assertEquals(4, o.infikonavaOrganizacia(2));
            assertEquals(3, o.infikonavaOrganizacia(4));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(1, o.infikonavaOrganizacia("Z"));
            assertEquals(1, o.infikonavaOrganizacia("X"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikonavaOrganizacia("Solo"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E"),
                    "E", List.of("F")
            ));
            assertEquals(3, o.infikonavaOrganizacia("B"));
            assertEquals(3, o.infikonavaOrganizacia("A"));
            assertEquals(4, o.infikonavaOrganizacia("D"));
            assertEquals(3, o.infikonavaOrganizacia("E"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikonavaOrganizacia(1));
            assertEquals(4, o.infikonavaOrganizacia(2));
            assertEquals(3, o.infikonavaOrganizacia(4));
            assertEquals(3, o.infikonavaOrganizacia(7));
            assertEquals(4, o.infikonavaOrganizacia(9));
        }

        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(1, o.infikonavaOrganizacia("Z"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikonavaOrganizacia("Solo"));
        }

        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b","c"),
                            "b", List.of("d","e"),
                            "c", List.of("f","g"),
                            "d", List.of("h"),
                            "e", List.of("i","j"),
                            "f", List.of("k","l"),
                            "g", List.of("m")

                    ));
            assertEquals(3, o.infikonavaOrganizacia("a"));
            assertEquals(3, o.infikonavaOrganizacia("b"));
            assertEquals(3, o.infikonavaOrganizacia("c"));
            assertEquals(4, o.infikonavaOrganizacia("d"));
            assertEquals(5, o.infikonavaOrganizacia("i"));
            assertEquals(5, o.infikonavaOrganizacia("m"));
        }
        {
            var o = new Organizacia<>(
                    Map.of(
                            "a", List.of("b"),
                            "b", List.of("c"),
                            "c", List.of("d"),
                            "d", List.of("e"),
                            "e", List.of("f"),
                            "f", List.of("g"),
                            "g", List.of("h")

                    ));
            assertEquals(7, o.infikonavaOrganizacia("a"));
            assertEquals(6, o.infikonavaOrganizacia("b"));
            assertEquals(5, o.infikonavaOrganizacia("c"));
            assertEquals(4, o.infikonavaOrganizacia("d"));
            assertEquals(4, o.infikonavaOrganizacia("e"));
            assertEquals(5, o.infikonavaOrganizacia("f"));
            assertEquals(6, o.infikonavaOrganizacia("g"));
            assertEquals(7, o.infikonavaOrganizacia("h"));
        }


        scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }
}