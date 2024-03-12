import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		boolean[][] visited = new boolean[n][m];
		int[][] board = new int[n][m];

		Queue<Pair> q = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				if (str[j].equals("O")) {
					board[i][j] = 0;
				}
				if (str[j].equals("X")) {
					board[i][j] = -1;
				}
				if (str[j].equals("I")) {
					board[i][j] = 1;
					q.add(new Pair(i, j));
					visited[i][j] = true;
				}
				if (str[j].equals("P")) {
					board[i][j] = 2;
				}
			}
		}

		int res = 0;

		int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int y = p.y;
			int x = p.x;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
					continue;
				}
				if (visited[ny][nx] || board[ny][nx] < 0) {
					continue;
				}
				if (board[ny][nx] == 2) {
					res++;
				}
				q.add(new Pair(ny, nx));
				visited[ny][nx] = true;
			}
		}

		System.out.println(res == 0 ? "TT" : res);
	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}