
import java.util.*;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] incomes = new int[n];
        int totalTax = 0;

        for (int i = 0; i < n; i++) {
            incomes[i] = sc.nextInt();
        }

        for (int income : incomes) {
            int tax = computeTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }

        int average = totalTax / n;
        System.out.println("Average: " + average);
    }

    static int computeTax(int income) {
        int tax = 0;

        if (income <= 120000) {
            tax += (int) (income * 0.05);
        } else if (income <= 500000) {
            tax += (int) (120000 * 0.05);
            tax += (int) ((income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax += (int) (120000 * 0.05);
            tax += (int) (380000 * 0.12);
            tax += (int) ((income - 500000) * 0.20);
        } else {
            tax += (int) (120000 * 0.05);
            tax += (int) (380000 * 0.12);
            tax += (int) (500000 * 0.20);
            tax += (int) ((income - 1000000) * 0.30);
        }

        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 輸入讀取 n 筆收入資料為 O(n)
 * - 每筆呼叫 computeTax 時為 O(1)，因為分段計算為固定步驟
 * - 總體執行時間為 O(n)，線性於輸入筆數
 */
