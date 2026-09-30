/**
 * implementacia prioritneho frontu spajaneho zoznamu s hodntami typu E
 * v tomto pripade je spajany zoznam prvkov typu E, pricom je utriedeny opat podla rastucich priorit
 * najnizsia priorita je na zaciatku
 * dequeue je lahle, pri enqueue treba sikovne najst miesto, kam treba prvok vsunut
 */

public class ListPF<E> implements FrontInterface<E> {
    Node<E> first;

    /**
     * konstruktor
     */
    public ListPF() {
        first = null;
    }

    /**
     * zarad prvok elem s prioritou prio
     */
    @Override
    public void enqueue(E elem, int prio) {
        Node<E> f = first;
        Node<E> pf = null;
        while (f != null && f.getPrior() <= prio) {
            pf = f;
            f = f.getNext();
        }
        if (pf == null) {
            first = new Node<>(prio, elem, f, null);
        } else {
            pf.setNext(new Node<>(prio, elem, f, pf));
        }
    }

    /**
     * vyber prvok s najmensou prioritou
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("prazdny front");
        }
        E result = first.getElement();
        first = first.getNext();
        return result;
    }

    /**
     * test, ci je front prazdny
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        FrontInterface<String> f = new ListPF<>();
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

    /**
     * toto je trieda, ktoru mozete pouzit na spajany zoznam
     *
     * @param <E>
     */
    public static class Node<E> {
        private int prior;        // priorita prvku
        private E element;        // hodnota prvku
        private Node<E> next;     // link na nasledujuci prvok
        private Node<E> prev;     // link na predchadzajuci prvok

        /**
         * konstruktor krabice
         */
        public Node(int prior, E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.prior = prior;
            this.next = next;
            this.prev = prev;
        }

        /**
         * gettery
         */
        public E getElement() {
            return element;
        }

        public int getPrior() {
            return prior;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        /**
         * settery
         */
        public void setNext(Node<E> new_next) {
            next = new_next;
        }

        public void setPrev(Node<E> new_prev) {
            prev = new_prev;
        }
    }
}
