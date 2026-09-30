/**
 * implementacia prioritneho frontu pomocu pola
 *
 * @param <E> idea, ktoru realizujte: konstruktor naalokuje pole velkosti size.
 *            Nejde to urobit takto E[] pole = new E[size], dovody su v prednaske
 *            Ide to urobit takto E[] pole = (E[])new Object[size]
 *            dequeue musi vratit prvok s najmensou prioritou, preto je najrozumnejsie, aby bole prvkov typu E bolo
 *            utriedene podla priorit, od najmensej po najvacsiu. Kedze prvky pribudaju a ubudaju, zistite z prednasky
 *            ako sa implementuje front v poli efektivne, aby ste ho stale neposuvali v poli. Stacia na to dva indexy, prvy
 *            a posledny, a rozmyslat, ze front moze odkracat cez hranu pola. Modulo size vas zahrani.
 *            Ak mate ploe utriedene, tak dequeue je trivialne, vyberie prvy prvok frontu.
 */
public class ArrayPF<E> implements FrontInterface<E> {
    private E[] values;
    private int[] priorities;
    private int first;
    private int last;
    /**
     * konstruktor
     */
    public ArrayPF(int size) {
        values = (E[]) new Object[size];
        priorities = new int[size];
        first = 0;
        last = 0;
    }

    /**
     * zarad prvok elem s prioritou prio
     */
    @Override
    public void enqueue(E elem, int prio) {
        var n = values.length;
        if ((last + 1) % values.length == first) {
            throw new RuntimeException("front je plny");
        }
        var l = this.last;
        while (l != first && priorities[Math.floorMod(l - 1,n)] > prio) {
            values[Math.floorMod(l,n)] = values[Math.floorMod(l - 1,n)];
            priorities[Math.floorMod(l,n)] = priorities[Math.floorMod(l - 1,n)];
            l = Math.floorMod(l - 1,n);
        }
        values[l] = elem;
        priorities[l] = prio;
        last = Math.floorMod(last+1, n);
    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        if (!isEmpty()) {
            E result = values[first];
            first = (first + 1) % values.length;
            return result;
        } else {
            throw new RuntimeException("front je prazdny");
        }
    }

    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        return first == last;
    }

    public static void main(String[] args) {
		FrontInterface<String> f = new ArrayPF<>(100);
        f.enqueue(new String("janka"), 5);
        f.enqueue(new String("danka"), 2);
        f.enqueue(new String("hanka"), 1);
        f.enqueue(new String("anka"), 4);
        f.enqueue(new String("zuzanka"), 3);
        f.enqueue(new String("elenka"), 1);
        f.enqueue(new String("zofka"), 6);
        f.enqueue(new String("evka"), 4);
        System.out.println(f);
        while (!f.isEmpty()) {
            System.out.println(f.dequeue());
        }
    }
}
