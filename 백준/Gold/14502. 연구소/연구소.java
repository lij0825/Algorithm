import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<Pair> vlist = new ArrayList<>();
	static int N, M, ans = 0;
	static int[][] board, copyboard;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		board = new int[N][M]; // 맵 0 빈 공간, 1 벽, 2 바이러스

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					vlist.add(new Pair(i, j));
				}
			}
		}
		dfs(0);
		System.out.println(ans);
	}

	static void dfs(int cnt) {
		if (cnt == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0) {
					board[i][j] = 1;
					dfs(cnt + 1);
					board[i][j] = 0;
				}
			}
		}
	}

	static void bfs() {
		copyboard = new int[N][M];
		arrCopy();
		Queue<Pair> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		for (Pair p : vlist) {
			q.add(new Pair(p.y, p.x));
			visited[p.y][p.x] = true;
		}
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				if (!isIn(ny, nx) || visited[ny][nx] || copyboard[ny][nx] == 1 || copyboard[ny][nx] == 2) {
					continue;
				}
				copyboard[ny][nx] = 2;
				visited[ny][nx] = true;
				q.add(new Pair(ny, nx));
			}
		}
		ans = Math.max(ans, cntEmpty());
	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	static void arrCopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyboard[i][j] = board[i][j];
			}
		}
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= M);
	}

	static int cntEmpty() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyboard[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}