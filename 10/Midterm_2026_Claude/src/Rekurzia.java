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
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n <= 1) return n;
        if (n > 1_000L) throw new IllegalArgumentException("n too large for fooMemoizacia");
        int nn = (int) n;
        long[] memo = new long[nn + 1];
        memo[0] = 0L;
        memo[1] = 1L;
        for (int i = 2; i <= nn; i++) {
            memo[i] = 2026 + memo[i - 1] - memo[i - 2];
        }
        return memo[nn];
    }

    /** verzia funkcie foo, ktora nepouziva rekurziu,
     * pouziva zasobnik, alebo iteraciu, a memoizaciu
     * @param n <= 1_000_000L
     */
    public static long fooBezRekurzie(long n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        if (n <= 1) return n;
        if (n > 1_000_000L) throw new IllegalArgumentException("n too large for fooBezRekurzie");
        long prev2 = 0L; // foo(0)
        long prev1 = 1L; // foo(1)
        long cur = 0L;
        int nn = (int) n;
        for (int i = 2; i <= nn; i++) {
            cur = 2026 + prev1 - prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return cur;
    }
    /** verzia funkcie foo, ktora spocita foo(n) pre lubovolne
     * velke n
     */
    public static long fooBezLimitov(long n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        // The recurrence has period 6 around particular solution 2026.
        // Precompute first 6 values and index by n % 6.
        long[] vals = new long[]{0L, 1L, 2027L, 4052L, 4051L, 2025L};
        return vals[(int) (n % 6)];
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
