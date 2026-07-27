import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//화려한 경로
public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] node = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				node[i] = Integer.parseInt(st.nextToken()) - 1;
			}

			int[] edgeU = new int[M];
			int[] edgeV = new int[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				edgeU[i] = Integer.parseInt(st.nextToken()) - 1;
				edgeV[i] = Integer.parseInt(st.nextToken()) - 1;
			}

			int MAX_STATE = 1;
			for (int i = 0; i < K; i++) {
				MAX_STATE *= 2;
			}

			long[][] dp = new long[N][MAX_STATE];

			for (int i = 0; i < N; i++) {
				boolean[] visited = new boolean[K];
				visited[node[i]] = true;

				int startState = getIndexFromVisited(visited, K);
				dp[i][startState] = 1;
			}

			for (int state = 1; state < MAX_STATE; state++) {
				boolean[] visited = getVisitedFromState(state, K);

				for (int i = 0; i < M; i++) {
					int u = edgeU[i];
					int v = edgeV[i];
					int colorU = node[u];
					int colorV = node[v];

					if (dp[u][state] > 0 && visited[colorV] == false) {
						visited[colorV] = true;
						int nextState = getIndexFromVisited(visited, K);
						dp[v][nextState] += dp[u][state];
						visited[colorV] = false;
					}

					if (dp[v][state] > 0 && visited[colorU] == false) {
						visited[colorU] = true;
						int nextState = getIndexFromVisited(visited, K);
						dp[u][nextState] += dp[v][state];
						visited[colorU] = false;
					}
				}
			}

			long answer = 0;
			for (int i = 0; i < N; i++) {
				for (int state = 1; state < MAX_STATE; state++) {
					boolean[] visited = getVisitedFromState(state, K);

					int colorCount = 0;
					for (int c = 0; c < K; c++) {
						if (visited[c])
							colorCount++;
					}

					if (colorCount >= 2) {
						answer += dp[i][state];
					}
				}
			}

			bw.write(answer + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
	}

	static boolean[] getVisitedFromState(int state, int K) {
		boolean[] visited = new boolean[K];
		for (int i = 0; i < K; i++) {
			if (state % 2 == 1) {
				visited[i] = true;
			}
			state /= 2;
		}
		return visited;
	}

	static int getIndexFromVisited(boolean[] visited, int K) {
		int index = 0;
		int multiplier = 1;
		for (int i = 0; i < K; i++) {
			if (visited[i]) {
				index += multiplier;
			}
			multiplier *= 2;
		}
		return index;
	}
}