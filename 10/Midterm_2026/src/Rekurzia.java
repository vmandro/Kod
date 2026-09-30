import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

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
    private static HashMap<Long, Long> memo = new HashMap<>();

    /**
     * rekurzivna funkcia, ktora pouziva memoizaciu,
     * teda uklada vysledky pre uz vypocitane argumenty
     * @param n <= 1_000L
     */
    public static long fooMemoizacia(long n) {
        if (n <= 1) return n;
        else if (memo.containsKey(n)) return memo.get(n);
        else {
            long result = 2026L + fooMemoizacia(n - 1) - fooMemoizacia(n - 2);
            memo.put(n, result);
            return result;
        }
    }

    /** verzia funkcie foo, ktora nepouziva rekurziu,
     * pouziva zasobnik, alebo iteraciu, a memoizaciu
     * @param n <= 1_000_000L
     */
    public static long fooBezRekurzie(long n) {
        memo.put(0L,0L);
        memo.put(1L,1L);
        Stack<Long> stack = new Stack<>();
        stack.push(n);
        while (!stack.isEmpty()) {
            long curr = stack.peek();
            if (curr <= 1) stack.pop();
            else if (memo.get(curr - 1) == null) stack.push(curr - 1);
            else if (memo.get(curr - 2) == null) stack.push(curr - 2);
            else if (memo.get(curr - 1) != null && memo.get(curr - 2) != null) {
                memo.put(curr, 2026 + memo.get(curr - 1) - memo.get(curr - 2));
                stack.pop();
            }
        }
        return memo.get(n);
    }
    /** verzia funkcie foo, ktora nepouziva rekurziu,
     * pouziva zasobnik, alebo iteraciu, a memoizaciu
     * @param n <= 1_000_000L
     */
    public static long fooBezRekurzie1(long n) {
        if (n <= 1) return n;
        long prev2 = 0; // foo(0)
        long prev1 = 1; // foo(1)
        long current = 0;
        for (int i = 2; i <= n; i++) {
            current = 2026 + prev1 - prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static long fooBezLimitov(long n) {

        return new long[]{0,1,2027, 4052, 4051, 2025}[(int)(n % 6)];
    }

    public static void main(String[] args) {
        // nespocita, pretoze sa spolieha na rekurziu
        // System.out.println("foo(100) = " + foo(100));
        System.out.println("fooMemoizacia(1_000) = " + fooMemoizacia(1_000));
        System.out.println("fooBezRekurzie(1_000_000) = " + fooBezRekurzie(1_000_000));
        System.out.println("fooBezRekurzie1(1_000_000) = " + fooBezRekurzie1(1_000_000));
        System.out.println("fooBezLimitov(1_000_000_000_000L) = " + fooBezLimitov(1_000_000_000_000L));
        for(int i = 0; i <= 10; i++) {
            System.out.println("foo(" + i + ") = " + foo(i) +
                    ", fooMemoizacia(" + i + ") = " + fooMemoizacia(i) +
                    ", fooBezRekurzie(" + i + ") = " + fooBezRekurzie(i) +
                    ", fooBezLimitov(" + i + ") = " + fooBezLimitov(i)
            );
        }
        var rnd = new Random();
        for (int i = 0; i <= 20; i++) {
            var n = (long) (rnd.nextLong(1_000));
            var r = fooMemoizacia(n);
            System.out.println(
                    "assertEquals(\"fooMemoizacia(" + n + ") = \" + " + r + ", " + r + ", Rekurzia.fooMemoizacia("+n+"));"
            );
        }
        for (int i = 0; i <= 20; i++) {
            var n = (long) (rnd.nextLong(1_000_000));
            var r = fooBezRekurzie(n);
            System.out.println(
                    "assertEquals(\"fooBezRekurzie(" + n + ") = \" + " + r + ", " + r + ", Rekurzia.fooBezRekurzie("+n+"));"
            );
        }

        for (int i = 0; i <= 20; i++) {
            var n = (long) (rnd.nextLong(1_000_000_000_000L));
            var r = fooBezLimitov(n);
            System.out.println(
                    "assertEquals(\"fooBezLimitov(" + n + ") = \" + " + r + ", " + r + ", Rekurzia.fooBezLimitov("+n+"));"
            );
        }

    }


}
