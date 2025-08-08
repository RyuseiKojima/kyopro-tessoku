import java.util.*;

class Answer_B53 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int D = sc.nextInt();
        // G[i] は i 日目に始められる仕事の給料リスト
        List<List<Integer>> G = new ArrayList<>();
        for (int i = 0; i <= D; i++) {
            G.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            int X = sc.nextInt(); // 開始日
            int Y = sc.nextInt(); // 給料
            if (X <= D) {
                G.get(X).add(Y);
            }
        }

		sc.close();

		// 最大ヒープ（高給な仕事を優先）
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        long answer = 0;
        for (int day = 1; day <= D; day++) {
            // 今日始められる仕事をキューに追加
            for (int y : G.get(day)) {
                queue.offer(y);
            }

            // 最も給料の高い仕事を選んで実行
            if (!queue.isEmpty()) {
                answer += queue.poll();
            }
        }

        System.out.println(answer);
	}
}