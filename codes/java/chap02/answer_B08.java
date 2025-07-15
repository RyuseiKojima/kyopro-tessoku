import java.util.*;

class Answer_B08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int n = sc.nextInt();
		int[][] A = new int[1501][1501];
		for (int i = 1; i <= n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			A[x][y]++;
		}

		for (int i = 1; i <= 1500; i++) {
			for (int j = 1; j <= 1500; j++) {
				A[i][j] += A[i][j - 1];
			}
		}

		for (int i = 1; i <= 1500; i++) {
			for (int j = 1; j <= 1500; j++) {
				A[j][i] += A[j - 1][i];
			}
		}

		int q = sc.nextInt();
		for (int i = 1; i <= q; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int result = A[x2][y2] - A[x1 - 1][y2] - A[x2][y1 - 1] + A[x1 - 1][y1 - 1];
			System.out.println(result);
		}

		sc.close();
	}
}
