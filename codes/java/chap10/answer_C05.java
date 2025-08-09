import java.util.*;

class Answer_C05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt() - 1;
		sc.close();
        for (int x = 9; x >= 0; x--) {
            if (((N >> x) & 1) == 0) {
                System.out.print("4");
            } else {
                System.out.print("7");
            }
        }
        System.out.println();
	}
}