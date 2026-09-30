import java.util.List;
import java.util.Map;
import java.util.Set;

public class Organizacia<E> {
    Map<E, List<E>> organizacia;

    public Organizacia(Map<E, List<E>> organizacia) {
        this.organizacia = organizacia;
    }

    /**
     * @return CEO organizácie, teda vrchol stromu, ktorý už
     * nemá žiadneho nadriadeného.
     * Môžete predpokladať, že organizácia je koreňový strom,
     * teda že existuje práve jeden zamestnanec (CEO),
     * ktorý nemá nadriadeného.
     */
    public E ceo() {
        return null; // doprogramuj
    }

    /**
     * @return - vráti zoznam všetkých podriadených,
     * ktorí nie su šefom žiadnej skupiny, teda listy stromu
     */
    public Set<E> podriadeni() {
        return null; // doprogramuj
    }

    /**
     * @return vráti čas (v tyždňoch), kedy sa nakazia všetci
     * priami ale aj nepriami podriadení vírusom nakazeného
     * zamestnanca
     */
    public int infikonaveOddelenie(E virusomNakazeny) {
        return -1; // doprogramuj
    }
     /**
     * @return vráti čas (v tyždňoch), kedy sa nakazí celá
      * organizácia, teda všetci jej zamestnanci
     */
    public int infikonavaOrganizacia(E virusomNakazeny) {
        return -1;  // doprogramuj
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
