
import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        List<int[]> profits = new ArrayList<>();
        int n = prices.length;
        int i = 0;

        while (i < n - 1) {
            // 找買入點：局部最低點
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i++;

            // 找賣出點：局部最高點
            while (i < n && prices[i] >= prices[i - 1]) {
                i++;
            }
            int sell = i - 1;

            // 計算利潤
            if (buy < sell) {
                int profit = prices[sell] - prices[buy];
                profits.add(new int[]{profit, buy, sell});
            }
        }

        // 使用 Max Heap 儲存最大利潤
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] p : profits) {
            maxHeap.offer(p[0]);
        }

        int total = 0;
        for (int j = 0; j < K && !maxHeap.isEmpty(); j++) {
            total += maxHeap.poll();
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("測試案例 1: " + maxProfit(new int[]{2, 4, 1}, 2));         // 2
        System.out.println("測試案例 2: " + maxProfit(new int[]{3, 2, 6, 5, 0, 3}, 2)); // 7
        System.out.println("測試案例 3: " + maxProfit(new int[]{1, 2, 3, 4, 5}, 2));     // 4
    }
}
