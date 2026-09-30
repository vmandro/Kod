import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Streams {

    /**
     * produce an IntStream with n*n elements which arranged into n columns
     * forms the identity matrix (1 on diagonal, 0 elsewhere).
     * @param n >= 0
     */
    public static IntStream jednotkovaMatica(int n) {
        if (n <= 0) return IntStream.empty();
        // iterate rows and for each row produce n values where 1 at diagonal
        return IntStream.range(0, n).flatMap(row ->
                IntStream.range(0, n).map(col -> row == col ? 1 : 0)
        );
    }

    /**
     * filter numbers whose digit sum is divisible by 9
     */
    public static IntStream cifSum9(IntStream vstup) {
        return vstup.filter(x -> x % 9 == 0);
    }

    /**
     * filter numbers that contain all digits 1..9 exactly once (no zeros, no repeats)
     */
    public static IntStream cifry1_9(IntStream vstup) {
        return vstup.filter(x -> {
            if (x <= 0) return false;
            int cnt = 0;
            int tmp = x;
            boolean[] seen = new boolean[10];
            while (tmp > 0) {
                int d = tmp % 10;
                if (d == 0) return false;
                if (seen[d]) return false;
                seen[d] = true;
                cnt++;
                tmp /= 10;
            }
            if (cnt != 9) return false;
            for (int d = 1; d <= 9; d++) if (!seen[d]) return false;
            return true;
        });
    }

    /**
     * perfect number: sum of proper divisors equals the number itself
     */
    public static int sumaVlastnychDelitelov(int n) {
        return IntStream.rangeClosed(1, n / 2)
                .filter(i -> n % i == 0)
                .sum();
    }

    public static IntPredicate dokonale = n -> n > 1 && sumaVlastnychDelitelov(n) == n;


    private static int sumProperDivisors(int n) {
        if (n <= 1) return 0;
        int sum = 1;
        int r = (int) Math.sqrt(n);
        for (int d = 2; d <= r; d++) {
            if (n % d == 0) {
                sum += d;
                int o = n / d;
                if (o != d) sum += o;
            }
        }
        return sum;
    }

    /**
     * amicable numbers: two numbers a and b are friends if the sum of proper divisors of a equals b
     * and vice versa. The method filters numbers that have some amicable partner.
     */

    public static IntStream spriatelene(IntStream vstup){
        return vstup.filter(x -> {
            int b = sumProperDivisors(x);
            return b != x && b > 0 && sumProperDivisors(b) == x;
        });
    }

    public static void main(String[] args) {
        System.out.println("jednotkova matica:" +
                jednotkovaMatica(4).boxed().collect(Collectors.toList()));
        System.out.println("cifsum:" + cifSum9(
                IntStream.of(12,18,19,20,21,27,35,49,654,34,5,6,25,89,76,90,3,22,1111,1000)).boxed().collect(Collectors.toList()));
        System.out.println("cifry1_9:" +
                cifry1_9(IntStream.of(987654321,
                        112345,
                        1023456789,
                        1123456789,
                        876543219,
                        999999999,
                        192837465
                        )).boxed().collect(Collectors.toList())
        );
        System.out.println("dokonale:" +
                IntStream.rangeClosed(1, 10000)
                        .filter(dokonale)
                        .boxed().collect(Collectors.toList())
        );
        System.out.println("spriatelene:" +
                spriatelene(IntStream.range(0,30_000)).boxed().collect(Collectors.toList()) );
    }
}
