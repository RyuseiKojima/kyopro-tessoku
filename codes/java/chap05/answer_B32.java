import java.util.*;

class Answer_B32 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] A = new int[K];
		for (int i = 0; i < K; i++) A[i] = sc.nextInt();

		sc.close();

		// 勝者を計算する
		boolean[] dp = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < K; j++) {
				if (i >= A[j] && !dp[i - A[j]]) {
					dp[i] = true; // 勝ちの状態
					break;
				}
			}
		}

		// 出力
		if (dp[N] == true) System.out.println("First");
		else System.out.println("Second");
	}
};
