import java.util.*;

class Answer_C09 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();

		long[] dp = new long[N + 1];
		dp[1] = A[1];
		dp[2] = Math.max(A[1], A[2]);

		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i]);
		}

		System.out.println(dp[N]);
	}
}