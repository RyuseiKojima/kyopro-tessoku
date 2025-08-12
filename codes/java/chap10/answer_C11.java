import java.util.*;

class Answer_C11 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int K = sc.nextInt();
		A = new int[N + 1];
		for (int i = 1; i <= N; i++) A[i] = sc.nextInt();
		sc.close();

		double Left = 1, Right = 1000000000, Mid;
		double Border = 0; // 現在のボーダー（合計議席数が K 以上となった最大の値）
		for (int i = 1; i <= 60; i++) {
			Mid = (Left + Right) / 2.0;

			// 割り算の値は Mid より大きいか？
			long val = check(Mid);
			if (val >= K) {
				Left = Mid;
				Border = Math.max(Border, Mid);
			}
			else {
				Right = Mid;
			}
		}

		// 出力
		for (int i = 1; i <= N; i++) {
			System.out.print((long)(A[i] / Border) + " ");
		}
		System.out.println();
	}

	static int N;
	static int[] A;

	// 割り算の値が x であるときの議席数は？
	static long check(double x) {
		long sum = 0;
		for (int i = 1; i <= N; i++) sum += (long)(A[i] / x);
		return sum;
	}
}