import java.util.*;

public class DFA<S,A> {
    private Set<S> states;          // mnozina stavov
    private S initState;            // pociatocny stav
    private Set<A> alphabet;        // abeceda symbolov slova
    private Set<S> finalStates;     // mnozina koncovych/akceptujucich stavov
    private Map<S, Map<A, S>> delta; // delta funkcia, zobrazuje stav a pismenko na novy stav

    public DFA(Set<S> states, S initState, Set<A> alphabet, Set<S> finalStates, Map<S, Map<A, S>> delta) {
        this.states = states;
        this.initState = initState;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "DFA{" +
                "states=" + states +
                ", initState=" + initState +
                ", alphabet=" + alphabet +
                ", finalStates=" + finalStates +
                ", delta=" + delta +
                '}';
    }

    public Set<S> getStates() {
        return states;
    }

    public S getInitState() {
        return initState;
    }

    public Set<A> getAlphabet() {
        return alphabet;
    }

    public Set<S> getFinalStates() {
        return finalStates;
    }

    public Map<S, Map<A, S>> getDelta() {
        return delta;
    }
    
    /**
     * tento kus kodu som vam nechal len preto, aby
     * som ilustroval, ze zistit, ci automat akceptuje
     * dane vstupne slovo je uplne priamociare, a aj
     * ako ilustraciu na pracu so strukturami automatu
     */
    public boolean accept(List<A> word) {
        S s = initState;
        // nerad indexujem
        for (A ch : word)
            s = delta.get(s).get(ch);

        // rad indexujem
//        for (int i = 0; i < word.size(); i++)
//            s = delta.get(s).get(word.get(i));
        return finalStates.contains(s);
    }

    public boolean prazdny() {
        S s = initState;
        for (S fs : finalStates) {
            if (existujeCesta(s, fs /*, new HashSet<>()*/)) {
                return false;
            }
        }
        if (finalStates.contains(initState)){
            return false;
        }
        return true;
    }

//    private boolean existujeCesta(S state, S fs, Set<S> visited) {
//        if (fs.equals(state) ) {
//            return true;
//        }
//        visited.add(state);
//        for (A a : alphabet) {
//            S nextState = delta.get(state).get(a);
//            if (!visited.contains(nextState)) {
//                if (existujeCesta(nextState, fs, visited)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }


    public boolean existujeCesta(S startState, S endState){
        Set<S> visited = new HashSet<>();
        var stack = new Stack<S>();
        stack.push(startState);
        while (!stack.isEmpty()){
            S currentState = stack.pop();
            visited.add(currentState);
            for (S nextState : delta.get(currentState).values()){
                if (nextState.equals(endState)){
                    return true;
                }
                if (!visited.contains(nextState)){
                    stack.push(nextState);
                }
            }
        }
        return false;
    }
    public boolean nekonecny() {
        for (S s : states) {
            if (existujeCesta(s, s /*new HashSet<>()*/)) {
                // cyklus s -> s
                if (existujeCesta(initState, s /*new HashSet<>()*/)) {
                    // cesta initState -> s
                    for (S endState : finalStates){
                        if (existujeCesta(s, endState /*new HashSet<>()*/)){
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public Set<List<A>> language(int len) {
        Set<List<A>> result = new HashSet<>();
        solve(len, initState, new ArrayList<>(), result);
        return result;
    }
    private void solve(int len, S state, List<A> prefix, Set<List<A>> result) {
        if (len == 0) {
            if (finalStates.contains(state)) {
                result.add(prefix);
            }
        } else {
            for (A a : alphabet) {
                prefix.add(a);
                solve(len - 1, delta.get(state).get(a), prefix, result);
                //prefix.remove(prefix.size() - 1);
                prefix.remove(a);
            }
        }
    }

    public static void main(String[] args) {
        var dfa = new DFA<String, Character>(
                Set.of("a", "b", "c", "d"),
                "a",
                Set.of('0', '1'),
                Set.of("d"),
                Map.of("a", Map.of(
                                '0', "b",
                                '1', "a"),
                       "b", Map.of(
                                '0', "b",
                                '1', "c"),
                       "c", Map.of(
                                '0', "d",
                                '1', "a"),
                        "d", Map.of(
                                '0', "d",
                                '1', "d")

                )
        );
        System.out.println(dfa.accept(List.of('1','1','0','0','1','0','0','1')));
        System.out.println(dfa.language(10));
        System.out.println(dfa.prazdny());
        System.out.println(dfa.nekonecny());
    }
}
