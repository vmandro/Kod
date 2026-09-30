import java.util.*;

public class Mena {

    // 1. verzia: krstné meno + priezvisko
    public static List<String> names(List<String> krstne, List<String> priezviska) {
        List<String> result = new ArrayList<>();
        if (krstne == null || priezviska == null) return result;
        // sort copies so výsledok je usporiadaný podľa priezviska, potom podľa krstného mena
        List<String> sortedPriezviska = new ArrayList<>(priezviska);
        List<String> sortedKrstne = new ArrayList<>(krstne);
        Collections.sort(sortedPriezviska);
        Collections.sort(sortedKrstne);

        for (String priezvisko : sortedPriezviska) {        // najprv priezviská (hlavné triedenie)
            for (String krstneMeno : sortedKrstne) {       // potom krstné mená
                result.add(krstneMeno + " " + priezvisko);
            }
        }

        return result;
    }

    // 2. verzia: viac častí mena
    public static List<String> names(List<List<String>> mena) {
        List<String> result = new ArrayList<>();

        // ak je prázdny zoznam alebo niektorý podzoznam prázdny → prázdny výsledok
        if (mena == null || mena.isEmpty()) return result;
        int parts = mena.size();
        List<List<String>> sortedParts = new ArrayList<>(parts);
        for (List<String> part : mena) {
            if (part == null || part.isEmpty()) return result;
            List<String> copy = new ArrayList<>(part);
            Collections.sort(copy);
            sortedParts.add(copy);
        }

        // We'll generate combinations so that the last part is the most significant
        // i.e., we iterate outermost over the last list, then over second-last, ...,
        // and innermost over the first list. For each full choice we join parts in order 0..last.
        String[] chosen = new String[parts];
        buildReversed(sortedParts, parts - 1, chosen, result);
        return result;
    }

    private static void buildReversed(List<List<String>> sortedParts, int idx, String[] chosen, List<String> result) {
        // idx is current index we are choosing for; we start from last index and go down to 0
        List<String> part = sortedParts.get(idx);
        for (String s : part) {
            chosen[idx] = s;
            if (idx > 0) {
                buildReversed(sortedParts, idx - 1, chosen, result);
            } else {
                // build full name from chosen[0..last]
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < chosen.length; i++) {
                    if (i > 0) sb.append(' ');
                    sb.append(chosen[i]);
                }
                result.add(sb.toString());
            }
        }
    }
}