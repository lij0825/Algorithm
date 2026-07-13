import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	// 고객수
	static int N;
	// 장소
	static int[][] spot;
	// 방문 체크
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			min = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());

			visited = new boolean[N + 2];

			st = new StringTokenizer(br.readLine());
			spot = new int[N + 2][2];

			for (int i = 0; i < N + 2; i++) {
				spot[i][0] = Integer.parseInt(st.nextToken());
				spot[i][1] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0, 0);

			bw.write("#" + tc + " " + min + "\n");
		}

		bw.flush();
		bw.close();
	}

	static void dfs(int now, int count, int distance) {

		if (distance > min) {
			return;
		}

		if (count == N) {
			distance += Math.abs(spot[now][0] - spot[1][0]) + Math.abs(spot[now][1] - spot[1][1]);
			if (min > distance) {
				min = distance;
			}
			return;

		}

		for (int i = 2; i < N + 2; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i, count + 1, distance + Math.abs(spot[now][0] - spot[i][0]) + Math.abs(spot[now][1] - spot[i][1]));
				visited[i] = false;
			}
		}

		return;
	}

}
