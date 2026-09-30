//import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRegDate {

//    private static LISTTestScoring scoring = null;
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }



    static String[] ok = {
            "2022-02-24",
            "2020-02-29",
            "2000-02-29",
            "1932-02-29",
            "2020-12-12",
            "2100-12-31",
    };
    static String[] bad = {
            "2022-02-29",
            "2020-02-30",
            "2020-02-31",
            "2020-00-11",
            "2020-13-11",
            "1934-02-29",
            "2022-04-31",
    };


    static String[] ok1 = {
            "2400-02-24",
            "2400-02-29",
            "2320-02-29",
            "1584-02-29",
            "1600-02-29",
            "1604-02-29",
    };
    static String[] bad1 = {
            "1583-02-29",
            "1700-02-29",
            "1700-02-30",
            "1700-04-31",
    };

    @Test
    public void testRegDate_19_20() {
        String reg = RegDate.RegExpDate();
        System.out.println(reg);
        System.out.println(reg.length());
        if (reg.length() > 25*1024)
            fail("regularny vyraz je prilis dlhy");
        for(String s : ok)
            if (!s.matches(reg))
                fail("datum " + s + " je korektny a a vy ho neuznavate");

        for(String s : bad)
            if (s.matches(reg))
                fail("datum " + s + " nie je korektny a a vy ho uznavate");
//        scoring.updateScore("lang:common_list_test_scoring_name",50);
    }

    @Test
    public void testRegDate_Ine_sotorcia() {
        String reg = RegDate.RegExpDate();
        if (reg.length() > 25*1024)
            fail("regularny vyraz je prilis dlhy");
        for(String s : ok1)
            if (!s.matches(reg))
                fail("datum " + s + " je korektny a a vy ho neuznavate");

        for(String s : bad1)
            if (s.matches(reg))
                fail("datum " + s + " nie je korektny a a vy ho uznavate");
        //scoring.updateScore("lang:common_list_test_scoring_name",50);
    }

}