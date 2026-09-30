import java.util.*;

public class Organizacia<E> {
    private final Map<E, List<E>> org;
    private final Map<E, E> parent; // subordinate -> their boss

    public Organizacia(Map<E, List<E>> org) {
        this.org = org;
        this.parent = new HashMap<>();
        for (Map.Entry<E, List<E>> entry : org.entrySet()) {
            for (E sub : entry.getValue()) {
                parent.put(sub, entry.getKey());
            }
        }
    }

    // CEO: person who is not a subordinate of anyone
    public E ceo() {
        Set<E> allSubordinates = new HashSet<>(parent.keySet());
        for (E boss : org.keySet()) {
            if (!allSubordinates.contains(boss)) {
                return boss;
            }
        }
        return null;
    }

    // Leaves: subordinates who are not bosses of any group
    public Set<E> podriadeni() {
        Set<E> leaves = new HashSet<>();
        Set<E> bosses = org.keySet();
        for (E sub : parent.keySet()) {
            if (!bosses.contains(sub)) {
                leaves.add(sub);
            }
        }
        return leaves;
    }

    // Week when all direct+indirect subordinates of virusomNakazeny are infected
    // Infection spreads through standups: each week, infected person infects their whole meeting group
    public int infikovaneOddelenie(E virusomNakazeny) {
        if (!org.containsKey(virusomNakazeny)) return 0;

        // BFS through subtree, tracking infection week for each node
        Map<E, Integer> infectedWeek = new HashMap<>();
        infectedWeek.put(virusomNakazeny, 0);

        Queue<E> queue = new LinkedList<>();
        queue.add(virusomNakazeny);
        int maxWeek = 0;

        while (!queue.isEmpty()) {
            E current = queue.poll();
            int week = infectedWeek.get(current);

            // current infects their subordinates at week+1 (they share a standup)
            if (org.containsKey(current)) {
                for (E sub : org.get(current)) {
                    if (!infectedWeek.containsKey(sub)) {
                        infectedWeek.put(sub, week + 1);
                        maxWeek = Math.max(maxWeek, week + 1);
                        queue.add(sub);
                    }
                }
            }
        }

        return maxWeek;
    }

    // Week when the entire organization is infected
    public int infikovanaOrganizacia(E virusomNakazeny) {
        Map<E, Integer> infectedWeek = new HashMap<>();
        infectedWeek.put(virusomNakazeny, 0);

        // BFS with priority queue (process in order of infection week)
        PriorityQueue<Map.Entry<E, Integer>> pq = new PriorityQueue<>(
                Map.Entry.comparingByValue()
        );
        pq.add(new AbstractMap.SimpleEntry<>(virusomNakazeny, 0));

        int maxWeek = 0;

        while (!pq.isEmpty()) {
            Map.Entry<E, Integer> entry = pq.poll();
            E current = entry.getKey();
            int week = entry.getValue();

            // Skip if we already processed this node with a smaller week
            if (infectedWeek.get(current) < week) continue;

            // 1. Infect subordinates (downward): boss meets with subs
            if (org.containsKey(current)) {
                for (E sub : org.get(current)) {
                    int newWeek = week + 1;
                    if (!infectedWeek.containsKey(sub) || infectedWeek.get(sub) > newWeek) {
                        infectedWeek.put(sub, newWeek);
                        maxWeek = Math.max(maxWeek, newWeek);
                        pq.add(new AbstractMap.SimpleEntry<>(sub, newWeek));
                    }
                }
            }

            // 2. Infect boss and co-subordinates (upward): they share a standup together
            if (parent.containsKey(current)) {
                E boss = parent.get(current);
                // Infect the boss
                int newWeek = week + 1;
                if (!infectedWeek.containsKey(boss) || infectedWeek.get(boss) > newWeek) {
                    infectedWeek.put(boss, newWeek);
                    maxWeek = Math.max(maxWeek, newWeek);
                    pq.add(new AbstractMap.SimpleEntry<>(boss, newWeek));
                }
                // Infect co-subordinates (siblings)
                for (E sibling : org.get(boss)) {
                    if (!infectedWeek.containsKey(sibling) || infectedWeek.get(sibling) > newWeek) {
                        infectedWeek.put(sibling, newWeek);
                        maxWeek = Math.max(maxWeek, newWeek);
                        pq.add(new AbstractMap.SimpleEntry<>(sibling, newWeek));
                    }
                }
            }
        }

        return maxWeek;
    }

    // Test
    public static void main(String[] args) {
        var o = new Organizacia<>(
                Map.of(
                        0, List.of(1, 2, 3, 4),
                        1, List.of(6, 7),
                        2, List.of(8),
                        3, List.of(9, 10),
                        7, List.of(11, 12, 13),
                        10, List.of(14)
                )
        );

        System.out.println("CEO: " + o.ceo());                          // 0
        System.out.println("Leaves: " + o.podriadeni());                // [4,6,8,9,11,12,13,14]
        System.out.println("infikovaneOddelenie(1): " + o.infikovaneOddelenie(1));   // 2
        System.out.println("infikovaneOddelenie(0): " + o.infikovaneOddelenie(0));   // 3
        System.out.println("infikovaneOddelenie(14): " + o.infikovaneOddelenie(14)); // 0
        System.out.println("infikovanaOrganizacia(1): " + o.infikovanaOrganizacia(1));  // 3
        System.out.println("infikovanaOrganizacia(0): " + o.infikovanaOrganizacia(0));  // 3
        System.out.println("infikovanaOrganizacia(14): " + o.infikovanaOrganizacia(14));// 5
    }
}