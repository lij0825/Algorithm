import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int[] arr;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 점윈 수
			int B = Integer.parseInt(st.nextToken()); // 선반 높이
			ans = Integer.MAX_VALUE;
			arr = new int[N]; // 각 점원의 키
			visited = new boolean[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer> set;

			for (int i = 0; i < 1 << N; i++) {
				set = new ArrayList<>();
				for (int j = 0; j < N; j++) {
					if ((i & 1 << j) != 0) {
						set.add(arr[j]);
					}
				}
				if (!set.isEmpty()) {
					int sum = 0;
					for (int k = 0; k < set.size(); k++) {

						sum += set.get(k);

						if (sum - B >= ans) {
							break;
						}
					}
					if (sum >= B) {
						ans = Math.min(ans, sum - B);
					}
					if (ans == 0) {
						break;
					}
				}

			}
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}

}