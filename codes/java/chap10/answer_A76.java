import java.util.*;

class Answer_A76 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 足場の数
		int W = sc.nextInt(); // 川の幅
		int L = sc.nextInt(); // 最小跳躍距離
		int R = sc.nextInt(); // 最大跳躍距離
		int[] X = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			X[i] = sc.nextInt();
		}

		sc.close();

		// 西岸を足場 0、東岸を足場 N+1 とみなす
		X[0] = 0;
		X[N + 1] = W;

		// 動的計画法
		final int MOD = 1000000007;
		int[] dp = new int[N + 2];
		int[] sum = new int[N + 2];
		dp[0] = 1;
		sum[0] = 1;
		for (int i = 1; i <= N + 1; i++) {
			int posL = lowerBound(X, X[i] - R); // 左端の位置
			int posR = lowerBound(X, X[i] - L + 1) - 1; // 右端の位置
			// dp[i] の値を累積和で計算
			dp[i] += (posR >= 0 ? sum[posR] : 0);
			dp[i] -= (posL >= 1 ? sum[posL - 1] : 0);
			dp[i] = (dp[i] + MOD) % MOD; // 負の値を防ぐために MOD を加える
			// 累積和 sum[i] を更新
			sum[i] = sum[i - 1] + dp[i];
			sum[i] %= MOD;
		}

		// 出力
		System.out.println(dp[N + 1]);
	}

	// ソートされた配列 A[0], A[1], ..., A[N-1] に対して、A[i] >= X となる最小の i を求める関数
	static int lowerBound(int[] A, int X) {
		int l = -1, r = A.length;
		while (r - l > 1) {
			int m = (l + r) / 2;
			if (A[m] >= X) {
				r = m;
			}
			else {
				l = m;
			}
		}
		return r;
	}
}