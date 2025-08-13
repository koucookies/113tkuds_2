
import java.util.*;

public class PriorityQueueWithHeap {

    static class Task {

        String name;
        int priority;
        long timestamp; // 用來處理相同優先級的情況

        public Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + "(priority=" + priority + ")";
        }
    }

    static class TaskComparator implements Comparator<Task> {

        public int compare(Task a, Task b) {
            if (b.priority != a.priority) {
                return b.priority - a.priority; // 大的優先
            } else {
                return Long.compare(a.timestamp, b.timestamp); // 較早加入的先處理
            }
        }
    }

    private PriorityQueue<Task> heap;
    private Map<String, Task> taskMap;
    private long timestampCounter;

    public PriorityQueueWithHeap() {
        heap = new PriorityQueue<>(new TaskComparator());
        taskMap = new HashMap<>();
        timestampCounter = 0;
    }

    public void addTask(String name, int priority) {
        Task task = new Task(name, priority, timestampCounter++);
        heap.offer(task);
        taskMap.put(name, task);
    }

    public Task executeNext() {
        Task task = heap.poll();
        if (task != null) {
            taskMap.remove(task.name);
        }
        return task;
    }

    public Task peek() {
        return heap.peek();
    }

    public void changePriority(String name, int newPriority) {
        Task oldTask = taskMap.get(name);
        if (oldTask != null) {
            heap.remove(oldTask); // PriorityQueue 沒有更新功能，只能移除再加入
            Task newTask = new Task(name, newPriority, timestampCounter++);
            heap.offer(newTask);
            taskMap.put(name, newTask);
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // 測試主程式
    public static void main(String[] args) {
        PriorityQueueWithHeap queue = new PriorityQueueWithHeap();

        queue.addTask("備份", 1);
        queue.addTask("緊急修復", 5);
        queue.addTask("更新", 3);

        System.out.println("下一個任務：" + queue.peek());
        System.out.println("執行任務：" + queue.executeNext());
        System.out.println("執行任務：" + queue.executeNext());

        queue.addTask("重新啟動", 2);
        queue.changePriority("備份", 6); // 變成最高

        System.out.println("執行任務：" + queue.executeNext());
        System.out.println("執行任務：" + queue.executeNext());
    }
}
