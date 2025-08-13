
import java.util.*;

public class MeetingRoomScheduler {

    // 子問題 1：計算所需最少會議室數量
    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) {
            return 0;
        }

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            if (!heap.isEmpty() && heap.peek() <= meeting[0]) {
                heap.poll();  // 重用會議室
            }
            heap.offer(meeting[1]);  // 放入新的結束時間
        }

        return heap.size();
    }

    // 子問題 2：限制 N 間會議室下，排最大會議總時間
    public static int maximizeTotalMeetingTime(int[][] meetings, int roomCount) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1])); // 依結束時間排序

        // 儲存每間會議室的最後結束時間
        PriorityQueue<int[]> roomHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        int totalTime = 0;
        for (int[] meeting : meetings) {
            while (!roomHeap.isEmpty() && roomHeap.peek()[1] <= meeting[0]) {
                roomHeap.poll();  // 釋放可以用的會議室
            }

            if (roomHeap.size() < roomCount) {
                totalTime += meeting[1] - meeting[0];
                roomHeap.offer(meeting); // 安排會議
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] test2 = {{9, 10}, {4, 9}, {4, 17}};
        int[][] test3 = {{1, 5}, {8, 9}, {8, 9}};
        int[][] test4 = {{1, 4}, {2, 3}, {4, 6}};

        System.out.println("Test1 需要會議室: " + minMeetingRooms(test1)); // 2
        System.out.println("Test2 需要會議室: " + minMeetingRooms(test2)); // 2
        System.out.println("Test3 需要會議室: " + minMeetingRooms(test3)); // 2

        System.out.println("Test4 1間會議室時最大會議總時間: " + maximizeTotalMeetingTime(test4, 1)); // 5
    }
}
