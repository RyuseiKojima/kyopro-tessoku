import java.util.*;

class Answer_B22 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[] A = new int[N + 5];
		int[] B = new int[N + 5];
		for (int i = 2; i <= N; i++) A[i] = sc.nextInt();
		for (int i = 3; i <= N; i++) B[i] = sc.nextInt();

		sc.close();

		// 動的計画法
		int[] dp = new int[N + 5];
		dp[1] = 0;
		for (int i = 2; i <= N + 1; i++) dp[i] = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			dp[i + 1] = Math.min(dp[i] + A[i + 1], dp[i + 1]);
			dp[i + 2] = Math.min(dp[i] + B[i + 2], dp[i + 2]);
		}

		// 出力
		System.out.println(dp[N]);
	}
};
