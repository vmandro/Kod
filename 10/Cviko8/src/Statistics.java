import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Statistics {
    static final String url = "https://raw.githubusercontent.com/datasets/covid-19/master/data/countries-aggregated.csv?fbclid=IwAR2iKFpJVdryhZx5A6h_T66-eLZ0kcgciEbGxvZLdnQvW3b-fo2vvVvbiTY";

    private static Stream<String> fetch(String path) {
        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new URL(path).openStream()));
            return read.lines();
        } catch (IOException e) {
            System.out.println("nieco zle sa stalo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        List<String> lines = fetch(url).collect(Collectors.toList());

        var m =
                lines.stream().skip(1).
                mapToInt(
                        line -> Integer.parseInt(
                            line.replaceAll("Korea, South", "Korea-South")
                                .split(",")[2]))
                .filter(x -> x > 0)
                .mapToObj(x -> ""+x)
                .map(x -> x.charAt(0))
                .collect(Collectors.groupingBy(y -> y, Collectors.counting()));

        var a = m.values().stream().max(Long::compareTo).orElse(0L);
        var b = m.values().stream().mapToLong(x->x).sum();
        System.out.println((double)a/b);

        // dalej pokracujte vy...
    }
}


