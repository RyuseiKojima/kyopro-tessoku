import java.util.*;

class Answer_B56 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int Q = sc.nextInt();
		String S = sc.next();
		int[] L = new int[Q + 1];
		int[] R = new int[Q + 1];
		for (int i = 1; i <= Q; i++) {
			L[i] = sc.nextInt();
			R[i] = sc.nextInt();
		}

		sc.close();

		// Sの文字列を反転
		String S_rev = new StringBuilder(S).reverse().toString();

		// 文字列のハッシュの準備
		StringHash Z = new StringHash(S);
		StringHash Z_rev = new StringHash(S_rev);

		// クエリに答える
		for (int i = 1; i <= Q; i++) {
			long hash1 = Z.hashValue(L[i], R[i]);
			long hash2 = Z_rev.hashValue(N - R[i] + 1, N - L[i] + 1);
			if (hash1 == hash2) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}

	// 文字列のハッシュを実装したクラス StringHash
	static class StringHash {
		static final int MOD = 2147483647;
		long[] power100;
		long[] h;
		StringHash(String S) {
			int N = S.length();
			// 文字列を数値に変換
			int[] T = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				T[i] = (int)(S.charAt(i - 1) - 'a') + 1;
			}
			// 100 の n 乗（本文中の B^0, B^1, ... に対応）を前計算する
			power100 = new long[N + 1];
			power100[0] = 1;
			for (int i = 1; i <= N; i++) {
				power100[i] = power100[i - 1] * 100 % MOD;
			}
			// H[1], H[2], ..., H[N] を前計算する
			h = new long[N + 1];
			h[0] = 1;
			for (int i = 1; i <= N; i++) {
				h[i] = (h[i - 1] * 100 + T[i]) % MOD;
			}
		}
		// S[l, r] のハッシュ値を返す関数
		long hashValue(int l, int r) {
			long val = h[r] - h[l - 1] * power100[r - l + 1] % MOD;
			if (val < 0) {
				val += MOD;
			}
			return val;
		}
	}
}