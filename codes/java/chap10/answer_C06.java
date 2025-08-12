import java.util.*;

class Answer_C06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

        System.out.println(N);
        for (int i = 1; i <= N; i++) {
            System.out.println(i + " " + (i % N + 1));
        }
	}
}