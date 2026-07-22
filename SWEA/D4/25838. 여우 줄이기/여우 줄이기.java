import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 여우 줄이기
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			char[] input = br.readLine().toCharArray();

			char[] stack = new char[N];
			int idx = 0;

			for (int i = 0; i < N; i++) {
				stack[idx] = input[i];
				idx++;

				if (idx >= 3) {
					if (stack[idx - 1] == 'x' && stack[idx - 2] == 'o' && stack[idx - 3] == 'f') {
						idx -= 3;
					}
				}
			}

			bw.write(idx + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}