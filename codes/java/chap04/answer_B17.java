import java.util.*;

class Answer_B17 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[] H = new int[N + 1];
		for (int i = 1; i <= N; i++) H[i] = sc.nextInt();

		sc.close();

		// 動的計画法
		int[] dp = new int[N + 1];
		dp[1] = 0;
		dp[2] = Math.abs(H[2] - H[1]);
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.min(dp[i - 1] + Math.abs(H[i] - H[i - 1]), dp[i - 2] + Math.abs(H[i] - H[i - 2]));
		}

		ArrayList<Integer> path = new ArrayList<>();
		int current = N;
		while (current > 1) {
			path.add(current);
			if (current == 2 || dp[current] - dp[current - 1] == Math.abs(H[current] - H[current - 1])) {
				current--;
			} else {
				current -= 2;
			}
		}
		path.add(1);
		Collections.reverse(path);

		System.out.println(path.size());

		for (int p : path) {
			System.out.print(p + " ");
		}
		System.out.println();
	}
};
