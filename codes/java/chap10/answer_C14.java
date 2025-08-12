import java.util.*;

class Answer_C14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 入力
		int N = sc.nextInt();
		int M = sc.nextInt();
		// 隣接リストの作成
	    List<Edge>[] G = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) G[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            G[a].add(new Edge(b, c));
            G[b].add(new Edge(a, c));
		}
		sc.close();

        int[] cur1 = dijkstra(1, G, N);
        int[] curN = dijkstra(N, G, N);
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (cur1[i] + curN[i] == cur1[N]) {
				count++;
			}
		}
		System.out.println(count);
	}

	/**
	 * ダイクストラ法
	 * @param start 開始ノード
	 * @param G グラフ
	 * @param N ノード数
	 * @return 最短距離
	 */
    static int[] dijkstra(int start, List<Edge>[] G, int N) {
        final int INF = Integer.MAX_VALUE / 2;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        boolean[] used = new boolean[N + 1];
        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new State(0, start));
        while (!pq.isEmpty()) {
            State s = pq.poll();
            if (used[s.pos]) continue;
            used[s.pos] = true;
            for (Edge e : G[s.pos]) {
                if (dist[e.to] > dist[s.pos] + e.cost) {
                    dist[e.to] = dist[s.pos] + e.cost;
                    pq.add(new State(dist[e.to], e.to));
                }
            }
        }
        return dist;
    }

	// 重み付きグラフの辺のクラス Edge
	static class Edge {
		int to, cost; // 行き先 to、長さ cost
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	// ダイクストラ法の (cur[x], x) を管理するクラス（cur[x] = dist, x = pos に対応）
	static class State implements Comparable<State> {
		int dist, pos;
		public State(int dist, int pos) {
			this.dist = dist;
			this.pos = pos;
		}
		@Override public int compareTo(State s) {
			// State 型同士の比較をする関数
			if (this.dist < s.dist) {
				return -1;
			}
			if (this.dist > s.dist) {
				return 1;
			}
			return 0;
		}
	}
}