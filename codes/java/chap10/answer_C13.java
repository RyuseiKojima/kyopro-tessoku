import java.util.*;

class Answer_C13 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 入力
		int N = sc.nextInt();
		long P = sc.nextLong();
		long mod = 1000000007;
		long[] A = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = sc.nextLong() % mod;
		}
		sc.close();

        long count = 0;
		HashMap<Long, Long> counts = new HashMap<>();
        for (int i = 1; i <= N; i++) {
			if (A[i] == 0) {
				if (P == 0) count += (i - 1);
			}
			else {
				// A[i]*Goal mod 1000000007 = P を満たす整数が Goal
				long Goal = Division(P, A[i], mod);
				count += counts.getOrDefault(Goal, 0L);
			}
			// A[i] の出現回数を更新
			counts.put(A[i], counts.getOrDefault(A[i], 0L) + 1);
		}
		System.out.println(count);
	}
	// a の b 乗を m で割った余りを返す関数
	// 変数 a は a^1 → a^2 → a^4 → a^8 → a^16 → ･･･ と変化
	static long Power(long a, long b, long m) {
		long p = a, Answer = 1;
		for (int i = 0; i < 60; i++) {
			long wari = (1L << i);
			if ((b / wari) % 2 == 1) {
				Answer = (Answer * p) % m; // 「a の 2i 乗」が掛けられるとき
			}
			p = (p * p) % m;
		}
		return Answer;
	}

	// a ÷ b を m で割った余りを返す関数
	static long Division(long a, long b, long m) {
		return (a * Power(b, m - 2, m)) % m;
	}
}