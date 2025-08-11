import java.util.*;
import java.io.*;

class Answer_B66 {
	public static void main(String[] args) throws IOException {
		// 入力（高速な入出力のため、Scanner の代わりに BufferedReader を使っています）
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
		
		st = new StringTokenizer(buff.readLine());
		int Q = Integer.parseInt(st.nextToken());
		int[] QueryType = new int[Q + 1];
		int[] X = new int[Q + 1];
		int[] U = new int[Q + 1];
		int[] V = new int[Q + 1];
		for (int i = 1; i <= Q; i++) {
			st = new StringTokenizer(buff.readLine());
			QueryType[i] = Integer.parseInt(st.nextToken());
			if (QueryType[i] == 1) X[i] = Integer.parseInt(st.nextToken());
			if (QueryType[i] == 2) {
				U[i] = Integer.parseInt(st.nextToken());
				V[i] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[] cancelled = new boolean[M + 1];
		for (int i = 1; i <= Q; i++) {
			if (QueryType[i] == 1) cancelled[X[i]] = true;
		}

		// 最後の状態の Union-Find 木を作成
		UnionFind uf = new UnionFind(N + 1);
		for (int i = 1; i <= M; i++) {
			if (!cancelled[i]) {
				uf.unite(A[i], B[i]);
			}
		}

		String[] Answers = new String[Q + 1];

		// クエリを逆から処理
		for (int i = Q; i >= 1; i--) {
			if (QueryType[i] == 1) {
				// 駅 A[x[i]] と駅 B[x[i]] を結ぶ路線が開通
				uf.unite(A[X[i]], B[X[i]]);
			}
			if (QueryType[i] == 2) {
				if (uf.same(U[i], V[i]) == true) Answers[i] = "Yes";
				else Answers[i] = "No";
			}
		}

		PrintWriter output = new PrintWriter(System.out);
		for (int i = 1; i <= Q; i++) {
			if (QueryType[i] == 2) output.println(Answers[i]);
		}
		output.flush();
	}
	
	// Union-Find 木を実装したクラス UnionFind
	static class UnionFind {
		int n;
		int[] par;
		int[] size;

		// n 頂点の Union-Find を作成
		public UnionFind(int n) {
			this.n = n;
			par = new int[n + 1];
			size = new int[n + 1];
			Arrays.fill(par, -1); // 最初は親が無い (par[i] = -1)
			Arrays.fill(size, 1); // 最初はグループの頂点数が 1 (size[i] = 1)
		}

		// 頂点 x の根を返す関数
		int root(int x) {
			while (true) {
				if (par[x] == -1) {
					break;  // 1 個先（親）がなければ、ここが根
				}
				x = par[x]; // 1 個先（親）に進む
			}
			return x;
		}

		// 要素 u と v を統合する関数
		void unite(int u, int v) {
			int rootU = root(u);
			int rootV = root(v);
			if (rootU == rootV) {
				return; // u と v が同グループのときは処理を行わない
			}
			if (size[rootU] < size[rootV]) {
				par[rootU] = rootV;
				size[rootV] += size[rootU];
			}
			else {
				par[rootV] = rootU;
				size[rootU] += size[rootV];
			}
		}

		// 要素xを孤立させる関数
		void remove(int x) {
			int rootX = root(x);
			if (rootX == -100) {
				return; // x が存在しない場合は処理を行わない
			}
			par[x] = -100; // 親を -100 にして孤立させる
			size[x] = 1; // グループのサイズを 1 に戻す
		}

		// 要素 u と v が同一のグループかどうかを返す関数
		boolean same(int u, int v) {
			// どちらかが存在しない場合は false
			if (root(u) == -100 || root(v) == -100) {
				return false;
			}
			return root(u) == root(v);
		}
	}
}