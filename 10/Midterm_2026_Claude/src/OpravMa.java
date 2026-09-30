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
        // searching in descending order array
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) {
                // mid value greater than target; in descending array target is on the right
                left = mid + 1;
            } else {
                // arr[mid] < target -> target is on the left
                right = mid - 1;
            }
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
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            int start = i + 1;
            int end = 2 * (i + 1) - 1;
            for (int v = start; v <= end; v++) row.add(v);
            result.add(row);
        }
        return result;
    }

    public static void Uloha5(Integer [][] a) throws Exception {
        if (a == null) throw new Exception("zly vstup");
        int rows = a.length;
        if (rows == 0) return; // empty array is considered rectangular and fine
        int cols = a[0] != null ? a[0].length : -1;
        if (cols < 0) throw new Exception("zly vstup");
        for (int i = 0; i < rows; i++) {
            if (a[i] == null) throw new Exception("zly vstup");
            if (a[i].length != cols) throw new Exception("zly vstup");
            for (int j = 0; j < cols; j++) {
                if (a[i][j] == null) throw new Exception("zly vstup");
            }
        }
        // otherwise fine
    }

}
