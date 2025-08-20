
import java.util.Scanner;

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd = gcd(a, b);
        long lcm = a / gcd * b;  // 避免溢位：先除後乘

        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }

    // 遞迴歐幾里得演算法
    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：
 * - 歐幾里得演算法每次遞迴都會將較小數減半或更快收斂
 * - 最壞情況約需 log(min(a, b)) 次遞迴
 * - LCM 計算為 O(1)，僅包含一次除法與一次乘法
 */
