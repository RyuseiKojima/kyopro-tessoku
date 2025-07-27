import java.util.*;

class Answer_B27 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		long A = sc.nextLong();
		long B = sc.nextLong();

		sc.close();

		// 出力
		System.out.println((A * B) / GCD(A, B));
	}

	// 正の整数 A と B の最大公約数を返す関数
	// GCD は Greatest Common Divisor（最大公約数）の略
	static long GCD(long A, long B) {
		while (A >= 1 && B >= 1) {
			if (A >= B) {
				A %= B; // A の値を変更する場合
			}
			else {
				B %= A; // B の値を変更する場合
			}
		}
		if (A >= 1) {
			return A;
		}
		return B;
	}
};
