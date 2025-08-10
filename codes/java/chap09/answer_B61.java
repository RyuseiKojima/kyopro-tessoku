import java.util.*;
import java.io.*;

class Answer_B61 {
	public static void main(String[] args) throws IOException {
		// 入力（高速な入力のため、Scanner の代わりに BufferedReader を使っています）
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(buff.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[M + 1];
		int[] B = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(buff.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// 隣接リストの作成
		ArrayList<Integer>[] G = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			G[i] = new ArrayList<Integer>(); // G[i] は頂点 i に隣接する頂点のリスト
		}
		for (int i = 1; i <= M; i++) {
			G[A[i]].add(B[i]); // 頂点 A[i] に隣接する頂点として B[i] を追加
			G[B[i]].add(A[i]); // 頂点 B[i] に隣接する頂点として A[i] を追加
		}
		
		// 出力（G[i].size() は頂点 i に隣接する頂点のリストの大きさ ＝ 次数）
		//（高速な出力のため、System.out.println ではなく PrintWriter を使っています）
		PrintWriter output = new PrintWriter(System.out);
		int ans = 0;
		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (G[i].size() > max) {
				max = G[i].size();
				ans = i;
			}
		}
		output.println(ans);
		output.flush();
	}
}