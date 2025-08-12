import java.util.*;

class Answer_C15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 入力
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] L = new int[N + 1];
        int[] R = new int[N + 1];
        int[] cntL = new int[86400 + 2]; // 86400秒（24時間）を超える可能性があるため、+2
        int[] cntR = new int[86400 + 2]; // 86400秒（24時間）を超える可能性があるため、+2
        for (int i = 1; i <= N; i++) {
            L[i] = sc.nextInt();
            R[i] = sc.nextInt() + K; // R[i] に K を加算
        }
        sc.close();

        // 左から区間スケジューリング
        List<int[]> tmp1 = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            tmp1.add(new int[]{R[i], L[i]});
        }
        tmp1.sort(Comparator.comparingInt(a -> a[0])); // R[i] の昇順（終了時間の早い順）でソート

        int CurrentTime1 = 0;
        int Num1 = 0;
        for (int[] interval : tmp1) {
            // interval[0] は R[i]、interval[1] は L[i]
            // CurrentTime1 は現在の時間
            // interval[1] は L[i]、つまり開始時間
            if (CurrentTime1 <= interval[1]) {
                CurrentTime1 = interval[0];
                Num1++;
                cntL[CurrentTime1] = Num1;
            }
        }

        // 右から区間スケジューリング
        List<int[]> tmp2 = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            tmp2.add(new int[]{L[i], R[i]});
        }
        tmp2.sort((a, b) -> Integer.compare(b[0], a[0])); // L[i] の降順（開始時間の遅い順）でソート

        int CurrentTime2 = 200000;
        int Num2 = 0;
        for (int[] interval : tmp2) {
            if (CurrentTime2 >= interval[1]) {
                CurrentTime2 = interval[0];
                Num2++;
                cntR[CurrentTime2] = Num2;
            }
        }

        // cntL, cntR を累積的に更新
        for (int i = 1; i <= 86400; i++) {
            cntL[i] = Math.max(cntL[i], cntL[i - 1]);
        }
        for (int i = 86400; i >= 0; i--) {
            cntR[i] = Math.max(cntR[i], cntR[i + 1]);
        }

        // 出力
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(cntL[L[i]] + cntR[R[i]] + 1).append("\n");
        }
        System.out.print(sb);
    }
}