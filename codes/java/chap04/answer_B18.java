import java.util.*;

class Answer_B18 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int S = sc.nextInt();
		int[] A = new int[N + 1];
		for (int i = 1; i <= N; i++) A[i] = sc.nextInt();

		sc.close();

		// 配列 dp の定義
		boolean[][] dp = new boolean[N + 1][S + 1];

		// 動的計画法（i = 0）
		dp[0][0] = true;
		for (int i = 1; i <= S; i++) dp[0][i] = false;

		// 動的計画法（i >= 1）
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= S; j++) {
				if (j < A[i]) {
					if (dp[i - 1][j] == true) dp[i][j] = true;
					else dp[i][j] = false;
				}
				if (j >= A[i]) {
					if (dp[i - 1][j] == true || dp[i - 1][j - A[i]] == true) dp[i][j] = true;
					else dp[i][j] = false;
				}
			}
		}

		// 出力
		if (dp[N][S] != true) {
			System.out.println(-1);
			return;
		}
		
		ArrayList<Integer> Answer = new ArrayList<Integer>();
		int Place = N;
		while (Place > 0) {
			if (dp[Place][S] != dp[Place - 1][S]) {
				Answer.add(Place);
				S -= A[Place];
			}
			Place--;
		}
		Collections.reverse(Answer);
		System.out.println(Answer.size());
		for (int a : Answer) System.out.print(a + " ");
		System.out.println();
	}
};
