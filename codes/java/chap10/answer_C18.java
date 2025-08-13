import java.util.*;

class Answer_C18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] A = new int[N * 2 + 1];
        for (int i = 1; i <= 2 * N; i++) {
            A[i] = sc.nextInt();
        }
        sc.close();

        int[][] dp = new int[N * 2 + 1][N * 2 + 1]; // 区間 [l, r] を全て消すのに必要な最小コスト

        for (int i = 1; i <= 2 * N; i++) {
            for (int j = 1; j <= 2 * N; j++) {
                dp[i][j] = 1_000_000_000; // 初期化
            }
        }

        // 動的計画法（初期状態）
        // 長さ 2 の区間（要素2個）なら、ペアにして消すしかない
        for (int i = 1; i <= 2 * N - 1; i++) {
            dp[i][i + 1] = Math.abs(A[i] - A[i + 1]);
        }

        // 動的計画法（遷移）
        for (int LEN = 2; LEN <= 2 * N - 1; LEN++) {
            for (int l = 1; l <= 2 * N - LEN; l++) {
                int r = l + LEN;

                // l 番目と r 番目を消す場合
                // 区間 [l, r] の両端をペアにし、残った中身 [l+1, r-1] の最小コストに加える
                dp[l][r] = Math.min(dp[l][r], dp[l + 1][r - 1] + Math.abs(A[l] - A[r]));

                // 2 つの区間を合成させる場合
                // 区間 [l, r] を2つの部分 [l, m] と [m+1, r] に分割
                for (int m = l; m < r; m++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m + 1][r]);
                }
            }
        }

        System.out.println(dp[1][2 * N]);
    }
}