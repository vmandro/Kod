public class RunMenaTest {
    public static void main(String[] args) {
        var a = java.util.List.of("Jožko", "Paľko", "Peťko");
        var b = java.util.List.of("Mrkvička", "Hrášok");
        System.out.println(Mena.names(a,b));

        var parts = java.util.List.of(
            java.util.List.of("Jožko", "Paľko", "Peťko"),
            java.util.List.of("Mrkvička", "Hrášok"),
            java.util.List.of("Ml.", "St.")
        );
        System.out.println(Mena.names(parts));
    }
}
