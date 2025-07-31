import java.util.*;

class Answer_B40 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[] A = new int[100];
		for (int i = 1; i <= N; i++) A[sc.nextInt() % 100]++;

		sc.close();

		// 個数を数える
		long cnt = 0;
		for (int i = 1; i < 50; i++) cnt += A[i] * A[100 - i];
		cnt += A[0] * (A[0] - 1) / 2;
		cnt += A[50] * (A[50] - 1) / 2;

		System.out.println(cnt);
	}
};
