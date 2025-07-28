import java.util.*;

class Answer_B36 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int K = sc.nextInt();
		String S = sc.next();

		sc.close();

		int count = 0;
		for (int i = 0; i < N; i++) {
			if (S.charAt(i) == '1') {
				count++;
			}
		}

		if (count % 2 == 0 && K % 2 == 0) {
			System.out.println("Yes");
		} else if (count % 2 == 1 && K % 2 == 1) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
};
