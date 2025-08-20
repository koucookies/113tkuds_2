
import java.util.*;

public class M03_TopKConvenience {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        // 建立 min-heap，根據銷量升序排序（銷量相同則依照輸入順序）
        PriorityQueue<Item> heap = new PriorityQueue<>(Comparator
                .comparingInt((Item i) -> i.qty)
                .thenComparing(i -> i.name));

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String name = parts[0];
            int qty = Integer.parseInt(parts[1]);

            Item item = new Item(name, qty);
            heap.offer(item);
            if (heap.size() > k) {
                heap.poll(); // 保持大小為 K
            }
        }

        // 將 heap 的內容取出並依照銷量從大到小排序
        List<Item> result = new ArrayList<>(heap);
        result.sort((a, b) -> {
            if (b.qty != a.qty) {
                return b.qty - a.qty;
            }
            return a.name.compareTo(b.name);
        });

        for (Item item : result) {
            System.out.println(item.name + " " + item.qty);
        }
    }

    static class Item {

        String name;
        int qty;

        Item(String name, int qty) {
            this.name = name;
            this.qty = qty;
        }
    }
}

/*
 * Time Complexity: O(n log k)
 * 說明：
 * - 對每一筆輸入最多維護 K 大小的 min-heap，插入/刪除操作時間為 O(log k)
 * - n 筆輸入總時間為 O(n log k)
 * - 最後對 K 個項目排序需 O(k log k)，但因 K ≪ n，可視為常數
 */
