import java.util.*;

class Answer_C03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int D = sc.nextInt();
		int[] A = new int[D + 1];
		A[1] = sc.nextInt();
		for (int i = 2; i <= D; i++) {
			A[i] = A[i - 1] + sc.nextInt();
		}
		int Q = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int S = sc.nextInt();
			int T = sc.nextInt();
			if (A[S] > A[T]) {
				sb.append(S).append("\n");
			} else if (A[S] < A[T]) {
				sb.append(T).append("\n");
			} else {
				sb.append("Same").append("\n");
			}
		}
		sc.close();
		System.out.print(sb.toString());
	}
}