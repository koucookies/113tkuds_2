
import java.util.*;

public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextLong();
        }
        sc.close();

        System.out.println(maxArea(heights));
    }

    public static long maxArea(long[] heights) {
        int left = 0, right = heights.length - 1;
        long max = 0;

        while (left < right) {
            long h = Math.min(heights[left], heights[right]);
            long width = right - left;
            max = Math.max(max, h * width);

            // 移動較短邊
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
