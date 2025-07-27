import java.util.*;

class Answer_B26 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();

		sc.close();

		boolean[] deleted = new boolean[N + 1];

		// エラトステネスのふるい
		for (int i = 2; i * i <= N; i++) {
			if (deleted[i]) continue;
			for (int j = i * i; j <= N; j += i) {
				deleted[j] = true;
			}
		}

		for (int i = 2; i <= N; i++) {
			if (!deleted[i]) System.out.println(i);
		}
	}
};
