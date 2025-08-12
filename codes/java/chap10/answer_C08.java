import java.util.*;

class Answer_C08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] S = new String[N + 1];
		int[] T = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			S[i] = sc.next();
			T[i] = sc.nextInt();
		}
		sc.close();

		ArrayList<String> Answer = new ArrayList<>();
		for (int num = 0; num <= 9999; num++) {
			// 整数 num を 4 桁の文字列に置き換え
			String ID = String.format("%04d", num);

			// すべての情報が正しいかどうかを確認
			boolean flag = true;
			for (int i = 1; i <= N; i++) {
				if (Hantei(S[i], ID) != T[i]) {
					flag = false;
				}
			}

			// もしすべての情報が正しかった場合
			if (flag == true) {
				Answer.add(ID);
			}
		}

		// 出力
		if (Answer.size() != 1) {
			System.out.println("Can't Solve");
		}
		else {
			System.out.println(Answer.get(0));
		}
	}

	/**
	 * 文字列 S と ID の一致度を判定する
	 * @param S 文字列
	 * @param ID 一致度を判定する ID
	 * @return 一致度
	 */
	static int Hantei(String S, String ID) {
		int diff = 0;
		for (int i = 0; i < 4; i++) {
			if (S.charAt(i) != ID.charAt(i)) diff++;
		}
		if (diff == 0) return 1;
		if (diff == 1) return 2;
		return 3;
	}
}