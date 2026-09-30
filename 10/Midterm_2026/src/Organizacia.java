import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Organizacia<E> {
    Map<E, List<E>> organizacia;

    public Organizacia(Map<E, List<E>> organizacia) {

        this.organizacia = organizacia;
    }

    /**
     * @return Nájde CEO organizácie, teda vrchol stromu, ktorý
     * nemá žiadneho nadriadeného. Môžete predpokladať, že organizácia je koreňový strom,
     * teda že existuje práve jeden zamestnanec (CEO), ktorý nemá nadriadeného.
     */
    public E ceo() {
        var nadriadeni = new HashSet<>(organizacia.keySet());
        var podriadeni = organizacia.values().stream().flatMap(List::stream).toList();
        nadriadeni.removeAll(podriadeni);
        return nadriadeni.stream().findFirst().orElseThrow();
    }

    /**
     * @return - vrati zoznam vsetkych podriadenych, ktori nie su sefom ziadnej skupiny, teda listy stromu
     */
    public Set<E> podriadeni() {
        var nadriadeni = new HashSet<>(organizacia.keySet());
        var podriadeni = new HashSet<>(organizacia.values().stream().flatMap(List::stream).toList());
        podriadeni.removeAll(nadriadeni);
        return podriadeni;
    }

    /**
     * @return vrati cas (v tyzdnoch), kedy sa nakazia,
     * ktori su priami ale aj nepriami podriadeni nakazeneho zamestnanca
     */
    public int infikonaveOddelenie(E virusomNakazeny) {
        var x = organizacia.get(virusomNakazeny);
        return (x == null)?0:
                x.stream()
                .map(z -> 1 + infikonaveOddelenie(z))
                .max(Integer::compareTo)
                .orElse(0);
    }


    public int infikonaveOddelenie1(E virusomNakazeny) {
        Queue<Pair<E>> q = new java.util.LinkedList<>();
        q.add(new Pair<>(virusomNakazeny, 0));
        var maxTyzden = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            var zamestnanec = p.zamestnanec;
            var tyzden = p.tyzden;
            if (organizacia.containsKey(zamestnanec)) {
                for (var podriadeny : organizacia.get(zamestnanec)) {
                    q.add(new Pair<>(podriadeny, tyzden + 1));
                    if (tyzden + 1 > maxTyzden) {
                        maxTyzden = tyzden + 1;
                    }
                }
            }
        }
        return maxTyzden;
    }
    /**
     * @return vrati cas (v tyzdnoch), kedy sa nakazi cela organizacia, teda vsetci zamestnanci
     */
    public int infikonavaOrganizacia(E virusomNakazeny) {
        Queue<Pair<E>> q = new java.util.LinkedList<>();
        q.add(new Pair<>(virusomNakazeny, 0));
        Set<E> nakazeni = new HashSet<>();
        nakazeni.add(virusomNakazeny);
        var maxTyzden = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            var zamestnanec = p.zamestnanec;
            var tyzden = p.tyzden;
            for (var k : organizacia.keySet()) {
                var v = organizacia.get(k);
                if (k.equals(zamestnanec) || v.contains(zamestnanec)) {
                    var standup = new ArrayList<>(v);
                    standup.add(k);
                    for(var y : standup) {
                        if (!nakazeni.contains(y)) {
                            nakazeni.add(y);
                            q.add(new Pair<>(y, tyzden + 1));
                            if (tyzden + 1 > maxTyzden)
                                maxTyzden = tyzden + 1;
                        }
                    }
                }
            }
        }
        return maxTyzden;
    }

//    public int infikonavaOrganizacia(E virusomNakazeny) {
//        Queue<Pair<E>> q = new java.util.LinkedList<>();
//        q.add(new Pair<>(virusomNakazeny, 0));
//        Set<E> nakazeni = new HashSet<>();
//        nakazeni.add(virusomNakazeny);
//        var maxTyzden = 0;
//        while (!q.isEmpty()) {
//            var p = q.poll();
//            var zamestnanec = p.zamestnanec;
//            var tyzden = p.tyzden;
//            if (organizacia.containsKey(zamestnanec)) {
//                for (var podriadeny : organizacia.get(zamestnanec)) {
//                    if (nakazeni.contains(podriadeny)) continue;
//                    nakazeni.add(podriadeny);
//                    q.add(new Pair<>(podriadeny, tyzden + 1));
//                    if (tyzden + 1 > maxTyzden) {
//                        maxTyzden = tyzden + 1;
//                    }
//                }
//            }
//            for (var nadriadeny : nadriadeni(zamestnanec)) {
//                    if (nakazeni.contains(nadriadeny)) continue;
//                    //System.out.println("nakazi sa nadriadeny " + nadriadeny + " od " + zamestnanec);
//                    nakazeni.add(nadriadeny);
//                    q.add(new Pair<>(nadriadeny, tyzden + 1));
//                    if (tyzden + 1 > maxTyzden) {
//                        maxTyzden = tyzden + 1;
//                    }
//            }
//        }
//        return maxTyzden;
//    }
//    private List<E>nadriadeni(E zamestnanec) {
//        return organizacia.entrySet()
//                .stream()
//                .filter(e -> e.getValue().contains(zamestnanec))
//                .map(Map.Entry::getKey).toList();
//    }
    static class Pair<E> {
        final E zamestnanec;
        final int tyzden;

        Pair(E zamestnanec, int tyzden) {
            this.zamestnanec = zamestnanec;
            this.tyzden = tyzden;
        }
    }

    public static void main(String[] args) {
        var o = new Organizacia<>(
            Map.of(
                0, List.of(1,2,3,4),
                1, List.of(6,7),
                2, List.of(8),
                3, List.of(9,10),
                7,  List.of(11,12,13),
                10, List.of(14)
        ));
        System.out.println(o.ceo()); // 0
        System.out.println(o.podriadeni()); // 4, 6, 8, 9, 11, 12, 13, 14
        System.out.println(o.infikonaveOddelenie(1)); // 2
        System.out.println(o.infikonavaOrganizacia(1)); //  3
        System.out.println(o.infikonaveOddelenie(0)); // 3
        System.out.println(o.infikonavaOrganizacia(0)); //  3
        System.out.println(o.infikonaveOddelenie(14)); // 0
        System.out.println(o.infikonavaOrganizacia(14)); //  5
    }
}
