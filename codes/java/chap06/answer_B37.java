import java.util.*;

class Answer_B37 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		long N = sc.nextLong();

		sc.close();

		// 10のN乗を配列に格納
		long[] Power10 = new long[17];
		Power10[0] = 1;
		for (int i = 1; i <= 16; i++) Power10[i] = 10 * Power10[i - 1];

		// R[i][j] は、N 以下の数の中で、下から i 桁目に j が登場する回数
		long[][] R = new long[18][10];
		for (int i = 0; i <= 15; i++) {
			// 下からi桁目の数字
			long digit = (N / Power10[i]) % 10;
	
			// R[i][j] の値を求める
			for (int j = 0; j < 10; j++) {
				if (j < digit) {
					// jが下からi桁目の数字より小さい場合
					R[i][j] = (N / Power10[i + 1] + 1) * Power10[i];
				}
				if (j == digit) {
					// jが下からi桁目の数字と等しい場合
					R[i][j] = (N / Power10[i + 1]) * Power10[i] + (N % Power10[i]) + 1;
				}
				if (j > digit) {
					// jが下からi桁目の数字より大きい場合
					R[i][j] = (N / Power10[i + 1]) * Power10[i];
				}
			}
		}
 
		// 答えを求める
		long Answer = 0;
		for (int i = 0; i <= 15; i++) {
			for (int j = 0; j < 10; j++) Answer += j * R[i][j];
		}

		// 出力
		System.out.println(Answer);
	}
};
