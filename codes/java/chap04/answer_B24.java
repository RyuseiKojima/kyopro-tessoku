import java.util.*;

class Answer_B24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] boxes = new int[N][2];
        for (int i = 0; i < N; i++) {
            boxes[i][0] = sc.nextInt(); // 縦
            boxes[i][1] = sc.nextInt(); // 横
        }

        // 縦で昇順、縦が同じなら横を降順にソート
        Arrays.sort(boxes, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return Integer.compare(b[1], a[1]); // 横は降順
        });

        // 横のみに注目して LIS を求める
        ArrayList<Integer> L = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int h = boxes[i][1];
            int pos = Collections.binarySearch(L, h);
            if (pos < 0) pos = ~pos;

            if (pos == L.size()) {
                L.add(h);
            } else {
                L.set(pos, h);
            }
        }

        System.out.println(L.size());
        sc.close();
    }
}
