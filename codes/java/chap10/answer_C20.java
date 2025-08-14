import java.io.*;
import java.util.*;

/**
 * 特別区切り分け問題（ヒューリスティック解法）
 * 
 * K個の元地区を、L個の特別区にまとめる。
 * 各特別区間での「人口」と「役所職員数」のバランスをできるだけ均等にすることを目的とする。
 * 
 * アルゴリズム概要：
 * 1. 入力データ読み込み（地区ごとの人口・職員数・地図情報）
 * 2. 隣接グラフ構築
 * 3. Union-Findを用いた貪欲マージ（K→L地区までまとめる）
 * 4. 焼きなまし風局所探索で解の改善
 * 5. 最終的な地区割り当てを出力
 */
class Answer_C20 {
    public static void main(String[] args) throws IOException {
        // 高速入出力の準備
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 入力
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // マス目（地図はN×N）
        int K = Integer.parseInt(st.nextToken()); // 元々の地区数
        int L = Integer.parseInt(st.nextToken()); // 作りたい特別区の数

        int[] A = new int[K + 1]; // 地区kの人口
        int[] B = new int[K + 1]; // 地区kの役所職員数
        for (int k = 1; k <= K; k++) {
            st = new StringTokenizer(br.readLine());
            A[k] = Integer.parseInt(st.nextToken());
            B[k] = Integer.parseInt(st.nextToken());
        }

        // 地図情報（C[i][j] = そのマスの地区番号）
        int[][] C = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                C[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 隣接グラフ構築
        // G[i] = 地区iに隣接している地区のリスト
        ArrayList<Integer>[] G = new ArrayList[K + 1];
        for (int i = 1; i <= K; i++) G[i] = new ArrayList<>();

        // 上下左右の隣接マスから地区の隣接関係を抽出
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 下方向
                if (i != N && C[i][j] != 0 && C[i + 1][j] != 0 && C[i][j] != C[i + 1][j]) {
                    G[C[i][j]].add(C[i + 1][j]);
                    G[C[i + 1][j]].add(C[i][j]);
                }
                // 右方向
                if (j != N && C[i][j] != 0 && C[i][j + 1] != 0 && C[i][j] != C[i][j + 1]) {
                    G[C[i][j]].add(C[i][j + 1]);
                    G[C[i][j + 1]].add(C[i][j]);
                }
            }
        }
        // 重複除去（LinkedHashSetで順序保持＋重複排除）
        for (int i = 1; i <= K; i++) {
            Collections.sort(G[i]);
            G[i] = new ArrayList<>(new LinkedHashSet<>(G[i]));
        }

        // Union-Findによる初期マージ（貪欲法）
        UnionFind uf = new UnionFind(K);
        // K → L 地区になるまでマージ
        for (int step = 1; step <= K - L; step++) {
            int minSize = Integer.MAX_VALUE;
            int v1 = -1, v2 = -1;
            // 全ての隣接ペアを確認し、最小合併サイズの組を選ぶ
            for (int j = 1; j <= K; j++) {
                for (int v : G[j]) {
                    if (!uf.same(j, v)) {
                        int s1 = uf.size[uf.root(j)];
                        int s2 = uf.size[uf.root(v)];
                        if (minSize > s1 + s2) {
                            minSize = s1 + s2;
                            v1 = j;
                            v2 = v;
                        }
                    }
                }
            }
            uf.unite(v1, v2);
        }

        // 現在のマージ結果を answer 配列に反映（特別区番号を割り振る）
        int[] answer = new int[K + 1];
        List<Integer> roots = new ArrayList<>();
        for (int i = 1; i <= K; i++) roots.add(uf.root(i)); // 特別区グループごとの代表地区
        Collections.sort(roots);
        roots = new ArrayList<>(new LinkedHashSet<>(roots)); // 重複除去（代表地区に集約）
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < roots.size(); j++) {
                if (roots.get(j) == uf.root(i)) {
                    answer[i] = j + 1; // 1〜L の区番号
                }
            }
        }

        // 焼きなまし風ランダム探索で改善
        Random rand = new Random();
        double TIME_LIMIT = 0.8; // 制限時間(秒)
        long startTime = System.nanoTime();
        double currentScore = getScore(K, L, A, B, G, answer); // 現在のスコア

        while ((System.nanoTime() - startTime) / 1e9 < TIME_LIMIT) { // 制限時間までループ
            // ランダムに1地区を選び、その隣接地区の区番号に移す案を作成
            int v, x;
            do {
                v = rand.nextInt(K) + 1;
                int adj = G[v].get(rand.nextInt(G[v].size())); // 隣接地区をランダムに選ぶ
                x = answer[adj]; // その隣接地区の特別区番号を取得
            } while (answer[v] == x); // 同じ区ならやり直し

            // 試しに移動
            int oldX = answer[v];
            answer[v] = x;
            double newScore = getScore(K, L, A, B, G, answer);

            // 温度関数に基づく受理判定（確率的に悪化も許容）
            double randValue = rand.nextDouble();
            double temp = 0.0040 - 0.0039 * ((System.nanoTime() - startTime) / 1e9 / TIME_LIMIT); // 時間経過に応じて温度を下げる
            if (newScore != 0.0 && randValue < Math.exp((newScore - currentScore) / temp)) { // スコア改善量に応じて受理確率を決定
                currentScore = newScore; // 採用
            } else {
                answer[v] = oldX; // 元に戻す
            }
        }

        // 出力
        for (int i = 1; i <= K; i++) {
            bw.write(answer[i] + "\n");
        }
        bw.flush();
    }

    // Union-Find実装
    static class UnionFind {
        int[] par;  // 親ノード
        int[] size; // 集合のサイズ
        public UnionFind(int n) {
            par = new int[n + 1];
            size = new int[n + 1];
            Arrays.fill(par, -1);
            Arrays.fill(size, 1);
        }
        int root(int x) {
            while (par[x] != -1) x = par[x];
            return x;
        }
        void unite(int u, int v) {
            int rootU = root(u);
            int rootV = root(v);
            if (rootU == rootV) return;
            if (size[rootU] < size[rootV]) {
                par[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                par[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
        boolean same(int u, int v) {
            return root(u) == root(v);
        }
    }

    // DFSで連結性を確認
    static void dfs(int pos, boolean[] visited, ArrayList<Integer>[] G, int[] answer) {
        visited[pos] = true;
        for (int nex : G[pos]) {
            if (answer[nex] == answer[pos] && !visited[nex]) dfs(nex, visited, G, answer);
        }
    }

    /**
     * 現在の区割りに対するスコアを計算
     * @param K 地区数
     * @param L 特別区数
     * @param A 各地区の人口
     * @param B 各地区の職員数
     * @param G 隣接地区リスト
     * @param answer 現在の区割り
     * @return
     */
    static double getScore(int K, int L, int[] A, int[] B, ArrayList<Integer>[] G, int[] answer) {
        boolean[] visited = new boolean[K + 1];
        Arrays.fill(visited, false);

        // 各特別区が連結かどうか確認
        for (int i = 1; i <= L; i++) {
            int pos = -1;
            for (int j = 1; j <= K; j++) {
                if (answer[j] == i) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) return 0.0; // 特別区に地区が無い → 無効
            dfs(pos, visited, G, answer); // 同じ特別区の連結している地区をすべて訪問 
        }

        // 全地区が訪問済みでなければ無効
        for (int i = 1; i <= K; i++) {
            if (!visited[i]) return 0.0;
        }

        // 各特別区の人口・職員数を集計
        int[] p = new int[L + 1];
        int[] q = new int[L + 1];
        for (int i = 1; i <= K; i++) {
            p[answer[i]] += A[i];
            q[answer[i]] += B[i];
        }

        // 各特別区の人口と職員数の最小値と最大値
        int pmin = Arrays.stream(p, 1, L + 1).min().getAsInt();
        int pmax = Arrays.stream(p, 1, L + 1).max().getAsInt();
        int qmin = Arrays.stream(q, 1, L + 1).min().getAsInt();
        int qmax = Arrays.stream(q, 1, L + 1).max().getAsInt();

        // 最小人口/最大人口 と 最小職員数/最大職員数 のうち小さい方をスコアとする（バランスがいいほど大きいスコアとなる）
        return Math.min((double) pmin / pmax, (double) qmin / qmax);
    }
}