import java.util.*;

class Answer_B23 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[] X = new int[N];
		int[] Y = new int[N];
		for (int i = 0; i < N; i++) {
			X[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}

		double[][] dp = new double[1 << N][N];
		// 配列 dp の初期化
		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) dp[i][j] = 1e9;
		}

		// 動的計画法（dp[通った都市][今いる都市] となっている）
		dp[0][0] = 0;
		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] >= 1e9) continue;
	
				// 都市 j から都市 k に移動したい！
				for (int k = 0; k < N; k++) {
					// 既に都市 k を通っていた場合
					if ((i & (1 << k)) != 0) continue;
	
					// 状態遷移
					double DIST = Math.sqrt(Math.pow(X[j] - X[k], 2) + Math.pow(Y[j] - Y[k], 2));
					dp[i + (1 << k)][k] = Math.min(dp[i + (1 << k)][k], dp[i][j] + DIST);
				}
			}
		}

		System.out.println(dp[(1 << N) - 1][0]);

		sc.close();
	}
};
