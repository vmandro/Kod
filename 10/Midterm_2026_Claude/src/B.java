public class B implements Comparable<B> {
    private final String weight;
    public B() { this.weight = null; }
    public B(String weight) { this.weight = weight; }

    public Object goo() {
        return new Val(2);
    }

    public Object foo() {
        return new Val(4);
    }

    @Override
    public int compareTo(B o) {
        if (this.weight == null && o.weight == null) return 0;
        if (this.weight == null) return -1;
        if (o.weight == null) return 1;
        return this.weight.compareTo(o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof B)) return false;
        B b = (B) o;
        if (weight == null) return b.weight == null;
        return weight.equals(b.weight);
    }

    @Override
    public int hashCode() {
        return weight == null ? 0 : weight.hashCode();
    }

    @Override
    public String toString() {
        return "B(" + weight + ")";
    }
}