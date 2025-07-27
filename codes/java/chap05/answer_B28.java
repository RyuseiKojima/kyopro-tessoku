import java.util.*;

class Answer_B28 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		long[] A = new long[N + 1];

		sc.close();

		A[1] = 1;
		A[2] = 1;

		// フィボナッチ数列の計算
		for (int i = 3; i <= N; i++) {
			A[i] = A[i - 1] + A[i - 2];
			A[i] %= 1000000007; // 余りをとる
		}

		System.out.println(A[N]);
	}
};
