import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int B = Integer.parseInt(input[2]);

		boolean[] broken = new boolean[N + 1];

		for (int i = 0; i < B; i++) {
			broken[Integer.parseInt(br.readLine())] = true;
		}

		int brokeCnt = 0;

		for (int i = 1; i <= K; i++) {
			if (broken[i]) {
				brokeCnt++;
			}
		}

		int min = brokeCnt;

		for (int i = 2; i <= N - K + 1; i++) {
			if (broken[i - 1]) {
				brokeCnt--;
			}
			if (broken[i + K - 1]) {
				brokeCnt++;
			}
			min = Math.min(min, brokeCnt);
		}

		System.out.println(min);

	}
}