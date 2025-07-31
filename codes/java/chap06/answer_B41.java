import java.util.*;

class Answer_B41 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int X = sc.nextInt();
		int Y = sc.nextInt();

		sc.close();

		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();

		while (!(X == 1 && Y == 1)) {
			A.add(X);
			B.add(Y);
			if (X >= Y) {
				X -= Y;
			} else {
				Y -= X;
			}
		}

		// AとBを反転
		Collections.reverse(A);
		Collections.reverse(B);

		// 出力
		System.out.println(A.size());
		for (int i = 0; i < A.size(); i++) {
			System.out.println(A.get(i) + " " + B.get(i));
		}
	}
};
