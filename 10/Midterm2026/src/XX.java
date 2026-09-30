import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class XX {
    // Pablo Diego José Francisco de Paula Juan Nepomuceno Crispín Crispiniano María Remedios de la Santísima Trinidad Ruiz Picasso
    // Adolph Blaine Charles David Earl Frederick Gerald Hubert Irvin John Kenneth Lloyd Martin Nero Oliver Paul Quincy Randolph Sherman Thomas Uncas Victor William Xerxes Yancy Zeus Wolfeschlegelsteinhausenbergerdorff Sr

    /**
     * @return vytvori mnozinu vsetkych kombinacii mien z mena1, mena2, oddelene medzerou
     */
    public static Set<String> names(List<String> mena1, List<String> mena2) {
        return mena1.stream().flatMap(
                m -> mena2.stream().map(n -> m + " " + n )).collect(Collectors.toSet());
    }

    public static Set<String> names(List<List<String>> mena) {
        if (mena.size() == 0) return Set.of();
        return mena.getFirst().stream().flatMap(
                m -> mena.stream().skip(1)
                        .map(n -> m + " " + n )).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(names(List.of("Pablo", "Diego", "José"), List.of()));
        System.out.println(names(List.of("Pablo", "Diego", "José"), List.of("Adolph", "Blaine", "Charles")));
        System.out.println(names(
                List.of(
                        List.of("Pablo", "Diego", "José"),
                        List.of("Adolph", "Blaine", "Charles"),
                        List.of("Earl", "Frederick", "Gerald"),
                        List.of("David", "Earl", "Frederick"))
        ));
    }
}
