import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);

		int[][] dp = new int[N + 1][M + 1];

		dp[1][1] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		if (K == 0) {
			System.out.println(dp[N][M]);
		} else {
			int x = (K - 1) / M + 1;
			int y = (K - 1) % M + 1;
			System.out.println(dp[x][y] * dp[N - x + 1][M - y + 1]);
		}
	}
}