import java.util.*;

class Answer_B14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 半分全列挙
		// 入力
		int N = sc.nextInt();
		int K = sc.nextInt();
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();


		for (int x = 1; x <= N; x++) {
			if (x % 2 == 0) {
				A.add(sc.nextInt());
			} else {
				B.add(sc.nextInt());
			}
		}

		sc.close();

		int A_size = A.size();
		int B_size = B.size();

		// 配列 P を作成
		ArrayList<Integer> P = new ArrayList<>();
        // 2^n通りの部分集合をビット列で表現
        for (int bit = 0; bit < (1 << A_size); bit++) {
            int sum = 0;
            for (int i = 0; i < A_size; i++) {
                if ((bit & (1 << i)) != 0) {
                    sum += A.get(i);
                }
            }
            P.add(sum);
        }

		// 配列 Q を作成
		ArrayList<Integer> Q = new ArrayList<>();
        // 2^n通りの部分集合をビット列で表現
        for (int bit = 0; bit < (1 << B_size); bit++) {
            int sum = 0;
            for (int i = 0; i < B_size; i++) {
                if ((bit & (1 << i)) != 0) {
                    sum += B.get(i);
                }
            }
            Q.add(sum);
        }

		int P_size = P.size();
		int Q_size = Q.size();

		// 配列 Q を小さい順にソート
		Collections.sort(Q);

		// 二分探索（配列 P, Q が 0 番目から始まることに注意！）
		for (int i = 0; i < P_size; i++) {
			int pos1 = ~Collections.binarySearch(Q, K - P.get(i), (x, y) -> x.compareTo(y) >= 0 ? 1 : -1);
			if (pos1 < Q_size && Q.get(pos1) == K - P.get(i)) {
				System.out.println("Yes");
				System.exit(0);
			}
		}

		// 見つからなかった場合
		System.out.println("No");
	}
};
