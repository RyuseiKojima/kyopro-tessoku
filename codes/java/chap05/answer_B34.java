import java.util.*;

class Answer_B34 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		sc.nextInt();
		sc.nextInt();
		long[] A = new long[N + 1];
		for (int i = 1; i <= N; i++) A[i] = sc.nextLong();

		sc.close();
		// Grundy 数を計算
		int XOR_Sum = 0;
		// A[i] % 5 の値に応じて Grundy 数を決定
		for (int i = 1; i <= N; i++) {
			if (A[i] % 5 == 0 || A[i] % 5 == 1) XOR_Sum ^= 0;
			if (A[i] % 5 == 2 || A[i] % 5 == 3) XOR_Sum ^= 1;
			if (A[i] % 5 == 4) XOR_Sum ^= 2;
		}
		// 出力
		if (XOR_Sum != 0) System.out.println("First");
		if (XOR_Sum == 0) System.out.println("Second");
	}
};
