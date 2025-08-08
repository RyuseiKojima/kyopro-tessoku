import java.util.*;

class Answer_B51 {
	public static void main(String[] args) {
		// 入力
		Scanner sc = new Scanner(System.in);
		String Q = sc.next();

		sc.close();

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < Q.length(); i++) {
			char word = Q.charAt(i);
			if (word == '(') {
				stack.push(i + 1);
			} else if (word == ')') {
				int openIndex = stack.isEmpty() ? -1 : stack.pop();
				if (openIndex != -1) {
					System.out.println(openIndex + " " + (i + 1));
				}
			}
		}

	}
}