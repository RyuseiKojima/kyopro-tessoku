import java.util.*;
import java.io.*;

class Answer_B63 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		int sx = sc.nextInt();
		int sy = sc.nextInt();
		int gx = sc.nextInt();
		int gy = sc.nextInt();

		char[][] grid = new char[R + 1][C + 1];
		int[][] db = new int[R + 1][C + 1];

		for (int i = 1; i <= R; i++) {
			String line = sc.next();
			for (int j = 1; j <= C; j++) {
				char ch = line.charAt(j - 1);
				grid[i][j] = ch;
				db[i][j] = 1_000_000_000; // 初期化
			}
		}

		sc.close();

		// ダイクストラ法の実行
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(sx, sy, 0));
		db[sx][sy] = 0;

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int d = cur.dist;

			if (d > db[x][y]) continue;

			// 4方向に移動
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 1 || nx > R || ny < 1 || ny > C) continue; // 範囲外チェック
				if (grid[nx][ny] == '#') continue; // 壁チェック
				// 移動コストの計算
				if (db[nx][ny] > d + 1) {
					db[nx][ny] = d + 1;
					pq.add(new State(nx, ny, d + 1));
				}
			}
		}

		System.out.println(db[gx][gy]);
	}


	// 移動方向を表す配列
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] dy = {1, 0, -1, 0};

	/**
	 * 状態を表すクラス
	 * (x, y) の座標と、スタート地点からの距離を保持します。
	 */
	private static class State implements Comparable<State> {
		int x, y, dist;
		State(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public int compareTo(State other) {
			return Integer.compare(this.dist, other.dist);
		}
	}
}