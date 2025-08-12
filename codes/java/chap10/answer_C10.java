import java.util.*;

class Answer_C10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long W = sc.nextLong();
		sc.close();

		long mod = 1000000007;

		System.out.println(12 * Power(7, W - 1, mod) % mod);
	}

	// a の b 乗を m で割った余りを返す関数
	// 変数 a は a^1 → a^2 → a^4 → a^8 → a^16 → ･･･ と変化
	static long Power(long a, long b, long m) {
		long p = a, Answer = 1;
		for (int i = 0; i < 30; i++) {
			if ((b & (1 << i)) != 0) {
				Answer = (Answer * p) % m; // 「a の 2i 乗」が掛けられるとき
			}
			p = (p * p) % m;
		}
		return Answer;
	}
}