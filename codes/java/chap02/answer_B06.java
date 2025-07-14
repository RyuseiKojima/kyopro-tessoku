import java.util.*;

class Answer_B06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int win = 0;
		int lose = 0;
		int n = sc.nextInt();
		int[] win_counts = new int[n + 1];
		int[] lose_counts = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int result = sc.nextInt();
			if (result == 1) {
				win++;
			} else if (result == 0) {
				lose++;
				win_counts[i] = win;
				lose_counts[i] = lose;
			}
			win_counts[i] = win;
			lose_counts[i] = lose;
		}

		int q = sc.nextInt();

		for (int j = 1; j <= q; j++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			if (win_counts[r] - win_counts[l - 1] > lose_counts[r] - lose_counts[l - 1]) {
				System.out.println("win");
			} else if (win_counts[r] - win_counts[l - 1] < lose_counts[r] - lose_counts[l - 1]) {
				System.out.println("lose");
			} else {
				System.out.println("draw");
			}
		}

		sc.close();
	}
};
