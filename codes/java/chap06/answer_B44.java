import java.util.*;

class Answer_B44 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[][] A = new int[N + 1][N + 1];
		int[] B = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				A[i][j] = sc.nextInt();
			}
			B[i] = i;
		}

		// 答えを求める	
		int Q = sc.nextInt();

		// クエリの処理
		for (int i = 1; i <= Q; i++) {
			int Type = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();

			if (Type == 1) {
				int temp = B[x];
				B[x] = B[y];
				B[y] = temp;
			} else {
				int key = B[x];
				System.out.println(A[key][y]);
			}
		}

		sc.close();
	}
};
