import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OpravMa {

    /**
     * binarne vyhladavanie v poli, ktore je zoradene zostupne
     * teda prvy prvok pola je najvacsi, posledny je najmensi
     * v kode je logicka chyba, alebo viacere chyby, ktoru/e
     * treba opravit, aby metoda fungovala spravne,
     * po oprave musi zbehnut test testUloha1 v TestOpravMa1.java
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        //System.out.println(Arrays.toString(arr));
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] >= target) left = mid;
            else right = mid;
        }
        return -1;
    }

    /**
     * vytvori list listov, kde i-ty list obsahuje i+1 prvkov,
     * a to i+1, ..., 2*(i+1)-1
     // 1
     // 2,3
     // 3,4,5
     //...
     // n,n+1,....2n-1
     * @return - ALE NEFUNGUJE, OPRAVTE KOD, ABY FUNGOVAL SPRAVNE,
     * PO OPRAVE MUSI ZBEHNUT TEST testUloha2 v TestOpravMa2.java
     */
    public static List<List<Integer>> Uloha2(int n) {
        List<Integer> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.addAll(List.of(2*i, 2*i+1));
            list.removeFirst();
            result.add(list);       // do result prida dalsi, este dlhsi riadok 0,1, ..., i
        }
        return result;
    }

    public static void Uloha5(Integer [][] a) throws Exception {
        // doprogramuj
    }

}
