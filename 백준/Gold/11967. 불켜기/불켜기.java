import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dy = {0, 0, 1, -1}, dx = {1, -1, 0, 0};
	static int N, M, ans = 1;
	static ArrayList<int[]>[][] switches;
	static boolean[][] visited;
	static boolean[][] canGo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 헛간 크기
		M = Integer.parseInt(st.nextToken()); // 스위치 정보의 개수

		switches = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switches[i][j] = new ArrayList<>();
			}
		}

		visited = new boolean[N][N];
		canGo = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			switches[y][x].add(new int[] {b, a});
		}

		bfs(0, 0);

		System.out.println(ans);
	}

	static void bfs(int y, int x) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {y, x});
		visited[y][x] = true;
		canGo[y][x] = true;

		while (!q.isEmpty()) {
			int[] qq = q.poll();
			y = qq[0];
			x = qq[1];

			for (int[] next : switches[y][x]) {
				int ny = next[0];
				int nx = next[1];

				if (!canGo[ny][nx]) {
					canGo[ny][nx] = true;
					ans++;
					for (int i = 0; i < 4; i++) {
						int ty = ny + dy[i];
						int tx = nx + dx[i];
						if (isIn(ty, tx) && visited[ty][tx]) {
							visited[ny][nx] = true;
							q.add(new int[] {ny, nx});
                            break;
						}
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (isIn(ny, nx) && !visited[ny][nx] && canGo[ny][nx]) {
					visited[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
			}
		}
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && x >= 0 && y < N && x < N;
	}
}