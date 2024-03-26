import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N][100001];
		app[] apps = new app[N];
		StringTokenizer memst = new StringTokenizer(br.readLine());
		StringTokenizer costst = new StringTokenizer(br.readLine());
		int sum = 0;
		apps[0] = new app(0, 0);
		for (int i = 0; i < N; i++) {
			int memory = Integer.parseInt(memst.nextToken());
			int cost = Integer.parseInt(costst.nextToken());
			apps[i] = new app(memory, cost);
		}

		Arrays.sort(apps, new Comparator<app>() {

			@Override
			public int compare(app o1, app o2) {
				if (o1.cost == o2.cost) {
					return o2.memory - o1.memory;
				}
				return o1.cost - o2.cost;
			}
		});

		// for (app app : apps) {
		// System.out.println(app);
		// }

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int memory = apps[i].memory;
			int cost = apps[i].cost;
			for (int j = 0; j < 100001; j++) {
				if (i == 0) {
					if (j >= cost) {
						dp[i][j] = memory;
					}
				} else {
					if (j >= cost) {
						dp[i][j] = Math.max(dp[i - 1][j - cost] + memory, dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
				if (dp[i][j] >= M) {
					ans = Math.min(ans, j);
				}
			}
		}

		// for (int[] is : dp) {
		// System.out.println(Arrays.toString(is));
		// }

		System.out.println(ans);
	}

	static class app {
		int memory, cost;

		public app(int memory, int cost) {
			super();
			this.cost = cost;
			this.memory = memory;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public int getMemory() {
			return memory;
		}

		public void setMemory(int memory) {
			this.memory = memory;
		}

		@Override
		public String toString() {
			return "app [memory=" + memory + ", cost=" + cost + "]";
		}

	}
}