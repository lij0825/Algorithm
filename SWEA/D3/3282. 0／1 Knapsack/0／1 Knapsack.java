
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 0/1 Knapsack
public class Solution {

	static int N, K;
	static Stuff[] stuff;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 갯수
			K = Integer.parseInt(st.nextToken()); // 총 부피

			stuff = new Stuff[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken()); // 부피
				int c = Integer.parseInt(st.nextToken()); // 가치
				stuff[i] = new Stuff(v, c);
			}

			int result = solve();

			bw.write("#" + t + " " + result + "\n");

		}

		bw.flush();
		bw.close();
		br.close();

	}

	static int solve() {
		int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (stuff[i].v > j) {
					dp[i][j] = dp[i - 1][j];

				} else {
					int skip = dp[i - 1][j];
					int take = stuff[i].c + dp[i - 1][j - stuff[i].v];
					dp[i][j] = Math.max(take, skip);
				}
			}
		}

		return dp[N][K];
	}

	static class Stuff {
		int v, c;

		public Stuff() {
		}

		public Stuff(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}

}
