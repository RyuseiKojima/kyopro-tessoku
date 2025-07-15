import java.util.*;

class Answer_B09 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int n = sc.nextInt();
		int[][] A = new int[1502][1502];
		int x_max = 0, y_max = 0;
		for (int i = 1; i <= n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			x_max = Math.max(x_max, c);
			y_max = Math.max(y_max, d);
			A[a][b]++;
			A[c][b]--;
			A[a][d]--;
			A[c][d]++;
		}

		sc.close();

		for (int i = 0; i <= x_max+1; i++) {
			for (int j = 1; j <= y_max+1; j++) {
				A[i][j] = A[i][j - 1] + A[i][j];
			}
		}

		for (int i = 0; i <= y_max+1; i++) {
			for (int j = 1; j <= x_max+1; j++) {
				A[j][i] = A[j - 1][i] + A[j][i];
			}
		}

		int count = 0;
		for (int i = 0; i <= x_max; i++) {
			for (int j = 0; j <= y_max; j++) {
				if (A[i][j] > 0) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
