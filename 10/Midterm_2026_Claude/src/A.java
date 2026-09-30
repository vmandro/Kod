public class A implements Comparable<A> {
    private final String weight;
    public A() { this.weight = null; }
    public A(String weight) { this.weight = weight; }

    public static Object foo() {
        return new Val(10);
    }

    public Object goo() {
        return new Val(1);
    }

    @Override
    public int compareTo(A o) {
        return 0; // all A instances considered equal for TreeSet in tests
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof A; // all A are equal
    }

    @Override
    public int hashCode() {
        return 42;
    }

    @Override
    public String toString() {
        return "A(" + weight + ")";
    }
}