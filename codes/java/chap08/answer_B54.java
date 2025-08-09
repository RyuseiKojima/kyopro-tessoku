import java.util.*;

class Answer_B54 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Map<Long, Integer> scoreMap = new HashMap<>();

		long answer = 0;

		for (int i = 1; i <= N; i++) {
			long A = sc.nextLong();
			answer += scoreMap.getOrDefault(A, 0);
			scoreMap.put(A, scoreMap.getOrDefault(A, 0) + 1);
		}

		sc.close();

		System.out.println(answer);

	}
}