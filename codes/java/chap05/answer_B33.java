import java.util.*;

class Answer_B33 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		sc.nextInt();
		sc.nextInt();
		int[] A = new int[N];
		int[] B = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}

		sc.close();

		// ニム和問題
		int XOR_Sum = 0;
		for (int i = 0; i < N; i++) {
			XOR_Sum = (XOR_Sum ^ (A[i]-1));
			XOR_Sum = (XOR_Sum ^ (B[i]-1));
		}

		// 出力
		if (XOR_Sum != 0) System.out.println("First");
		else System.out.println("Second");
	}
};
