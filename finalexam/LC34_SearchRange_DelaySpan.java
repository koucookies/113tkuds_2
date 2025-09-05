
import java.util.*;

public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        int left = lowerBound(a, target);
        if (left == n || a[left] != target) {
            System.out.println("-1 -1");
            return;
        }
        int right = upperBound(a, target) - 1;
        System.out.println(left + " " + right);
    }

    // 回傳第一個 >= target 的索引；若全都小於 target，回 n
    private static int lowerBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (a[mid] >= target) {
                r = mid; 
            }else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 回傳第一個 > target 的索引；若全都 <= target，回 n
    private static int upperBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (a[mid] > target) {
                r = mid; 
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
}
ㄋㄋ
