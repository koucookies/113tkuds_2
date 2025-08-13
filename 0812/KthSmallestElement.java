
import java.util.*;

public class KthSmallestElement {

    public static int kthSmallestUsingMaxHeap(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums1 = {7, 10, 4, 3, 20, 15};
        int[] nums2 = {1};
        int[] nums3 = {3, 1, 4, 1, 5, 9, 2, 6};

        System.out.println("第3小: " + kthSmallestUsingMaxHeap(nums1, 3)); // 7
        System.out.println("第1小: " + kthSmallestUsingMaxHeap(nums2, 1)); // 1
        System.out.println("第4小: " + kthSmallestUsingMaxHeap(nums3, 4)); // 3
    }
}
