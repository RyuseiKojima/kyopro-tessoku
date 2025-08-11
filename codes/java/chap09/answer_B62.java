import java.util.*;
import java.io.*;

class Answer_B62 {
	public static void main(String[] args) throws IOException {
		// 入力（高速な入出力のため、Scanner の代わりに BufferedReader を使っています）
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(buff.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] A = new int[M + 1];
		int[] B = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(buff.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// 隣接リストの作成
		G = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			G[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= M; i++) {
			G[A[i]].add(B[i]);
			G[B[i]].add(A[i]);
		}
		
		// 深さ優先探索
		visited = new boolean[N + 1];
		list.add(1);
		dfs(1);

		for (int i : answer) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	static int N;
	static boolean[] visited; // 頂点 x が青色の場合、visited[x] = true
	static ArrayList<Integer>[] G;
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> answer;
	static void dfs(int pos) { // pos は現在位置
		// Nにたどり着いたら終了
		if (pos == N) {
			answer = (ArrayList<Integer>) list.clone();
			return;
		}

		// その他の場合
		visited[pos] = true;
		for (int i = 0; i < G[pos].size(); i++) {
			int nex = G[pos].get(i);
			if (visited[nex] == false) {
				list.add(nex); // 頂点 nex を経路に追加
				dfs(nex);
				// Nに辿り着かなかったときは、経路から nex を削除
				list.remove(list.size() - 1);
			}
		}
		return;
	}
}