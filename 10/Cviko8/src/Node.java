import java.io.Serializable;

public class Node implements Serializable {
    private static final long serialVersionUID = 918972645L;
    Integer value;
    Node left;
    Node right;

    public Node(Node left, Integer value, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(int n) {
        if (n < 2) {
            this.value = n;
            this.left = null;
            this.right = null;
        } else {
            this.left = new Node(n - 2);
            this.right = new Node(n - 1);
            this.value = this.left.value + this.right.value;
        }

    }
    public String toString() {
        Object var10000 = this.left != null ? this.left : ".";
        return "(" + var10000 + "," + (this.value != null ? this.value : "+") + "," + (this.right != null ? this.right : ".") + ")";
    }
}
