import java.util.*;

class Answer_C17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int Q = sc.nextInt(); // クエリ数
        char[] queryType = new char[Q + 1];
        String[] X = new String[Q + 1];

        for (int i = 1; i <= Q; i++) {
            queryType[i] = sc.next().charAt(0);
            if (queryType[i] == 'A' || queryType[i] == 'B') {
                X[i] = sc.next();
            }
        }

        Deque<String> Z1 = new ArrayDeque<>(); // 真ん中までのデック
        Deque<String> Z2 = new ArrayDeque<>(); // 後半のデック

        for (int i = 1; i <= Q; i++) {
            // [A] 最後尾に入る
            if (queryType[i] == 'A') {
                Z2.addLast(X[i]);
            }
            // [B] 中央に入る
            if (queryType[i] == 'B') {
                Z1.addLast(X[i]);
            }
            // [C] 先頭が抜ける
            if (queryType[i] == 'C') {
                Z1.pollFirst();
            }
            // [D] 先頭を答える
            if (queryType[i] == 'D') {
                System.out.println(Z1.peekFirst());
            }

            // 微調整（前半のデック Z1 が大きすぎる場合）
            while (Z1.size() - Z2.size() >= 2) {
                String r = Z1.pollLast();
                Z2.addFirst(r);
            }
            // 微調整（後半のデック Z2 が大きすぎる場合）
            while (Z1.size() - Z2.size() <= -1) {
                String r = Z2.pollFirst();
                Z1.addLast(r);
            }
        }

        sc.close();
    }
}