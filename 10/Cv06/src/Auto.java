import java.util.*;

public class Auto implements Comparable<Auto> {
    private final String meno;
    private final int rocnik;

    @Override
    public boolean equals(Object obj) {
        return this == obj
                ||
                (obj instanceof Auto auto && Objects.equals(meno, auto.meno) && rocnik == auto.rocnik);
    }
    @Override
    public int hashCode() {
        return Objects.hash(meno, rocnik);
        //return 51*meno.hashCode()+17*rocnik;
        //return 17*rocnik;
        //return 1984;
    }

    @Override
    public int compareTo(Auto o) {
        return this.rocnik != o.rocnik ? Integer.compare(this.rocnik, o.rocnik) : this.meno.compareTo(o.meno);
//        if (o == null) {
//            throw new NullPointerException("other cannot be null");
//        }
//
//        int yearComparison = Integer.compare(rocnik, o.rocnik);
//        if (yearComparison != 0) {
//            return yearComparison;
//        }
//
//        return meno.compareTo(o.meno);
    }

    public Auto(String meno, int rocnik) {
        if(meno == null){ throw new NullPointerException(); }
        this.meno = meno;
        this.rocnik = rocnik;


    }

    public String getMeno() { return meno; }
    public int getRocnik() { return rocnik; }

    @Override
    public String toString() {
        return "(" + meno + ", rocnik=" + rocnik + ")";
    }

    // ...?



    public static void main(String[] args) {
        List<Auto> autaList = Arrays.asList(
                new Auto("Fabia", 1996),
                new Auto("Octavia", 1996),
                new Auto("Octavia", 2017),
                new Auto("Octavia", 2017),
                new Auto("Octavia", 2017),
                new Auto("Golf", 2003),
                new Auto("Golf", 2003),
                new Auto("Passat", 2015),
                new Auto("Tatrovka", 1984)
        );
        System.out.println("autaList.size() = " + autaList.size());  // 9
        System.out.println("autaList = " + autaList);

        Set<Auto> autaMnozina = new HashSet<>(autaList);
        System.out.println("\nautaMnozina.size() = " + autaMnozina.size());  // malo by ich byt 6
        System.out.println("autaMnozina = " + autaMnozina);

        SortedSet<Auto> autaUsporiadanaMnozina = new TreeSet<>(autaList);
        System.out.println("\nautaUsporiadanaMnozina.size() = " + autaUsporiadanaMnozina.size());  // malo by ich byt 6
        System.out.println("autaUsporiadanaMnozina = " + autaUsporiadanaMnozina);
    }
}
