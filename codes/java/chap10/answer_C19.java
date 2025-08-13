// 問題: 長さ L の道に N 店舗があり、位置ごとに価格がある
// 長さ K の全ての区間で最低価格の店を選び、合計の最小値を求める
// 店がない区間があれば -1 を出力

import java.util.*;

class Answer_C19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 入力
        int N = sc.nextInt();
        int L = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[N + 1]; // 店舗の位置
        long[] C = new long[N + 1]; // 店舗の価格
        for (int i = 1; i <= N; i++) {
            A[i] = sc.nextInt();
            C[i] = sc.nextLong();
        }
        sc.close();

        long INF = (1L << 60);

        // 各地点の最安値を保持
        long[] Min_Value = new long[L + 1];
        Arrays.fill(Min_Value, INF);
        Min_Value[0] = 0; // 未使用
        for (int i = 1; i <= N; i++) {
            Min_Value[A[i]] = Math.min(Min_Value[A[i]], C[i]);
        }

        // セグメント木構築
        SegmentTree seg = new SegmentTree(L, INF);
        for (int i = 1; i <= L - 1; i++) {
            seg.update(i, Min_Value[i]);
        }

        // 各区間の最小値を取得して合計
        long Answer = 0;
        for (int i = 1; i <= L - K; i++) {
            // i から i+K の間をカバーできる施設の中で最小コストを取得
            long val = seg.query(i, i + K, 1, seg.size + 1, 1);
            if (val == INF) {
                System.out.println("-1");
                return;
            }
            Answer += val;
        }
        System.out.println(Answer);
    }

    // セグメント木 (min 取得用)
    static class SegmentTree {
        int size;
        long[] dat;
        long INF;

        SegmentTree(int n, long inf) {
            size = 1;
            this.INF = inf;
            while (size < n) size *= 2; // 2の累乗に拡張
            dat = new long[size * 2];
            Arrays.fill(dat, inf); // 初期値は INF
        }

        // 値の更新
        void update(int pos, long x) {
            pos += size - 1;
            dat[pos] = x;
            while (pos >= 2) {
                pos /= 2;
                dat[pos] = Math.min(dat[pos * 2], dat[pos * 2 + 1]);
            }
        }

        // 区間 [l, r) の最小値を取得
        long query(int l, int r, int a, int b, int u) {
            if (r <= a || b <= l) return INF; // 完全に外
            if (l <= a && b <= r) return dat[u]; // 完全に内
            int m = (a + b) / 2;
            long left = query(l, r, a, m, u * 2);
            long right = query(l, r, m, b, u * 2 + 1);
            return Math.min(left, right);
        }
    }
}
