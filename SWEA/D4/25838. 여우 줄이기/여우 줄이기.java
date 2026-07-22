import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// 여우 줄이기
public class Solution {

	static int N;
	static Stack<Character> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {

			stack = new Stack<Character>();
			N = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {
				stack.push(input[i]);
				if (stack.size() >= 3) {
					if (stack.peek() == 'x' && stack.get(stack.size() - 2) == 'o'
							&& stack.get(stack.size() - 3) == 'f') {
						stack.pop();
						stack.pop();
						stack.pop();
					}
				}
			}

			bw.write(stack.size() + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
