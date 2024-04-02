import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int n, sy, sx, ey, ex; // board 크기 , 시작좌표 , 도착좌표
	static int[][] board; // 수영장 0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이
	static boolean[][] visited; // 방문체크
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
	static Pair start, end;
	static PriorityQueue<Pair> q = new PriorityQueue<>((a, b) -> a.time - b.time);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			n = Integer.parseInt(br.readLine());

			board = new int[n][n];
			visited = new boolean[n][n];

			for (int i = 0; i < n; i++) { // 수영장 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine()); // 시작점 입력
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			start = new Pair(sy, sx, 0);

			st = new StringTokenizer(br.readLine()); // 끝점 입력
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());

			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs() {
		q.clear();
		q.add(start);
		visited[sy][sx] = true;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int y = p.y;
			int x = p.x;
			int t = p.time;
			if (y == ey && x == ex) {
				return t;
			}
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (!isIn(ny, nx) || board[ny][nx] == 1 || visited[ny][nx]) { // 범위 밖이거나 벽이거나 방문했던 곳이면
					continue;
				}
				if (board[ny][nx] == 2) {
					q.add(new Pair(ny, nx, t + (3 - t % 3)));
					visited[ny][nx] = true;
				} else {
					q.add(new Pair(ny, nx, t + 1));
					visited[ny][nx] = true;
				}
			}
		}
		return -1;
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= n || x >= n);
	}

	static class Pair {
		int y, x, time;

		public Pair(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}

	}
}