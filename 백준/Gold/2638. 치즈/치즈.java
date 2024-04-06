import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
	static int[][] board, meltingCnt;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		meltingCnt = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean flag = cntOne();

		int day = 0;

		while (flag) {
			dfs(0, 0);
			melting();
			visited = new boolean[N][M];
			day++;
			flag = cntOne();
		}

		System.out.println(day);
	}

	static boolean cntOne() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	static void melting() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (meltingCnt[i][j] > 1) {
					board[i][j] = 0;
				}
				meltingCnt[i][j] = 0;
			}
		}
	}

	static void dfs(int y, int x) {
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (!isIn(ny, nx) || visited[ny][nx]) {
				continue;
			}
			if (board[ny][nx] == 1) {
				meltingCnt[ny][nx] += 1;
			} else {
				dfs(ny, nx);
			}
		}
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= M);
	}

}