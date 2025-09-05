
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // Map<字元, 上次出現的位置>
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, ans = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                // 更新左界：取最大值避免回退
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            ans = Math.max(ans, right - left + 1);
        }

        System.out.println(ans);
        sc.close();
    }
}
