import java.util.*;

class Answer_C12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 入力
		int N = sc.nextInt();
		M = sc.nextInt();
		int K = sc.nextInt();
		A = new int[M + 1];
		B = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			A[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}
		sc.close();

		// 配列 dp の初期化
		int[][] dp = new int[K + 1][N + 1];
		for (int i = 0; i <= K; i++) {
			for (int j = 0; j <= N; j++) dp[i][j] = -1000000;
		}

		// 動的計画法（貰う遷移形式）
		dp[0][0] = 0;
		for (int i = 1; i <= K; i++) { // 賞数
			for (int j = 1; j <= N; j++) { // i賞で jページまで
				// k は「前の章がどのページで終わったか」
				for (int k = 0; k <= j - 1; k++) {// 前の章でkページまで
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + tsunagari(k + 1, j));
				}
			}
		}

		// 出力
		System.out.println(dp[K][N]);
	}

	static int M;
	static int[] A, B;

	// l ページ目から r ページ目までの間に、何個のつながりがあるか？
	static int tsunagari(int l, int r) {
		int cnt = 0;
		for (int i = 1; i <= M; i++) {
			if (l <= A[i] && B[i] <= r) cnt++;
		}
		return cnt;
	}
}