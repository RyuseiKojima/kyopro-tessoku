import java.util.*;

class Answer_B42 {
	static int N;
	static long[] A;
	static long[] B;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		N = sc.nextInt();
		// A[i] は i 番目のカードの表の値、B[i] は i 番目のカードの裏の値
		A = new long[N + 1];
		B = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = sc.nextLong();
			B[i] = sc.nextLong();
		}

		sc.close();

		// 各パターンの計算
		// omote=1 のとき表の総和が正、ura=1 のとき裏の総和が正
		// omote=2 のとき表の総和が負、ura=2 のとき裏の総和が負
		// 4通りのパターンを考慮して最大値を求める
		long Answer1 = solve(1, 1);
		long Answer2 = solve(2, 1);
		long Answer3 = solve(1, 2);
		long Answer4 = solve(2, 2);

		// 出力
		System.out.println(Math.max(Answer1, Math.max(Answer2, Math.max(Answer3, Answer4))));
	}
	/**
	 * カードの表と裏の状態を考慮して、合計値を計算するメソッド
	 * @param omote 表の状態 (1: 正, 2: 負)
	 * @param ura 裏の状態 (1: 正, 2: 負)
	 * @return 合計値
	 */
	static long solve(int omote, int ura) {
		long ans = 0;
		for (int i = 1; i <= N; i++) {
			// 表と裏の状態に応じてカードの値を決定
			long card1 = A[i]; if (omote == 2) card1 = -A[i];
			long card2 = B[i]; if (ura == 2) card2 = -B[i];
			if (card1 + card2 >= 0) {
				ans += (card1 + card2);
			}
		}
		return ans;
	}
};
