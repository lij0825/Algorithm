import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dp;
	static int v, c, k, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 개수
		M = Integer.parseInt(st.nextToken()); // 제한 무게

		dp = new int[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			knapsack();
		}

		System.out.println(dp[M]);
	}

	static void knapsack() {
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