import java.util.*;

class Answer_B07 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 入力
		int t = sc.nextInt();
		int n = sc.nextInt();
		int[] people = new int[t+1]; 
		for (int i = 0; i < n; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			people[l]++;
			people[r]--;
		}

		int count = 0;
		for (int i = 0; i < t; i++) {
			count += people[i];
			System.out.println(count);
		}

		sc.close();
	}
};
