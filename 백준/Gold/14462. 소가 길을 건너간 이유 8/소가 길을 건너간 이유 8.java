import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] left = new int[N];
		int[] right = new int[N];

		for (int i = 0; i < N; i++) {
			left[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			right[i] = Integer.parseInt(br.readLine());
		}

		int[][] dp = new int[N][N];
		dp[0][0] = isFriend(left[0], right[0]) ? 1 : 0;

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], isFriend(left[i], right[0]) ? 1 : 0);
			dp[0][i] = Math.max(dp[0][i - 1], isFriend(left[0], right[i]) ? 1 : 0);
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (isFriend(left[i], right[j])) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}

		System.out.println(dp[N - 1][N - 1]);

	}

	static boolean isFriend(int a, int b) {
		return Math.abs(a - b) <= 4;
	}
}