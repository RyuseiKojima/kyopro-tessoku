import java.util.*;

class Answer_B31 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long N = sc.nextLong();

		sc.close();
		long A1 = N /  3; // 3 で割り切れるものの個数
		long A2 = N /  5; // 5 で割り切れるものの個数
		long A3 = N / 7;
		long A4 = N / 15;
		long A5 = N / 21;
		long A6 = N / 35;
		long A7 = N / 105;
		System.out.println(A1 + A2 + A3 - A4 - A5 - A6 + A7);
	}
};
