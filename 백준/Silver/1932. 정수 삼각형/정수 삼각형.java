import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][N];
		for (int i = 0; i < dp.length; i++) {
			dp[N - 1][i] = board[N - 1][i];
		}

		int idx = N - 1;
		while (idx > 0) {
			for (int i = 0; i < idx; i++) {
				dp[idx - 1][i] = Math.max(dp[idx][i], dp[idx][i + 1]) + board[idx - 1][i];
			}
			idx--;
		}
		System.out.println(dp[0][0]);
	}
}