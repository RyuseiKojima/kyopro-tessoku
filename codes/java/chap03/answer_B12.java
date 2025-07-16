import java.util.*;

class Answer_B12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		double N_min = N - 0.01f;
		double N_max = N + 0.01f;
		double L = 0;
		double R = (double) 1e9;
		
		double M = (L + R) / 2;
		while (L <= R) {
			double check = M + Math.pow(M, 3);
			if (check >= N_min && check <= N_max) {
				System.out.println(M);
				break;
			}
			if (check < N_min) {
				L = M;
			} else {
				R = M;
			}
			M = (L + R) / 2;
		}
		sc.close();
	}
};
