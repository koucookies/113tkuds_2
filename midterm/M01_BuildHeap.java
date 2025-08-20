
import java.util.*;

public class M01_BuildHeap {

    static String type;
    static int[] heap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        type = sc.next();
        int n = sc.nextInt();
        heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = sc.nextInt();
        }

        buildHeap(n);

        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + (i == n - 1 ? "\n" : " "));
        }
    }

    static void buildHeap(int n) {
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(i, n);
        }
    }

    static void heapifyDown(int i, int n) {
        int largestOrSmallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && heap[left] > heap[largestOrSmallest]) {
                largestOrSmallest = left;
            }
            if (right < n && heap[right] > heap[largestOrSmallest]) {
                largestOrSmallest = right;
            }
        } else {
            if (left < n && heap[left] < heap[largestOrSmallest]) {
                largestOrSmallest = left;
            }
            if (right < n && heap[right] < heap[largestOrSmallest]) {
                largestOrSmallest = right;
            }
        }

        if (largestOrSmallest != i) {
            int temp = heap[i];
            heap[i] = heap[largestOrSmallest];
            heap[largestOrSmallest] = temp;
            heapifyDown(largestOrSmallest, n);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆的平均與最差時間複雜度為 O(n)，因為高度較低的節點數量較多，每層處理次數總和呈現遞減趨勢。
 *      總複雜度為 Σ floor(n/2^h) * O(h) ≈ O(n)
 */
