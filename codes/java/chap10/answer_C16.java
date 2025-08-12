import java.util.*;

class Answer_C16 {
    static final int NEG_INF = -1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();     // 空港の数
        int M = sc.nextInt();     // 便の数
        long K = sc.nextLong();   // 乗り継ぎ時間（long）

        int[] A = new int[M + 1];   // A[i]: 出発空港
        int[] B = new int[M + 1];   // B[i]: 到着空港
        long[] S = new long[M + 1]; // 出発時刻
        long[] T = new long[M + 1]; // 到着時刻

        for (int i = 1; i <= M; i++) {
            A[i] = sc.nextInt();
            S[i] = sc.nextLong();
            B[i] = sc.nextInt();
            T[i] = sc.nextLong() + K; // 到着時刻に乗り継ぎ時間を加算（long）
        }
        sc.close();

        // list: (時刻, 種別, id)
        // 種別: 0 = ダミー（空港ごとの始端・終端マーカー）
        //       1 = 到着 (T)
        //       2 = 出発 (S)
        List<long[]> list = new ArrayList<>();
        // 出発便を追加
        for (int i = 1; i <= M; i++) list.add(new long[] { S[i], 2, i });
        // 到着便を追加
        for (int i = 1; i <= M; i++) list.add(new long[] { T[i], 1, i });
        // ダミーポイントを追加（各空港に始端・終端を作る）
        for (int i = 1; i <= N; i++) list.add(new long[] { -1L, 0, i });
        for (int i = 1; i <= N; i++) list.add(new long[] { 2_100_000_000L, 0, i });

        // 時刻昇順にソート、同じ時刻なら種別昇順（0,1,2）で到着(1)が出発(2)より先に来るようにする
        list.sort((o1, o2) -> {
            int cmp = Long.compare(o1[0], o2[0]);
            if (cmp != 0) return cmp;
            return Integer.compare((int)o1[1], (int)o2[1]);
        });

        int Lsize = list.size();
        // フライトIDに対応する頂点番号を記録
        int[] vertS = new int[M + 1]; // 出発の頂点番号
        int[] vertT = new int[M + 1]; // 到着の頂点番号

        // 各空港ごとの頂点番号リスト（時刻順）
        List<Integer>[] airport = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) airport[i] = new ArrayList<>();

        // ソートされた順に頂点番号(1..Lsize)を割り当て
        for (int i = 0; i < Lsize; i++) {
            long[] rec = list.get(i);
            int type = (int) rec[1];
            int id = (int) rec[2];
            int vnode = i + 1;
            if (type == 2) { // 出発
                vertS[id] = vnode;
                // 出発は空港 A[id] に属する
                airport[A[id]].add(vnode);
            } else if (type == 1) { // 到着
                vertT[id] = vnode;
                // 到着は空港 B[id] に属する
                airport[B[id]].add(vnode);
            } else { // ダミー
                // ダミーポイントは空港 id に属する
                airport[id].add(vnode);
            }
        }

        // グラフ構築: 頂点 0..Lsize+1 (0=始端, Lsize+1=終端)
        int V = Lsize + 2;
        List<int[]>[] G = new ArrayList[V];
        for (int i = 0; i < V; i++) G[i] = new ArrayList<>();

        // 1) フライトの辺: 出発 -> 到着（コスト1）
        for (int i = 1; i <= M; i++) {
            int u = vertS[i];
            int v = vertT[i];
            if (u == 0 || v == 0) continue; // 念のための安全チェック
            G[u].add(new int[]{v, 1});
        }

        // 2) 待機の辺: 同一空港内で、早い時刻 -> 遅い時刻（コスト0）
        for (int a = 1; a <= N; a++) {
            List<Integer> arr = airport[a];
            // arr は list のソート順により時刻昇順になっている
            for (int j = 0; j + 1 < arr.size(); j++) {
                int u = arr.get(j);
                int v = arr.get(j + 1);
                // 待機可能（早い -> 遅い）
                G[u].add(new int[]{v, 0});
            }
        }

        // 3) 始端と終端の接続
        int SRC = 0, SINK = V - 1;
        for (int a = 1; a <= N; a++) {
            List<Integer> arr = airport[a];
            if (arr.isEmpty()) continue; // 念のための安全チェック
            int first = arr.get(0);
            int last = arr.get(arr.size() - 1);
            // 始端 -> 最初の頂点
            G[SRC].add(new int[]{first, 0});
            // 最後の頂点 -> 終端
            G[last].add(new int[]{SINK, 0});
        }

        // DAG上でのDP（頂点番号昇順に処理）
        int[] dp = new int[V];
        Arrays.fill(dp, NEG_INF);
        dp[SRC] = 0;
        for (int u = 0; u < V; u++) {
            if (dp[u] == NEG_INF) continue;
            for (int[] e : G[u]) {
                int v = e[0], cost = e[1];
                if (dp[v] < dp[u] + cost) dp[v] = dp[u] + cost;
            }
        }

        System.out.println(dp[SINK]);
    }
}