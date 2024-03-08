import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine()); // 가로, 세로, 배추 개수
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int[][] board = new int[y][x];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				board[b][a] = 1;
			}

			boolean[][] visited = new boolean[y][x];

			Queue<int[]> q = new ArrayDeque<>();

			int ans = 0;

			for (int i = 0; i < y; i++) {
				for (int j = 0; j < x; j++) {
					if (board[i][j] == 1 && !visited[i][j]) {
						ans++;
						q.add(new int[] { i, j });
						visited[i][j] = true;
						while (!q.isEmpty()) {
							int[] yx = q.poll();
							int yy = yx[0];
							int xx = yx[1];
							for (int l = 0; l < 4; l++) {
								int ny = yy + dy[l];
								int nx = xx + dx[l];
								if (ny < 0 || nx < 0 || ny >= y || nx >= x) {
									continue;
								}
								if (visited[ny][nx]) {
									continue;
								}
								if (board[ny][nx] == 1) {
									q.add(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							}
						}
					}
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

}