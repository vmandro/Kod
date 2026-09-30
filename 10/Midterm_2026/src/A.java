public class A extends C implements Comparable<A> {  // doprogramuj
    String s;

    public A() {}
    public A(String s) {
        this.s = s;
    }
    @Override
    public int goo() {
        return 1;
    }
    public static String foo() {
        return new String("42");
    }

    @Override
    public int compareTo(A o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1984;
    }
}