import java.util.*;

class answer_B43 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] students = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			students[i] = M;
		}

		for (int i = 1; i <= M; i++) {
			int student = sc.nextInt();
			students[student]--;
		}

		sc.close();

		for (int i = 1; i <= N; i++) {
			System.out.println(students[i]);
		}
	}
};
