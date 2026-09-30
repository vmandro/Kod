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
        return IntStream.of(); // doprogramuj
    }

    /**
     * prefiltruje čísla vstupného streamu vstup, a vo výslednom streame nechá len tie,
     * ktorých ciferný súčet je deliteľný 9
     */
    public static IntStream cifSum9(IntStream vstup) {
        return IntStream.of(); // doprogramuj
    }

    /**
     * prefiltruje čísla vstupného streamu vstup, a vo výslednom streame
     * nechá len tie, ktoré obsahujú všetky cifry 1..9, každú práve raz.
     * Na poradí cifier nezáleží.
     * Príklad: 987654321 zostane, 112345 vypadne, 1023456789 vypadne.
     */
    public static IntStream cifry1_9(IntStream vstup) {
        return IntStream.of();
    }

    /**
     * dokonalé číslo je číslo, ktorého súčet vlastných deliteľov sa rovná číslu samotnému
     */
    public static IntPredicate dokonale =
          x -> false;

    /**
     * spriatelené čísla: two numbers a and b are friends if the sum of proper divisors of a equals b
     * and vice versa. Example: 220 and 284 are friends (amicable numbers).
     * The method should filter from the input stream those numbers that have some amicable partner
     * (the partner need not be present in the input stream).
     */

    public static IntStream spriatelene(IntStream vstup){
        return IntStream.of();
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
