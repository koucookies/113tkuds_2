
import java.util.*;

public class LC17_PhoneCombos_CSShift {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.hasNextLine() ? sc.nextLine().trim() : "";
        sc.close();

        if (digits.isEmpty()) {
            return; // 空字串 → 不輸出
        }
        String[] map = {
            "abc", // '2'
            "def", // '3'
            "ghi", // '4'
            "jkl", // '5'
            "mno", // '6'
            "pqrs", // '7'
            "tuv", // '8'
            "wxyz" // '9'
        };

        StringBuilder sb = new StringBuilder();
        backtrack(digits, 0, map, sb);
    }

    private static void backtrack(String digits, int idx, String[] map, StringBuilder sb) {
        if (idx == digits.length()) {
            System.out.println(sb.toString());
            return;
        }
        char d = digits.charAt(idx);
        // 題目保證僅含 '2'–'9'；若要防呆，可直接 return 或忽略不在範圍的字元
        String letters = map[d - '2'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            backtrack(digits, idx + 1, map, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
