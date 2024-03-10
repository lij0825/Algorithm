import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][N];
		int mine = 0;
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				if (str[j].equals("#")) {
					board[i][j] = -1;
				} else {
					board[i][j] = Integer.parseInt(str[j]);
				}
			}
		}
		int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 }, dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] < 0) {
					int zeroCnt = 0;
					for (int k = 0; k < 8; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (!isIn(ny, nx)) {
							continue;
						}
						if (board[ny][nx] == 0) {
							zeroCnt++;
						}
					}
					if (zeroCnt != 0) {
						continue;
					}
					for (int k = 0; k < 8; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (!isIn(ny, nx)) {
							continue;
						}
						if (board[ny][nx] > 0) {
							board[ny][nx] -= 1;
						}
					}
					mine++;
				}
			}
		}
		System.out.println(mine);
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= N);
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