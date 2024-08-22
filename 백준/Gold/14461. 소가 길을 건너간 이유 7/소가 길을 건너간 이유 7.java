import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] board;
	static int N, T;
	static long ans = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];

		T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	static long solve() {
		int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0};
		long[][][] visited = new long[N][N][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 3; k++) {
					visited[i][j][k] = Long.MAX_VALUE;
				}
			}
		}

		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = 0;

		while (!q.isEmpty()) {
			Point p = q.poll();

			if (p.y == N - 1 && p.x == N - 1) {
				ans = Math.min(ans, p.time);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				long ntime = p.time + T;
				int ncnt = (int)((p.cnt + 1) % 3);

				if (!isIn(ny, nx)) {
					continue;
				}

				if (ncnt == 0) {
					ntime += board[ny][nx];
				}

				if (visited[ny][nx][ncnt] > ntime) {
					visited[ny][nx][ncnt] = ntime;
					q.add(new Point(ny, nx, ntime, ncnt));
				}
			}
		}

		return ans;
	}

	static class Point {
		int y, x;
		long time;
		int cnt;

		Point(int y, int x, long time, int cnt) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.cnt = cnt;
		}
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && x >= 0 && y < N && x < N;
	}
}