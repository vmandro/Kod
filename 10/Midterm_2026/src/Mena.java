import java.util.List;

public class Mena {
    /**
     * @return vytvori mnozinu vsetkych kombinacii mien z krstne, priezviska, oddelene medzerou
     * vysledny zoznam je utriedeny podla priezviska a potom krstneho mena
     */
    public static List<String> names(List<String> krstne, List<String> priezviska) {
        return priezviska.stream().sorted().flatMap(
                m -> krstne.stream().sorted().map(n -> n + " " + m )).toList();
    }

    public static List<String> names(List<List<String>> mena) {
        if (mena.isEmpty()) return List.of();
        if (mena.size() == 1) return mena.get(0).stream().sorted().toList();
        // head is first list, tail is rest
        List<String> head = mena.get(0);
        List<List<String>> tail = mena.subList(1, mena.size());
        List<String> tailNames = names(tail);
        return tailNames.stream().flatMap(
                m-> head.stream().sorted().map(n -> n + " " + m)
        ).toList();
    }

    public static void main(String[] args) {
        System.out.println(names(List.of("Jozko", "Palko", "Petko"), List.of("Mrkvicka", "Hrasok")));
        System.out.println(names(List.of("Pablo", "Diego", "Jose"), List.of()));
        System.out.println(names(List.of("Pablo", "Diego", "José"), List.of("Adolph", "Blaine", "Charles")));

        System.out.println(names(List.of(
                List.of("Jozko", "Palko", "Petko"),
                List.of("Mrkvicka", "Hrasok"),
                List.of("Ml.", "St."))));

        System.out.println(names(
                List.of(
                        List.of("Pablo", "Diego", "Jose"),
                        List.of("Adolph", "Blaine", "Charles"),
                        List.of("Earl", "Frederick", "Gerald"),
                        List.of("David", "Johan"),
                        List.of("Napoleon")))
        );
        System.out.println(names(
                List.of(
                        List.of("Pablo", "Diego", "Jose"),
                        List.of("Adolph", "Blaine", "Charles"),
                        List.of(),
                        List.of("David", "Johan"),
                        List.of("Napoleon")))
        );
        System.out.println(names(List.of()));
    }
}
