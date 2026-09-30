import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import LISTTestScoring.LISTTestScoring;

import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;

public class TestDFA {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    @Test
    public void testPrazdny() {
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals("automat z orig.zadania zadania " +a.convert(), false, a.convert().prazdny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q2")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().prazdny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1"),
                            new Transit("q11", 'a', "q11"),
                            new Transit("q11", 'b', "q11")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().prazdny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1"),
                            new Transit("q11", 'a', "q11"),
                            new Transit("q11", 'b', "q11")
                    },
                    new String[]{"q11"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().prazdny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().prazdny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().prazdny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().prazdny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q0"),
                            new Transit("q1", 'b', "q0")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().prazdny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q0")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().prazdny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q0")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().prazdny());
        }

        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+1)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().prazdny());
        }


        scoring.updateScore("lang:common_list_test_scoring_name",34);
    }

    @Test
    public void testNekonecny() {
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q0"),
                            new Transit("q0", 'b', "q0"),
                    },
                    new String[]{"q0"});// koncove stavy
            assertEquals("automat z orig.zadania zadania " +a.convert(), true, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals("automat z orig.zadania zadania " +a.convert(), true, a.convert().nekonecny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q2")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }


        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q1"),
                            new Transit("q2", 'b', "q1")
                    },
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q1"),
                            new Transit("q2", 'b', "q1")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }


        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q1"),
                            new Transit("q2", 'b', "q1")
                    },
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }




        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q3"),
                            new Transit("q2", 'a', "q1"),
                            new Transit("q2", 'b', "q1"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3"),

                    },
                    new String[]{"q3"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q3"),
                            new Transit("q2", 'a', "q1"),
                            new Transit("q2", 'b', "q1"),
                            new Transit("q3", 'a', "q4"),
                            new Transit("q3", 'b', "q4"),
                            new Transit("q4", 'a', "q4"),
                            new Transit("q4", 'b', "q4"),
                    },
                    new String[]{"q3"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q2")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q3"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }
        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+1)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                int i1 = (i==0)?(MAX-1):i-1;
                delta[2*i] = new Transit("q"+i, 'a', "q"+i1);
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                int i1 = (i==0)?(MAX-1):i-1;
                delta[2*i] = new Transit("q"+i, 'a', "q"+i1);
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q9"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }

        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a = new Automata(
                    "q1",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), false, a.convert().nekonecny());
        }
        {
            final int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a = new Automata(
                    "q1",			// pociatocny stav
                    delta,
                    new String[]{"q1","q0"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            final int MAX = 101;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        {
            final int MAX = 101;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q1"});// koncove stavy
            assertEquals(""+a.convert(), true, a.convert().nekonecny());
        }
        scoring.updateScore("lang:common_list_test_scoring_name",33);
    }

    @Test
    public void testLanguage() {
            Automata a1 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1")
                    },
                    new String[]{"q1"});// koncove stavy
            Automata a2 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q0"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q1"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q2")
                    },
                    new String[]{"q2"});// koncove stavy
            Automata a3 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q2")
                    },
                    new String[]{"q2"});// koncove stavy
            Automata a4 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q1"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            Automata a5 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q2"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            Automata a6 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            Automata a7 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q0"});// koncove stavy
            Automata a8 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q3"});// koncove stavy
            Automata a9 = new Automata(
                    "q0",			// pociatocny stav
                    new Transit[]{	// delta
                            new Transit("q0", 'a', "q1"),
                            new Transit("q0", 'b', "q1"),
                            new Transit("q1", 'a', "q2"),
                            new Transit("q1", 'b', "q2"),
                            new Transit("q2", 'a', "q3"),
                            new Transit("q2", 'b', "q3"),
                            new Transit("q3", 'a', "q3"),
                            new Transit("q3", 'b', "q3")
                    },
                    new String[]{"q2"});// koncove stavy
            int MAX = 10;
            Transit[] delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+1)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a10 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy

            MAX = 10;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                int i1 = (i==0)?(MAX-1):i-1;
                delta[2*i] = new Transit("q"+i, 'a', "q"+i1);
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a11 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy

            MAX = 10;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                int i1 = (i==0)?(MAX-1):i-1;
                delta[2*i] = new Transit("q"+i, 'a', "q"+i1);
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+i);
            }
            Automata a12 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q9"});// koncove stavy


            MAX = 10;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a13 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy

            MAX = 10;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a15 = new Automata(
                    "q1",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy

            MAX = 10;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a16 = new Automata(
                    "q1",			// pociatocny stav
                    delta,
                    new String[]{"q1","q0"});// koncove stavy


            MAX = 101;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a17 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q0"});// koncove stavy

            MAX = 101;
            delta = new Transit[2*MAX];
            for (int i = 0; i<MAX; i++) {
                delta[2*i] = new Transit("q"+i, 'a', "q"+((i+2)%MAX));
                delta[2*i+1] = new Transit("q"+i, 'b', "q"+((i+2)%MAX));
            }
            Automata a18 = new Automata(
                    "q0",			// pociatocny stav
                    delta,
                    new String[]{"q1"});// koncove stavy


            for(Automata a : Arrays.asList(new Automata[]{a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a15,a16,a17,a18})) {
                for(var len = 0; len < 1; len++) {
                    var dfa = a.convert();
                    var res1 = dfa.language(len);
                    var res2 =  mylanguage(dfa, len);
                    //System.out.println(res2.size());
                    if (res1.size() != res2.size())
                        assertEquals("nespravy pocet akceptujucich slov pre automat " + dfa +  " dlzky najviac " + len,
                                res2, res1
                                );
                    var sres1 = res1.stream().map(x -> doString(x)).collect(Collectors.toList());
                    var sres2 = res2.stream().map(x -> doString(x)).collect(Collectors.toList());
                    for(String r : sres1)
                        if (!sres2.contains(r))
                            fail("nespravy pocet akceptujucich slov pre automat " + dfa +  " dlzky najviac " + len);
                    for(String r : sres2)
                        if (!sres1.contains(r))
                            fail("nespravy pocet akceptujucich slov pre automat " + dfa +  " dlzky najviac " + len);
                }
            }

        scoring.updateScore("lang:common_list_test_scoring_name",33);
    }

    private static String doString(List<Character> rs) {
        var sb = new StringBuffer();
        for (Character r : rs) {
            sb.append(r);
        }
        return sb.toString();
    }

    class Config<S,A> {
        S state;
        List<A> word;

        public Config(S state, List<A> word) {
            this.state = state;
            this.word = word;
        }
    }

    public <S,A> Set<List<A>> mylanguage(DFA<S,A>dfa, int len) {
        ArrayList<Config<S,A>> queue = new ArrayList<>();
        queue.add(new Config<S,A>(dfa.getInitState(), List.of()));
        Set<List<A>> res = new HashSet<>();
        if (dfa.getFinalStates().contains(dfa.getInitState()))
            res.add(List.of());
        while (!queue.isEmpty()) {
            var fst = queue.get(0);
            queue.remove(0);
            for (A ch : dfa.getAlphabet()) {
                var nword = new ArrayList<>(fst.word);
                nword.add(ch);
                if (nword.size() <= len) {
                    var ns = dfa.getDelta().get(fst.state).get(ch);
                    if (dfa.getFinalStates().contains(ns))
                        res.add(nword);
                    queue.add(new Config<S,A>(ns, nword));
                }
            }
        }
        return res;
    }
}

class Transit {
    private String fromState;
    private Character symbol;
    private String toState;

    public Transit(String fromState, Character symbol, String toState) {
        super();
        this.fromState = fromState;
        this.symbol = symbol;
        this.toState = toState;
    }
    public String getFromState() {
        return fromState;
    }
    public void setFromState(String fromState) {
        this.fromState = fromState;
    }
    public Character getSymbol() {
        return symbol;
    }
    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }
    public String getToState() {
        return toState;
    }
    public void setToState(String toState) {
        this.toState = toState;
    }
}

class Automata {
    private String initState;
    private Transit[] delta;
    private String[] finalStates;

    public Automata(String initState, Transit[] delta, String[] finalStates) {
        super();
        this.initState = initState;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    public DFA<String, Character> convert() {
        Set<String> states = new HashSet<>();
        Set<Character> alphabet = new HashSet<>();
        Map<String, Map<Character, String>>ndelta = new HashMap<>();
        for(Transit t : delta) {
            states.add(t.getFromState());
            states.add(t.getToState());
            alphabet.add(t.getSymbol());
            if (ndelta.get(t.getFromState()) == null) {
                ndelta.put(t.getFromState(), new HashMap<>());
            }
            if (ndelta.get(t.getFromState()).get(t.getSymbol()) == null) {
                ndelta.get(t.getFromState()).put(t.getSymbol(), t.getToState());
            }
        }
        return new DFA<String,Character>(states, initState, alphabet,
                new HashSet<>(Arrays.asList(finalStates)), ndelta);
    }
    /**
     * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) symbolov
     * 			 nachadzajucich sa v delta funkcii
     */
    public Character[] alphabet() {
        return null; // toto doprogramujte
    }
    /**
     * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii
     */
    public String[] states() {
        return null; // toto doprogramujte
    }
    /**
     * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
     * - pociatocny stav a vsetky koncove patria do mnoziny states()
     * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet()
     */
    public boolean isCorrectFA() {
        return false; // toto doprogramujte
    }
    /**
     * predpokladajte, ze objekt splna podmienku isCorrectFA()
     * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
     * @return - true, ak automat slovo akceptuje, inak false
     */
    public boolean accepts(String word) {
        return false; // toto doprogramujte
    }
    /**
     * @return konecny automat akceptujuci binarne slova z 0 a 1 predstavujuce binarny zapis (v dvojkovej sustave) prvocisla < 256
     */
    public static Automata prvocisla256() {
        return new Automata(null,  null,  null); // toto doprogramujte, ak riesite premiu
    }

//    public static void main(String[] args) {
//        Automata a = new Automata(
//                "q0",			// pociatocny stav
//                new Transit[]{	// delta
//                        new Transit("q0", 'a', "q1"),
//                        new Transit("q0", 'b', "q0"),
//                        new Transit("q1", 'a', "q1"),
//                        new Transit("q1", 'b', "q1")
//                },
//                new String[]{"q1"});// koncove stavy
//        System.out.println(Arrays.asList(a.alphabet()));
//        System.out.println(Arrays.asList(a.states()));
//        System.out.println(a.isCorrectFA());
//        System.out.println(a.accepts("a"));
//    }
}
