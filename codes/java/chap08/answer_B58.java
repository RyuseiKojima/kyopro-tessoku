import java.util.*;

class Answer_B58 {
    /**
     * セグメント木
     */
    static class SegmentTree {
        int[] dat; // セグメント木のデータ
        int siz; // セグメント木のサイズ

        /**
         * セグメント木の初期化
         * @param N 要素数
         */
        void init(int N) {
            siz = 1;
            while (siz < N) siz *= 2;
            dat = new int[siz * 2];
            Arrays.fill(dat, 1_000_000_000); // 大きい値で初期化
        }
        
        /**
         * セグメント木の更新
         * @param pos 更新する位置
         * @param x 新しい値
         */
        void update(int pos, int x) {
            // 葉ノードの更新
            pos = pos + siz - 1;
            dat[pos] = x;
            // 親ノードの更新
            while (pos >= 2) {
                pos /= 2;
                dat[pos] = Math.min(dat[pos * 2], dat[pos * 2 + 1]);
            }
        }

        /**
         * セグメント木のクエリ
         * @param l クエリの左端
         * @param r クエリの右端
         * @param a クエリの対象区間の左端
         * @param b クエリの対象区間の右端
         * @param u ノードのレベル
         * @return
         */
        int query(int l, int r, int a, int b, int u) {
            if (r <= a || b <= l) return 1_000_000_000;
            if (l <= a && b <= r) return dat[u];
            int m = (a + b) / 2;
            int leftVal = query(l, r, a, m, u * 2);
            int rightVal = query(l, r, m, b, u * 2 + 1);
            return Math.min(leftVal, rightVal);
        }
    }

    /**
     * C++ の lower_bound 相当
     * @param arr 配列
     * @param from 開始インデックス
     * @param to 終了インデックス
     * @param key 探索する値
     * @return
     */
    static int lowerBound(int[] arr, int from, int to, int key) {
        int idx = Arrays.binarySearch(arr, from, to, key);
        if (idx < 0) return -idx - 1;
        return idx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        int[] X = new int[N + 1];
        for (int i = 1; i <= N; i++) X[i] = sc.nextInt();

        sc.close();

        SegmentTree Z = new SegmentTree();
        Z.init(N);

        int[] dp = new int[N + 1];
        dp[1] = 0;
        Z.update(1, 0);

        for (int i = 2; i <= N; i++) {
            int posL = lowerBound(X, 1, N + 1, X[i] - R);
            int posR = lowerBound(X, 1, N + 1, X[i] - L + 1) - 1;
            

            if (posL <= posR) {
                dp[i] = Z.query(posL, posR + 1, 1, Z.siz + 1, 1) + 1;
            } else {
                dp[i] = 1_000_000_000; // 範囲がない場合
            }

            Z.update(i, dp[i]);
        }

        System.out.println(dp[N]);
    }
}
