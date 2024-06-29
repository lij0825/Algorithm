import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static final int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		int[][] dp = new int[2001][2001];

		for (int i = 0; i < 2001; i++) {
			for (int j = 0; j < 2001; j++) {
				dp[i][j] = MAX;
			}
		}

		Queue<info> q = new ArrayDeque<>();
		q.add(new info(1, 0, 0));
		dp[1][0] = 0;

		while (true) {
			info temp = q.poll();

			if (temp.s == S) {
				System.out.println(temp.t);
				break;
			}

			q.add(new info(temp.s, temp.s, temp.t + 1));
			dp[temp.s][temp.s] = temp.t + 1;

			if (temp.s + temp.c < 2001 && dp[temp.s + temp.c][temp.c] > temp.t + 1) {
				q.add(new info(temp.s + temp.c, temp.c, temp.t + 1));
				dp[temp.s + temp.c][temp.c] = temp.t + 1;
			}
			if (temp.s - 1 >= 0 && dp[temp.s - 1][temp.c] > temp.t + 1) {
				q.add(new info(temp.s - 1, temp.c, temp.t + 1));
				dp[temp.s - 1][temp.c] = temp.t + 1;
			}

		}

	}

	static class info {
		int s, c, t;

		public info(int s, int c, int t) {
			this.s = s;
			this.c = c;
			this.t = t;
		}
	}

}