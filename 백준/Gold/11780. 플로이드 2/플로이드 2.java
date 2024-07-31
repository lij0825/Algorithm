import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine()); // 도시 개수 노드
		int m = Integer.parseInt(br.readLine()); // 버스 개수 간선

		int[][] board = new int[n + 1][n + 1];

		int[][] path = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			Arrays.fill(board[i], INF);
			Arrays.fill(path[i], INF);
		}

		for (int i = 0; i < m; i++) { // 버스 정보
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작
			int e = Integer.parseInt(st.nextToken()); // 도착
			int v = Integer.parseInt(st.nextToken()); // 비용

			board[s][e] = Math.min(v, board[s][e]);
			path[s][e] = s;
		}

		for (int i = 1; i <= n; i++) { // 경유지
			for (int j = 1; j <= n; j++) { // 시작
				for (int k = 1; k <= n; k++) { // 끝
					if (j == k) {
						continue;
					}
					if (board[j][i] + board[i][k] < board[j][k]) {
						board[j][k] = board[j][i] + board[i][k]; // 경로가 추가 됐을때
						path[j][k] = path[i][k];
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (board[i][j] == INF) {
					path[i][j] = 0;
					sb.append(0).append(" ");
				} else {
					sb.append(board[i][j]).append(" ");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

		StringBuilder pathsb = new StringBuilder();
		Stack<Integer> s = new Stack<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (path[i][j] == 0) {
					pathsb.append(0).append("\n");
					continue;
				}
				s.add(j);
				int e = path[i][j];
				while (e != i) {
					s.add(e);
					e = path[i][e];
				}
				s.add(i);
				pathsb.append(s.size()).append(" ");
				while (!s.isEmpty()) {
					pathsb.append(s.pop()).append(" ");
				}
				pathsb.deleteCharAt(pathsb.length() - 1);
				pathsb.append("\n");
			}
		}
		pathsb.deleteCharAt(pathsb.length() - 1);
		System.out.println(pathsb);
	}
}