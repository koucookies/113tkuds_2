
public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 左子節點
            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反 Max Heap 規則的節點索引：" + left);
                return false;
            }

            // 右子節點
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反 Max Heap 規則的節點索引：" + right);
                return false;
            }
        }
        return true;
    }

    // 測試主程式
    public static void main(String[] args) {
        int[][] testCases = {
            {100, 90, 80, 70, 60, 75, 65}, // ✅ true
            {100, 90, 80, 95, 60, 75, 65}, // ❌ false
            {50}, // ✅ true
            {} // ✅ true
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("測試案例 " + (i + 1) + "：");
            boolean result = isValidMaxHeap(testCases[i]);
            System.out.println("是否為 Max Heap？" + result);
            System.out.println();
        }
    }
}
