import java.util.*;

class Answer_C07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] C = new int[N];
		for (int i = 0; i < N; i++) {
			C[i] = sc.nextInt();
		}
		int Q = sc.nextInt();
		int[] X = new int[Q];
		for (int i = 0; i < Q; i++) {
			X[i] = sc.nextInt();
		}

		sc.close();

		Arrays.sort(C);
		for (int i = 1; i < N; i++) {
			C[i] += C[i - 1];
		}

		for (int i = 0; i < Q; i++) {
			// X[i]の位置をCの中で探す
			int pos = Arrays.binarySearch(C, X[i] + 1);
			if (pos < 0) pos = -pos - 1;
			System.out.println(pos);
		}
	}
}