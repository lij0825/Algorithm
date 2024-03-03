import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static boolean visit[][];
	static int map[][];
	static int size[][];

	private static final int INF = Integer.MAX_VALUE;
	static int N, nowX, nowY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int count = 1;

		String temp = "";
		while (!(temp = br.readLine()).isEmpty()) {
			N = Integer.parseInt(temp);
			if (N == 0) {
				break;
			}

			map = new int[N][N];
			visit = new boolean[N][N];
			size = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					size[i][j] = INF;
				}
			}

			BFS(0, 0, map[0][0]);
			sb.append("Problem " + count + ": " + size[N - 1][N - 1]).append('\n');
			count++;
		}

		System.out.println(sb);

	} // End of main

	private static void BFS(int x, int y, int roopy) {
		PriorityQueue<rupee> q = new PriorityQueue<>();
		visit[x][y] = true;
		q.offer(new rupee(x, y, roopy));

		while (!q.isEmpty()) {

			rupee rp = q.poll();

			for (int i = 0; i < 4; i++) {
				nowX = rp.x + dx[i];
				nowY = rp.y + dy[i];

				if (isIN() && !visit[nowX][nowY] && size[nowX][nowY] > (map[nowX][nowY] + rp.size)) {
					size[nowX][nowY] = map[nowX][nowY] + rp.size;
					visit[nowX][nowY] = true;
					q.offer(new rupee(nowX, nowY, size[nowX][nowY]));
				}

			}
		}

	}

	static class rupee implements Comparable<rupee> {
		int x;
		int y;
		int size;

		public rupee(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(rupee o) {
			return size - o.size;
		}
	}

	private static boolean isIN() {
		return (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N);
	} 

}