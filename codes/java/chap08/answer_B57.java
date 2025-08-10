import java.util.*;

class Answer_B57 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		sc.close();

		// 前計算
		// ダブリング
		final int LEVELS = 30;
		int[][] dp = new int[LEVELS][N + 1];
		// 1回目の計算
		for (int i = 1; i <= N; i++) {
			// iからiの各桁の和を引いた値を計算
			int sum = 0;
			int tmp = i;
			while (tmp > 0) {
				sum += tmp % 10;
				tmp /= 10;
			}
			dp[0][i] = i - sum;
		}
		// 2回目以降の計算
		for (int d = 1; d <= 29; d++) {
			for (int i = 1; i <= N; i++) {
				dp[d][i] = dp[d - 1][dp[d - 1][i]];
			}
		}

		// クエリの処理
		for (int i = 1; i <= N; i++) {
			int currentPlace = i;
			for (int d = 29; d >= 0; d--) {
				if ((K & (1 << d)) != 0) {
					currentPlace = dp[d][currentPlace];
				}
			}
			System.out.println(currentPlace);
		}
	}
}