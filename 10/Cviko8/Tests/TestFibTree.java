import static org.junit.Assert.*;

//import LISTTestScoring.LISTTestScoring;
import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestFibTree {
//    private static LISTTestScoring scoring = null;
//    @BeforeClass
//    public static void initScoring() {
//        scoring = new LISTTestScoring();
//        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
//    }

    @Test
    public void testSum() {
        for (int i = 2; i < 15; i++) {
            Node t = new Node(i);
            long s = sumr(t);
            save("test.bin", t);
            assertEquals("sum " + t, s, FibTree.sum("test.bin"));
        }
//        scoring.updateScore("lang:common_list_test_scoring_name",  40.0D);
    }

    @Test
    public void testNext() {
        for (int i = 2; i < 15; i++) {
            Node t = new Node(i);
            Node t1 = new Node(i+1);
            long s = sumr(t);
            long s1 = sumr(t1);
            save("test.bin", t);
            assertEquals("next " + t, s1, sumr(FibTree.next("test.bin")));
        }
  //      scoring.updateScore("lang:common_list_test_scoring_name",  60.0D);
    }

    public static <E> void save(String fileName, E t) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(t);
        } catch (IOException e) {
            System.err.println("nieco zle nastalo pri otvarani suboru " + fileName +
                    ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static long sumr(Node t) {
        if (t == null) return 0;
        return t.value +
                ((t.left!=null)?sumr(t.left):0) +
                ((t.right!=null)?sumr(t.right):0);
    }
//    public static int isFibTree(Node t) {
//        if (t == null) return -1;
//        if (t.value == 0 && t.left == null && t.right == null)
//            return 0;
//        if (t.value == 1 && t.left == null && t.right == null)
//            return 1;
//        if (t.left != null && t.right != null) {
//            int l = isFibTree(t.left);
//            int r = isFibTree(t.right);
//            return (l >= 0 && r >= 0 && l+1 == r)?r+1:-1;
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        long t = sum("du8.bin");
//        System.out.println(t);
//        System.out.println(next("du8.bin"));
//    }
}
