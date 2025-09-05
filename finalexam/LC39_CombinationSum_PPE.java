
import java.util.*;

public class LC39_CombinationSum_PPE {

    static List<List<Integer>> ans = new ArrayList<>();
    static int[] cand;
    static int target;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        target = sc.nextInt();
        cand = new int[n];
        for (int i = 0; i < n; i++) {
            cand[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(cand);
        backtrack(0, 0, new ArrayList<>());

        for (List<Integer> comb : ans) {
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(comb.get(i));
            }
            System.out.println();
        }
    }

    // 可重複使用：下一層仍從 i 開始
    private static void backtrack(int i, int sum, List<Integer> path) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (i == cand.length || sum > target) {
            return;
        }

        // 選 cand[i]（可重複）
        path.add(cand[i]);
        backtrack(i, sum + cand[i], path);
        path.remove(path.size() - 1);

        // 不選 cand[i]，移至下一個
        backtrack(i + 1, sum, path);
    }
}
