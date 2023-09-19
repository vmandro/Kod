public class FibbonacciJAVA {

    static Integer[] table = new Integer[100];

    private static int fib(int n) {
        Integer result = table[n];
        if (result == null) {
            if (n < 2) result = 1;
            else result = fib(n - 2) + fib(n - 1);
            table[n] = result;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) System.out.println("fib(" + i + ")=" + fib(i));
    }

}
