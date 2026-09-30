public class Rekurzia {

    /**
     * rekurzivna funkcia zo zadania, ktora
     * pre n > 1 vracia 2026 + foo(n-1) - foo(n-2)
     * inak vrati n
     */
    public static long foo(long n) {
        if (n <= 1) return n;
        return 2026 + foo(n-1) - foo(n-2);
    }

    /**
     * rekurzivna funkcia, ktora pouziva memoizaciu,
     * teda uklada vysledky pre uz vypocitane argumenty
     * @param n <= 1_000L
     */
    public static long fooMemoizacia(long n) {
        return -1; // doprogramuj tuto funkciu,
        // pouzi memoizaciu, aby si mohol spocitat
        // fooMemoizacia(az po 1000)
    }

    /** verzia funkcie foo, ktora nepouziva rekurziu,
     * pouziva zasobnik, alebo iteraciu, a memoizaciu
     * @param n <= 1_000_000L
     */
    public static long fooBezRekurzie(long n) {
        return -1; // doprogramuj tuto funkciu,
        // pouzi zasobnik, alebo iteraciu, a memoizaciu,
        // aby si mohol spocitat fooBezRekurzie(az po 1_000_000)
    }
    /** verzia funkcie foo, ktora spocita foo(n) pre lubovolne
     * velke n
     */
    public static long fooBezLimitov(long n) {
            return  -1; // doprogramuj
    }

    public static void main(String[] args) {
        // nespocita, pretoze sa spolieha na rekurziu
        // System.out.println("foo(100) = " + foo(100));
        System.out.println("fooMemoizacia(1_000) = " + fooMemoizacia(1_000));
        System.out.println("fooBezRekurzie(1_000_000) = " + fooBezRekurzie(1_000_000));
        System.out.println("fooBezLimitov(1_000_000_000_000L) = " + fooBezLimitov(1_000_000_000_000L));
        for(int i = 0; i <= 10; i++) {
            System.out.println("foo(" + i + ") = " + foo(i) +
                    ", fooMemoizacia(" + i + ") = " + fooMemoizacia(i) +
                    ", fooBezRekurzie(" + i + ") = " + fooBezRekurzie(i) +
                    ", fooBezLimitov(" + i + ") = " + fooBezLimitov(i)
            );
        }
    }
}
