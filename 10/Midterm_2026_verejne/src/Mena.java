import java.util.List;

public class Mena {
    /**
     * @return vytvori mnozinu vsetkych kombinacii mien z krstne, priezviska, oddelene medzerou
     * vysledny zoznam je utriedeny podla priezviska a potom krstneho mena
     */
    public static List<String> names(List<String> krstne, List<String> priezviska) {
        return null; // doprogramuj
    }
    /**
     * @return vytvori mnozinu vsetkych kombinacii, utriedenu podla zadania
     * */
    public static List<String> names(List<List<String>> mena) {
        return null;  // doprogramuj
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
