
import java.util.*;

public class M12_MergeKTimeTables {

    static class Entry implements Comparable<Entry> {

        int time;
        int listIndex;
        int elemIndex;

        Entry(int time, int listIndex, int elemIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elemIndex = elemIndex;
        }

        public int compareTo(Entry other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> timeTables = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                list.add(sc.nextInt());
            }
            timeTables.add(list);
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>();

        // 初始化：每份表的第一個時間放入 heap
        for (int i = 0; i < K; i++) {
            if (!timeTables.get(i).isEmpty()) {
                pq.add(new Entry(timeTables.get(i).get(0), i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();

        while (!pq.isEmpty()) {
            Entry current = pq.poll();
            merged.add(current.time);

            int nextIndex = current.elemIndex + 1;
            if (nextIndex < timeTables.get(current.listIndex).size()) {
                int nextTime = timeTables.get(current.listIndex).get(nextIndex);
                pq.add(new Entry(nextTime, current.listIndex, nextIndex));
            }
        }

        for (int time : merged) {
            System.out.print(time + " ");
        }
    }
}
