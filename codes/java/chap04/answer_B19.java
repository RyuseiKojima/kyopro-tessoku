import java.util.*;

class Answer_B19 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		long W = sc.nextLong();
		long[] w = new long[N + 1];
		int[] v = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			w[i] = sc.nextLong();
			v[i] = sc.nextInt();
		}

		sc.close();

		// 配列 dp の定義・初期化
		long[][] dp = new long[N + 1][1000*100 + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i],  (long)1e15);
		}

		// 動的計画法
		dp[0][0] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= 1000*100; j++) {
				if (j < v[i]) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
			}
		}

		// 出力
		int Answer = 0;
			for (int j = 0; j <= 1000*100; j++) {
				if (dp[N][j] <= W) Answer = j;
			}
		System.out.println(Answer);
	}
};
