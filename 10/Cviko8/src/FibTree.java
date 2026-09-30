import java.io.*;

public class FibTree {

    public static long sum(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))){
            final Node root = (Node) ois.readObject();
            return suma(root);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static long suma(Node t) {
        if (t == null) return 0;
        return t.value + suma(t.left) + suma(t.right);
    }
    public static Node next(String fileName) {
        int n = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))){
            Node root = (Node) ois.readObject();
            //while (root.left != null) { n++; root = root.left; }
//            while (root.right != null) { n++; root = root.right; }
//            System.out.println(n);
//            return new Node(n+2);
            return new Node(root.right,root.right.value+ root.value ,root);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(sum("du8.bin"));
        System.out.println(next("du8.bin"));
    }
}
