import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Streams {

    /**
     * vyrobí IntStream s n*n prvkami, ktorý keby ste nakrájali po n
     * prvkoch a dali pod seba, tak dostanete jednotkovú maticu
     * Príklad: jednotkovaMatica(int n) vyrobí IntStream
     * obsahujúci 1, 0, 0, 0,   0, 1, 0, 0,   0, 0, 1, 0,   0, 0, 0, 1.
     * Medzery sú v ňom umiestnené pre zvýraznenie riadkov jednotkovej
     * matice 4x4.
     * @param n >= 0
     */
    public static IntStream jednotkovaMatica(int n) {
        return IntStream.range(0, n)
                .flatMap(i -> IntStream.range(0, n)
                        .map(j -> i == j ? 1 : 0));
    }

    /**
     * prefiltruje čísla vstupného streamu vstup, a vo výslednom streame nechá len tie,
     * ktorých ciferný súčet je deliteľný 9
     */
    public static IntStream cifSum9(IntStream vstup) {
        return vstup.filter(x -> x % 9 == 0);
    }

    /**
     * prefiltruje čísla vstupného streamu vstup, a vo výslednom streame
     * nechá len tie, ktoré obsahujú všetky cifry 1..9, každú práve raz.
     * Na poradí cifier nezáleží.
     * Príklad: 987654321 zostane, 112345 vypadne, 1023456789 vypadne.
     */
    public static IntStream cifry1_9(IntStream vstup) {
        return vstup.filter(x -> {
                    var cifry = Integer.toString(x).chars().map(c -> c - '0').toArray();
                    return cifry.length == 9 && IntStream.range(1, 10)
                            .allMatch(i -> IntStream.of(cifry)
                                    .filter(c -> c == i).count() == 1);
                }
        );
    }

    /**
     * dokonalé číslo je číslo, ktorého súčet vlastných deliteľov sa rovná číslu samotnému
     */
    private static IntFunction<Integer> sumdel =
            x-> IntStream.rangeClosed(1, x / 2 +1)
                    .filter(i -> x % i == 0)
                    .sum();
    /**
     * dokonalé číslo je číslo, ktorého súčet vlastných deliteľov sa rovná číslu samotnému
     */
    public static IntPredicate dokonale =
            x-> sumdel.apply(x) == x;

    /**
     * spriatelené čísla: two numbers a and b are friends if the sum of proper divisors of a equals b
     * and vice versa. Example: 220 and 284 are friends (amicable numbers).
     * The method should filter from the input stream those numbers that have some amicable partner
     * (the partner need not be present in the input stream).
     */

    public static IntStream spriatelene(IntStream vstup){
        return vstup.filter(x -> { var y = sumdel.apply(x); return x != y && sumdel.apply(y) == x;});
    }

    public static void main(String[] args) {
        System.out.println("jednotkova matica:" +
                jednotkovaMatica(4).boxed().toList());
        System.out.println("cifsum:" + cifSum9(
                IntStream.of(12,18,19,20,21,27,35,49,654,34,5,6,25,89,76,90,3,22,1111,1000)).boxed().toList());
        System.out.println("cifry1_9:" +
                cifry1_9(IntStream.of(987654321,
                        112345,
                        1023456789,
                        1123456789,
                        876543219,
                        999999999,
                        192837465
                        )).boxed().toList()
        );
        System.out.println("dokonale:" +
                IntStream.rangeClosed(1, 10000)
                        .filter(dokonale)
                        .boxed().toList()
        );
        System.out.println("spriatelene:" +
                spriatelene(IntStream.range(0,30_000)).boxed().toList() );
    }
}
