
import java.util.*;

public class MergeKSortedArrays {

    static class Element {

        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        // 將每個陣列的第一個元素加入 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            // 如果還有下一個元素，加入 heap
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextIndex = current.elementIndex + 1;
                int nextValue = arrays[current.arrayIndex][nextIndex];
                minHeap.offer(new Element(nextValue, current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][][] testCases = {
            {{1, 4, 5}, {1, 3, 4}, {2, 6}},
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{1}, {0}}
        };

        for (int i = 0; i < testCases.length; i++) {
            List<Integer> merged = mergeKSortedArrays(testCases[i]);
            System.out.println("Test Case " + (i + 1) + " 結果: " + merged);
        }
    }
}
