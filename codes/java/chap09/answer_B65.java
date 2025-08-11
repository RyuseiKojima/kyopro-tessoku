import java.util.*;
import java.io.*;

class Answer_B65 {
	public static void main(String[] args) throws IOException {
		// 入力（高速な入出力のため、Scanner の代わりに BufferedReader を使っています）
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(buff.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		G = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			G[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer tmp = new StringTokenizer(buff.readLine());
			int A = Integer.parseInt(tmp.nextToken());
			int B = Integer.parseInt(tmp.nextToken());
			G[A].add(B);
			G[B].add(A);
		}

		// 動的計画法（dp[x] は社員 x の部下の数）
		dp = new int[N + 1]; // Java では new で初期化した配列の要素は 0 になることに注意
		visited = new boolean[N + 1]; // 訪問済みフラグの初期化
		dps(T);
		
		// 答えを空白区切りで出力（高速な出力のため、System.out.println ではなく PrintWriter を使っています）
		PrintWriter output = new PrintWriter(System.out);
		for (int i = 1; i <= N; i++) {
			output.print(dp[i] + " ");

		}
		output.println();
		output.flush();
	}

	static ArrayList<Integer>[] G;
	static int[] dp;
	static boolean[] visited;

	static void dps(int x) {
		if (visited[x]) return; // すでに訪問済み
		visited[x] = true;

		// 子ノードの数を数える
		for (Integer to : G[x]) {
			if (visited[to]) continue; // すでに訪問済みの子ノードはスキップ
			dps(to);
			dp[x] = Math.max(dp[x], dp[to] + 1);
		}
	}
}