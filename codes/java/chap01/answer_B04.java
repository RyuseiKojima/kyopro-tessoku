import java.util.*;

class Answer_B04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		String str = Integer.toString(n);
		int result = 0;
		int multiVal = 1;
		for (int i = str.length() - 1; i >= 0 ; i--) {
			int digit = str.charAt(i) - '0';
			result += digit * multiVal;
			multiVal *= 2;
		}
		System.out.println(result);
	}
}