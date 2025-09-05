
import java.util.*;

public class LC40_CombinationSum2_Procurement {

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

    // 每個數只能用一次；同層跳過重複值
    private static void backtrack(int start, int sum, List<Integer> path) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }

        for (int i = start; i < cand.length; i++) {
            if (i > start && cand[i] == cand[i - 1]) {
                continue; // 同層去重

            }
            int x = cand[i];
            if (sum + x > target) {
                break; // 因為已排序，可以剪枝

            }
            path.add(x);
            backtrack(i + 1, sum + x, path); // i+1：每項只用一次
            path.remove(path.size() - 1);
        }
    }
}
ㄋㄋ
