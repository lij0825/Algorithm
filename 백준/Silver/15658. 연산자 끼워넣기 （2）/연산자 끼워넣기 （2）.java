import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 0 : + , 1 : - , 2 : * , 3 : /
	static int[] calcul = new int[4];
	static int[] nums;
	static int max, min, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		n = Integer.parseInt(br.readLine());

		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			calcul[i] = Integer.parseInt(st.nextToken());
		}

		dfs(1, nums[0]);
		sb.append(max + "\n" + min);

		System.out.println(sb);
	}

	static void dfs(int idx, int res) {
		if (idx == n) {
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}

		for (int j = 0; j < 4; j++) {
			if (calcul[j] > 0) {
				if (j == 0) {
					calcul[j]--;
					dfs(idx + 1, res + nums[idx]);
					calcul[j]++;
				}
				if (j == 1) {
					calcul[j]--;
					dfs(idx + 1, res - nums[idx]);
					calcul[j]++;
				}
				if (j == 2) {
					calcul[j]--;
					dfs(idx + 1, res * nums[idx]);
					calcul[j]++;
				}
				if (j == 3) {
					calcul[j]--;
					dfs(idx + 1, res / nums[idx]);
					calcul[j]++;
				}
			}
		}
	}
}