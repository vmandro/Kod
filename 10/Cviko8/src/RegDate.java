import java.util.List;
import java.util.regex.Pattern;

public class RegDate {

    public static String RegExpDate() {
        return
//                "(19|20)\\d{2}-(0[1-9]|1[0-2])-" +
//                        "(0[1-9]|[12][0-9]|3[01])";
        "(15|16|17|18|19|20|21|22|23|24)\\d{2}-"+
                "((01|03|05|07|08|10|12)-(0[1-9]|[12][0-9]|3[01])|"+
                "(04|06|09|11)-(0[1-9]|[12][0-9]|3[0])|"+
                "(02)-(0[1-9]|[12][0-8]))"+
                "|"+
                "(15|16|17|18|19|20|21|22|23|24)(0[48]|[2468][048]|[13579][26])-02-29" +
                "|"+
                "1600-02-29" +
                "|" +
                "2000-02-29" +
                "|" +
                "2400-02-29"
                ;
    }

    public static void main(String[] args) {
        var r = RegExpDate();
        var x = Pattern.compile(r);
        var tests = List.of(
                "2024-06-30",
                "2024-01-31",
                "2024-02-29",
                "2020-02-29",
                "2000-02-29"
        );
        tests.forEach(t ->
            System.out.println(x.matcher(t).matches()) // true
        );
    }
}
