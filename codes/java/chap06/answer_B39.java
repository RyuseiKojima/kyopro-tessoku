import java.util.*;

class Answer_B39 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int D = sc.nextInt();
		int[] X = new int[N+1];
		int[] Y = new int[N+1];
		boolean[] used = new boolean[N+1]; // used[i] は仕事 i を選んだかどうか
		long Answer = 0;
		for (int i = 1; i <= N; i++) {
			X[i] = sc.nextInt(); // 仕事 i の必要日数
			Y[i] = sc.nextInt(); // 仕事 i の給料
		}

		sc.close();

		// 答えを求める
		for (int i = 1; i <= D; i++) {
			int maxValue = 0; // 給料の最大値
			int maxID = -1;   // 給料が最大となる仕事の番号
			for (int j = 1; j <= N; j++) {
				if (used[j] == true) continue;
				// まだ選んでいない仕事 j があり、かつその仕事を選ぶことができる場合
				if (maxValue < Y[j] && X[j] <= i) {
					maxValue = Y[j];
					maxID = j;
				}
			}
	
			// 選べる仕事がある場合
			if (maxID != -1) {
				Answer += maxValue;
				used[maxID] = true;
			}
		}

		// 出力
		System.out.println(Answer);
	}
};
