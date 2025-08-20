
import java.util.*;

public class M11_HeapSortWithTie {

    static class Score implements Comparable<Score> {

        int value;
        int index;

        Score(int value, int index) {
            this.value = value;
            this.index = index;
        }

        // Max-Heap 排序規則：分數大優先；若分數相同，index 小者優先
        public int compareTo(Score other) {
            if (this.value != other.value) {
                return this.value - other.value; // 遞增排序用小根堆
            } else {
                return other.index - this.index; // index 小的優先（倒序回來）
            }
        }
    }

    public static void heapSort(Score[] arr) {
        int n = arr.length;

        // 建立 Heap（Bottom-Up）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Heap Sort 流程
        for (int i = n - 1; i > 0; i--) {
            Score tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(Score[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l].compareTo(arr[largest]) > 0) {
            largest = l;
        }
        if (r < n && arr[r].compareTo(arr[largest]) > 0) {
            largest = r;
        }

        if (largest != i) {
            Score tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Score[] arr = new Score[n];

        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            arr[i] = new Score(score, i);
        }

        heapSort(arr);

        for (Score s : arr) {
            System.out.print(s.value + " ");
        }
    }
}
/*
 * Time Complexity: O(n log n)
 * 說明：
 * 1. 建堆過程為 O(n)
 * 2. 每次從堆頂取出元素後重建為 O(log n)，共 n 次，總計 O(n log n)
 */
