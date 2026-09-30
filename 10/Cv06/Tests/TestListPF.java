import LISTTestScoring.LISTTestScoring;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.TreeSet;

public class TestListPF {
    private static LISTTestScoring scoring = null;

    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }
    // staci, ak zbehne jeden test
//    @Test
//    public void testArray() {
//        test3in1(new ArrayPF<String>(1000));
//        scoring.updateScore("lang:common_list_test_scoring_name",100);
//    }
//    @Test
//    public void testArrayDvojica() {
//        test3in1(new ArrayPFDvojica<String>(1000));
//        scoring.updateScore("lang:common_list_test_scoring_name",100);
//    }
    @Test
    public void testList() {
        test3in1(new ListPF<String>());
        scoring.updateScore("lang:common_list_test_scoring_name",50);
    }
//    @Test
//    public void testPriorityQueue() {
//        test3in1(new PrioritnyFront<String>());
//        scoring.updateScore("lang:common_list_test_scoring_name",100);
//    }
    public <E> void  test3in1(FrontInterface<String> pf) {
        testQuaziRandom(pf);
        testIncremental(pf);
        testDecremental(pf);
    }
    //---------------------------------------
    public <E> void  testQuaziRandom(FrontInterface<String> pf) {
        //ArrayPF<String> pf = new ArrayPF<>(1000);
        //ArrayPFDvojica<String> pf = new ArrayPFDvojica<>(1000);
        //ListPF<String> pf = new ListPF<>();
        //PrioritnyFront<String> pf = new PrioritnyFront<>();
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i < 100; i++) {
            int prio = 17*i % 149;
            ts.add(prio);
            pf.enqueue(""+prio, prio);
            Assert.assertFalse("non empty", pf.isEmpty());
        }
        //System.out.println(ts);
        //System.out.println(pf);
        while (!pf.isEmpty()) {
            String s = pf.dequeue();
            int min = ts.first();
            //System.out.println(s + ", " + min);
            ts.remove(min);
            Assert.assertEquals("dequeue " + s, ""+min, s);
        }
        Assert.assertTrue("empty", pf.isEmpty());
    }
    public <E> void  testIncremental(FrontInterface<String> pf) {
        for(int i = 0; i < 100; i++) {
            int prio = i;
            pf.enqueue(""+prio, prio);
            Assert.assertFalse("non empty", pf.isEmpty());
        }
        //System.out.println(ts);
        //System.out.println(pf);
        int i = 0;
        while (!pf.isEmpty()) {
            String s = pf.dequeue();
            int min = i;
            //System.out.println(s + ", " + min);
            Assert.assertEquals("dequeue " + s, ""+min, s);
            i++;
        }
        Assert.assertTrue("empty", pf.isEmpty());
    }
    public <E> void  testDecremental(FrontInterface<String> pf) {
        for(int i = 0; i < 100; i++) {
            int prio = 100-i;
            pf.enqueue(""+prio, prio);
            Assert.assertFalse("non empty", pf.isEmpty());
        }
        //System.out.println(ts);
        //System.out.println(pf);
        int i = 0;
        while (!pf.isEmpty()) {
            String s = pf.dequeue();
            int min = i+1;
            //System.out.println(s + ", " + min);
            Assert.assertEquals("dequeue " + s, ""+min, s);
            i++;
        }
        Assert.assertTrue("empty", pf.isEmpty());
    }
}