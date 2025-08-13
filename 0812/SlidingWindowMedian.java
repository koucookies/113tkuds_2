
import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // left (smaller half)
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // right (larger half)
    private Map<Integer, Integer> delayed = new HashMap<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            if (num == maxHeap.peek()) {
                prune(maxHeap); 
            }else {
                balanceHeaps();
            }
        } else {
            if (num == minHeap.peek()) {
                prune(minHeap); 
            }else {
                balanceHeaps();
            }
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int val = heap.poll();
            delayed.put(val, delayed.get(val) - 1);
            if (delayed.get(val) == 0) {
                delayed.remove(val);
            }
        }
    }

    private void balanceHeaps() {
        // Maintain size property
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        prune(maxHeap);
        prune(minHeap);
    }

    private double getMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums1, k1)));

        int[] nums2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("輸出: " + Arrays.toString(swm.medianSlidingWindow(nums2, k2)));
    }
}
