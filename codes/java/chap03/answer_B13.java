import java.util.*;

class Answer_B13 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int K = sc.nextInt();
		long[] A = new long[N + 1];
		for (int i = 1; i <= N; i++) A[i] = sc.nextInt();

		int[] R = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] += A[i - 1];
		}
		for (int i = 1; i <= N; i++) {
			if (i == 1) R[i] = 0;
			else R[i] = R[i - 1];
			while (R[i] < N && A[R[i] + 1] - A[i - 1] <= K) {
				R[i]++;
			}
		}
		long count = 0;
		for (int i = 1; i <= N; i++) {
			count += R[i] - i + 1;
		}
		System.out.println(count);

		sc.close();
	}
};
