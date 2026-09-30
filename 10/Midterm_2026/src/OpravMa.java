import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OpravMa {

    /**
     * binarne vyhladavanie v poli, ktore je zoradene zostupne
     * teda prvy prvok pola je najvacsi, posledny je najmensi
     * v kode je logicka chyba, ktoru treba opravit,
     * aby metoda fungovala spravne, po oprave musi zbehnut test
     * testUloha1 v TestOpravMa1.java
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        //System.out.println(Arrays.toString(arr));
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] >= target) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }
    public static List<List<Integer>> Uloha2(int n) {
        List<Integer> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.addAll(List.of(2*i, 2*i+1));
            list.removeFirst();
            result.add(new ArrayList<>(list));       // do result prida dalsi, este dlhsi riadok 0,1, ..., i
        }
        return result;
        // 1
        // 2,3
        // 3,4,5
        //...
        // n,n+1,....2n-1
    }

    public static void Uloha5(Integer [][] a) throws Exception {
        if (a == null) throw new Exception("zly vstup");
        var len =a[0].length;
        for(int i = 0; i < a.length; i++) {
            if (a[i] == null) throw new Exception("zly vstup");
            if (a[i].length != len) throw new Exception("zly vstup");
            for(int j = 0; j < a[i].length; j++) {
                if (a[i][j] == null) throw new Exception("zly vstup");
            }
        }
    }
}
