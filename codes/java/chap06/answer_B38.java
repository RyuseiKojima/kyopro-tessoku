import java.util.*;

class Answer_B38 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		String S = sc.next();

		sc.close();

		int[] L1 = new int[N];
		int streak1 = 1;
		L1[0] = 1;
		for (int i = 0; i < N - 1; i++) {
			if (S.charAt(i) == 'A') {
				streak1++;
			} else {
				streak1 = 1;
			}
			L1[i + 1] = streak1;
		}

		int[] L2 = new int[N];
		int streak2 = 1;
		L2[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			if (S.charAt(i) == 'B') {
				streak2++;
			} else {
				streak2 = 1;
			}
			L2[i] = streak2;
		}

		int Answer = 0;
		for (int i = 0; i < N; i++) {
			Answer += Math.max(L1[i], L2[i]);
		}
		System.out.println(Answer);
	}
};
