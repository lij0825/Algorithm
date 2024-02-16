import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int cnt = 1;
	static int[][] maze;
	static boolean[][] visited;
	static int[][] way;
	static Queue<int[]> q = new ArrayDeque<>();
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		maze = new int[n][m];
		way = new int[n][m];
		visited = new boolean[n][m]; // 방문 체크
		// 미로 입력 ----------------------------------------------------
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(str[j]);
			}
		}
		// --------------------------------------------------------------
		loop(0, 0); // 반복, 시작점
		System.out.println(way[n - 1][m - 1]);

	}

	static void loop(int y, int x) {
		q.add(new int[] { y, x });
		way[y][x] = 1;
		visited[y][x] = true;
		while (!q.isEmpty()) {
			int[] yx = q.poll();
			if (yx[0] == n - 1 && yx[1] == m - 1) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				int ny = yx[0] + dy[i];
				int nx = yx[1] + dx[i];
				if (ny < n && nx < m && ny >= 0 && nx >= 0 && !visited[ny][nx] && maze[ny][nx] == 1) {
					q.add(new int[] { ny, nx });
					visited[ny][nx] = true;
					way[ny][nx] = way[yx[0]][yx[1]] + 1;
				}
			}
		}
	}
}
