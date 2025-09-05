
import java.io.*;

public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine();
        String needle = br.readLine();
        if (haystack == null) {
            haystack = "";
        }
        if (needle == null) {
            needle = "";
        }

        System.out.println(strStr(haystack, needle));
    }

    // 回傳 needle 首次出現於 haystack 的起始索引；不存在回 -1
    public static int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        if (m == 0) {
            return 0;
        }
        if (m > n) {
            return -1;
        }

        int[] lps = buildLPS(needle);
        int i = 0, j = 0; // i: haystack, j: needle
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - m; // 完整匹配

                            }} else if (j > 0) {
                j = lps[j - 1];         // 失敗函數跳轉
            } else {
                i++;                    // j==0 仍不匹配，i 前進
            }
        }
        return -1;
    }

    // 建立 KMP 的最長相等前後綴表 (LPS)
    private static int[] buildLPS(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0; // 當前最長前後綴長度
        for (int i = 1; i < m;) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
