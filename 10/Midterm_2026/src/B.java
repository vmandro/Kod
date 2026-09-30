import java.util.Objects;

public class B extends C implements Comparable<B> { // doprogramuj
    String name;

    public B() {

    }
    public B(String s) {
        this.name = s;
    }

    @Override
    public int compareTo(B o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B magister = (B) o;
        return Objects.equals(name, magister.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @Override
    public int goo() {
        return 2;
    }
}