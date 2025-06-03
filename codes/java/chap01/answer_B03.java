import java.util.*;

class Answer_B03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] products = new int[n];
		for (int i = 0; i < n; i++) products[i] = sc.nextInt();
		sc.close();
		boolean flg = false;
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int j2 = j + 1; j2 < n; j2++) {
					if (products[i] + products[j] + products[j2] == 1000) {
						flg = true;
						break;
					}
				}
			}
		}
		System.out.println(flg ? "Yes" : "No");
	}
}
