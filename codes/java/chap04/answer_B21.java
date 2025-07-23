import java.util.*;

class Answer_B21 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		String S = sc.next();

		sc.close();

		int[][] dp = new int[N + 1][N + 1];

		// 動的計画法（初期状態）
		for (int i = 0; i < N; i++) dp[i][i] = 1;
		for (int i = 0; i < N - 1; i++) {
			if (S.charAt(i) == S.charAt(i + 1)) dp[i][i + 1] = 2;
			else dp[i][i + 1] = 1;
		}

		// 動的計画法（状態遷移）
		for (int LEN = 2; LEN <= N - 1; LEN++) {
			for (int l = 0; l < N - LEN; l++) {
				int r = l + LEN;

				if (S.charAt(l) == S.charAt(r)) {
					dp[l][r] = Math.max(Math.max(dp[l][r - 1], dp[l + 1][r]), dp[l + 1][r - 1] + 2);
				}
				else {
					dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
				}
			}
		}
	
		// 答えを求める
		System.out.println(dp[0][N - 1]);
	}
};
