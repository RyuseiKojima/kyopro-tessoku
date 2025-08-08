import java.util.*;

class Answer_B52 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();
		StringBuilder S = new StringBuilder(sc.next());

		sc.close();

		// クエリの処理
		Queue<Integer> T = new LinkedList<>();

		T.add(X - 1);
		// SのX文字目を@に置き換える
		S.setCharAt(X - 1, '@');
		while (!T.isEmpty()) {
			int index = T.poll();
			if (index > 0 && S.charAt(index - 1) == '.') {
				S.setCharAt(index - 1, '@');
				T.add(index - 1);
			}
			if (index < N - 1 && S.charAt(index + 1) == '.') {
				S.setCharAt(index + 1, '@');
				T.add(index + 1);
			}
		}

		System.out.println(S);
	}
}