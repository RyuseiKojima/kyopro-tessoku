import java.util.*;

class Answer_B45 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		long a = sc.nextLong();
		long b = sc.nextLong();
		long c = sc.nextLong();

		sc.close();

		if (a + b + c == 0) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
};
