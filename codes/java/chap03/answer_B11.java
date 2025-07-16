import java.util.*;

class Answer_B11 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) A[i] = sc.nextInt();

		Arrays.sort(A); // 配列 A をソート

		int Q = sc.nextInt(); // クエリの数
		for (int i = 0; i < Q; i++) {
			int X = sc.nextInt();
			int index = Arrays.binarySearch(A, X);
			if (index < 0) {
				index = -index - 1; // X が存在しない場合、挿入位置を取得
			}
			System.out.println(index);
		}
		sc.close();
	}
};
