
import java.util.*;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = toMinutes(sc.nextLine());
        }
        int queryTime = toMinutes(sc.nextLine());

        int result = binarySearch(times, queryTime);

        if (result == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(toHHmm(times[result]));
        }
    }

    // 將 HH:mm 轉成 0 ~ 1439 的分鐘
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 將分鐘轉回 HH:mm 格式
    private static String toHHmm(int totalMinutes) {
        int h = totalMinutes / 60;
        int m = totalMinutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    // 找到第一個大於查詢時間的 index，找不到則回傳 -1
    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
