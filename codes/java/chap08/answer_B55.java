import java.util.*;

class Answer_B55 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int Q = sc.nextInt();

		// クエリの処理
		TreeSet<Integer> S = new TreeSet<>();
		for (int i = 1; i <= Q; i++) {
			int queryType = sc.nextInt();
			if (queryType == 1) {
				S.add(sc.nextInt());
			} else if (queryType == 2) {
				int X = sc.nextInt();
				// Xより大きいもののうち最小の要素res
				Integer res = S.higher(X - 1);
				// Xより小さいもののうち最大の要素res2
				Integer res2 = S.lower(X + 1);

				if (res == null && res2 == null) {
					System.out.println(-1);
				} else if (res == null) {
					System.out.println(Math.abs(X - res2));
				} else if (res2 == null) {
					System.out.println(Math.abs(X - res));
				} else {
					System.out.println(Math.min(Math.abs(X - res), Math.abs(X - res2)));
				}
			}
		}

		sc.close();


	}
}