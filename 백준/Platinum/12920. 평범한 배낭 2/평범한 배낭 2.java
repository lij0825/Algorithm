import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 개수
		int M = Integer.parseInt(st.nextToken()); // 제한 무게

		dp = new int[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			knapsack(v, c, k, M);
		}

		System.out.println(dp[M]);
	}

	static void knapsack(int v, int c, int k, int M) {
		if (v * k >= M) {
			for (int j = v; j <= M; j++) {
				dp[j] = Math.max(dp[j], dp[j - v] + c);
			}
		} else {
			for (int cnt = 1; cnt <= k; cnt *= 2) {
				for (int j = M; j >= v * cnt; j--) {
					dp[j] = Math.max(dp[j], dp[j - v * cnt] + c * cnt);
				}
				k -= cnt;
			}
			if (k > 0) {
				for (int j = M; j >= v * k; j--) {
					dp[j] = Math.max(dp[j], dp[j - v * k] + c * k);
				}
			}
		}
	}
}