import java.util.*;

class Answer_C02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		sc.close();
		int answer = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				answer = Math.max(answer, A[i] + A[j]);
			}
		}
		System.out.println(answer);
	}
}