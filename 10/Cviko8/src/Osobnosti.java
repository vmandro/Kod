import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Osobnosti {
    /**
     * konvertuje String mesiaca na int
     */
    private  static int monthConvertor(String m) {
        switch (m) {
            case "januara":
            case "januar":
                return 0;
            case "februara":
            case "februar":
                return 1;
            case "marca":
            case "marec":
                return 2;
            case "aprila":
            case "april":
                return 3;
            case "maja":
            case "maj":
                return 4;
            case "juna":
            case "jun":
                return 5;
            case "jula":
            case "jul":
                return 6;
            case "augusta":
            case "august":
                return 7;
            case "septembra":
            case "september":
                return 8;
            case "oktobra":
            case "oktober":
                return 9;
            case "novembra":
            case "november":
                return 10;
            case "decembra":
            case "december":
                return 11;
            default:
                return 0;
        }
    }
    /**
     * vyrobi Date z troch stringov
     */
    private static Date makeDate(String yyyy, String month, String dd) {
        return new GregorianCalendar(
                Integer.parseInt(yyyy),
                monthConvertor(month),
                Integer.parseInt(dd)
        ).getTime();
    }
    // vráti niektorý z najčastejších dátumov v súbore
    public static Date najcastejsiDatum(String fileName) {
        Map<Date, Integer> freq = new HashMap<>();
        try {
            Scanner sc = new Scanner(new File(fileName));
            Pattern pat = Pattern.compile(
                    ".*\\s(\\d{1,2})\\.\\s*(januara|januar|februara|februar|marca|marec|aprila|april|maja|maj|juna|jun|jula|jul|augusta|august|septembra|september|oktobra|oktober|novembra|november|decembra|december)\\s*(\\d{4}).*");

            while (sc.hasNextLine())     {
                String line = sc.nextLine();
                //System.out.println(line);
                Matcher m = pat.matcher(line);
                if (m.matches()) {
                    Date d = makeDate(m.group(3), m.group(2), m.group(1));
//                    freq.computeIfAbsent(d, k -> 0);
//                    freq.put(d, (Integer)freq.get(d)+1);
                    freq.compute(d, (k, v) -> (v == null) ? 1 : (Integer)v + 1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(freq);
        System.out.println(freq.values().stream().max(Integer::compareTo).orElse(0));
        System.out.println(freq.entrySet().stream().filter(x -> x.getValue() == 3).toList());
        return freq.entrySet().stream().filter(x -> x.getValue() == 3)
                .map(x -> x.getKey()).toList().get(0);  // dorobte
    }
    public static void main(String[] args) {
        System.out.println(najcastejsiDatum("osobnosti.txt"));
    }
}
