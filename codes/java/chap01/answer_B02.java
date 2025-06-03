import java.util.*;

class Answer_B02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt(); // 入力部分
		int b = sc.nextInt(); // 入力部分
		sc.close();
		boolean flg = false;
		for (int i = a; i <= b; i++) {
			if (100 % i == 0) {
				flg = true;
				break;
			}
		}
		System.out.println(flg ? "Yes" : "No");
	}
};
