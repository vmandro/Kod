//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestOrganizacia {

//    private static LISTTestScoring scoring = null;
//
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }
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


//        scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
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

  //      scoring.updateScore("lang:common_list_test_scoring_name",   17.0D);
    }

    @Test
    public void testInfikonaveOddelenie() {
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E")
            ));
            
            assertEquals(1, o.infikovaneOddelenie("B"));
            assertEquals(2, o.infikovaneOddelenie("A"));
            assertEquals(0, o.infikovaneOddelenie("D"));
            assertEquals(0, o.infikovaneOddelenie("E"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikovaneOddelenie(1));
            assertEquals(4, o.infikovaneOddelenie(2));
            assertEquals(3, o.infikovaneOddelenie(4));
            assertEquals(2, o.infikovaneOddelenie(7));
            assertEquals(1, o.infikovaneOddelenie(9));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(0, o.infikovaneOddelenie("Z"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikovaneOddelenie("Solo"));
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
            assertEquals(3, o.infikovaneOddelenie("a"));
            assertEquals(2, o.infikovaneOddelenie("b"));
            assertEquals(2, o.infikovaneOddelenie("c"));
            assertEquals(1, o.infikovaneOddelenie("d"));
            assertEquals(0, o.infikovaneOddelenie("i"));
            assertEquals(0, o.infikovaneOddelenie("m"));
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
            assertEquals(7, o.infikovaneOddelenie("a"));
            assertEquals(6, o.infikovaneOddelenie("b"));
            assertEquals(5, o.infikovaneOddelenie("c"));
            assertEquals(4, o.infikovaneOddelenie("d"));
            assertEquals(3, o.infikovaneOddelenie("e"));
            assertEquals(2, o.infikovaneOddelenie("f"));
            assertEquals(1, o.infikovaneOddelenie("g"));
            assertEquals(0, o.infikovaneOddelenie("h"));
        }


    //    scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }

    @Test
    public void infikovanaOrganizacia() {
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E")
            ));

            assertEquals(2, o.infikovanaOrganizacia("B"));
            assertEquals(2, o.infikovanaOrganizacia("A"));
            assertEquals(3, o.infikovanaOrganizacia("D"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikovanaOrganizacia(1));
            assertEquals(4, o.infikovanaOrganizacia(2));
            assertEquals(3, o.infikovanaOrganizacia(4));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(1, o.infikovanaOrganizacia("Z"));
            assertEquals(1, o.infikovanaOrganizacia("X"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikovanaOrganizacia("Solo"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "A", List.of("B", "C"),
                    "B", List.of("D"),
                    "C", List.of("E"),
                    "E", List.of("F")
            ));
            assertEquals(3, o.infikovanaOrganizacia("B"));
            assertEquals(3, o.infikovanaOrganizacia("A"));
            assertEquals(4, o.infikovanaOrganizacia("D"));
            assertEquals(3, o.infikovanaOrganizacia("E"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    2, List.of(3, 4, 5),
                    4, List.of(6, 7, 8),
                    1, List.of(2),
                    7, List.of(9),
                    9, List.of(10, 11)
            ));
            assertEquals(5, o.infikovanaOrganizacia(1));
            assertEquals(4, o.infikovanaOrganizacia(2));
            assertEquals(3, o.infikovanaOrganizacia(4));
            assertEquals(3, o.infikovanaOrganizacia(7));
            assertEquals(4, o.infikovanaOrganizacia(9));
        }

        {
            var o = new Organizacia<>(Map.of(
                    "X", List.of("Z")
            ));
            assertEquals(1, o.infikovanaOrganizacia("Z"));
        }
        {
            var o = new Organizacia<>(Map.of(
                    "Solo", List.of()
            ));
            assertEquals(0, o.infikovanaOrganizacia("Solo"));
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
            assertEquals(3, o.infikovanaOrganizacia("a"));
            assertEquals(3, o.infikovanaOrganizacia("b"));
            assertEquals(3, o.infikovanaOrganizacia("c"));
            assertEquals(4, o.infikovanaOrganizacia("d"));
            assertEquals(5, o.infikovanaOrganizacia("i"));
            assertEquals(5, o.infikovanaOrganizacia("m"));
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
            assertEquals(7, o.infikovanaOrganizacia("a"));
            assertEquals(6, o.infikovanaOrganizacia("b"));
            assertEquals(5, o.infikovanaOrganizacia("c"));
            assertEquals(4, o.infikovanaOrganizacia("d"));
            assertEquals(4, o.infikovanaOrganizacia("e"));
            assertEquals(5, o.infikovanaOrganizacia("f"));
            assertEquals(6, o.infikovanaOrganizacia("g"));
            assertEquals(7, o.infikovanaOrganizacia("h"));
        }


      //  scoring.updateScore("lang:common_list_test_scoring_name",   33.0D);
    }
}