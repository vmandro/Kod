/**
 * parametricky interface pre prioritny front, ktory obsahuje hodnoty typu E, s prioritami int
 * @param <E>  - na predpoklad nie je ziaden predpoklad
 */
public interface FrontInterface<E> {
        public void enqueue(E elem, int prio);          // zarad prvok elem s prioritou prio
        public E dequeue();                             // vyber prvok s najmensou prioritou
        public boolean isEmpty();                       // test, ci je front prazdny
}
