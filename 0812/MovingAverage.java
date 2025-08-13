
import java.util.*;

public class MovingAverage {

    private final int size;
    private final Queue<Integer> window;
    private long sum;

    private final PriorityQueue<Integer> maxHeap; // 左邊較小那半
    private final PriorityQueue<Integer> minHeap; // 右邊較大那半

    private final TreeMap<Integer, Integer> countMap; // 支援 getMin()/getMax()

    public MovingAverage(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.countMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addNumber(val);

        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeNumber(removed);

            // 從 countMap 移除
            if (countMap.get(removed) == 1) {
                countMap.remove(removed);
            } else {
                countMap.put(removed, countMap.get(removed) - 1);
            }
        }

        return sum * 1.0 / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return countMap.firstKey();
    }

    public int getMax() {
        return countMap.lastKey();
    }

    // 將新數字加入 median heap
    private void addNumber(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        balanceHeaps();
    }

    // 將數字從 heap 中移除
    private void removeNumber(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num); // 注意 PriorityQueue 移除 O(n)
        } else {
            minHeap.remove(num);
        }

        balanceHeaps();
    }

    // 維持兩邊 heap 平衡
    private void balanceHeaps() {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1));  // 1.0
        System.out.println(ma.next(10)); // 5.5
        System.out.println(ma.next(3));  // 4.67
        System.out.println(ma.next(5));  // 6.0

        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}
