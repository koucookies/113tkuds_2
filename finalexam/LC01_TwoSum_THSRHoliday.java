
import java.util.*;

public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        long target = sc.nextLong();

        long[] seats = new long[n];
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextLong();
        }

        // HashMap<需要的數, 索引>
        Map<Long, Integer> map = new HashMap<>();
        int idx1 = -1, idx2 = -1;

        for (int i = 0; i < n; i++) {
            long x = seats[i];
            if (map.containsKey(x)) {
                idx1 = map.get(x);
                idx2 = i;
                break;
            }
            long need = target - x;
            map.put(need, i);
        }

        System.out.println(idx1 + " " + idx2);

        sc.close();
    }
}
