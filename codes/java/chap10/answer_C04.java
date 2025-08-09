import java.util.*;

class Answer_C04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		sc.close();

		// long値を格納するリストを作成
		List<Long> list = new ArrayList<>();
		for (long i = 1; i * i <= N; i++) {
			if (N % i == 0) {
				list.add(i);
				if (i * i != N) {
					list.add(N / i);
				}
			}
		}
		Collections.sort(list);
		for (long x : list) {
			System.out.println(x);
		}
	}
}