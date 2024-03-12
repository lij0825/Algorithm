import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		int M = Integer.parseInt(br.readLine());
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
		}

		boolean[] broke = new boolean[10];

		for (int i = 0; i < M; i++) {
			int token = Integer.parseInt(st.nextToken());
			broke[token] = true;
		}

		int result = Math.abs(N - 100);
		for (int i = 0; i < 1_000_000; i++) {
			String str = String.valueOf(i);

			int len = str.length();

			boolean isBreak = false;
			for (int j = 0; j < len; j++) {
				if (broke[str.charAt(j) - '0']) {
					isBreak = true;
					break;
				}
			}
			if (!isBreak) {
				int min = Math.abs(N - i) + len;
				result = Math.min(min, result);
			}
		}
		System.out.println(result);
	}
}