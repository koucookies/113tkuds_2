
import java.util.*;

public class LC18_4Sum_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(nums);
        List<int[]> ans = new ArrayList<>();

        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重 i
            }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 去重 j
                }
                int L = j + 1, R = len - 1;
                while (L < R) {
                    long sum = (long) nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        ans.add(new int[]{nums[i], nums[j], nums[L], nums[R]});
                        // 內層去重 L, R
                        int lVal = nums[L], rVal = nums[R];
                        while (L < R && nums[L] == lVal) {
                            L++;
                        }
                        while (L < R && nums[R] == rVal) {
                            R--;
                        }
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        for (int[] q : ans) {
            System.out.println(q[0] + " " + q[1] + " " + q[2] + " " + q[3]);
        }
    }
}
