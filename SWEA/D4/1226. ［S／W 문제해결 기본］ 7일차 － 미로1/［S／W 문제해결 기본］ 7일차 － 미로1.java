import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

//미로1
public class Solution {

	static int[][] board;
	static boolean[][] visited;
	static Pair start, end;
	static int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
	static Queue<Pair> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int tc = 1; tc <= 10; tc++) {

			int T = Integer.parseInt(br.readLine());

			board = new int[16][16];
			visited = new boolean[16][16];

			for (int i = 0; i < 16; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0; j < 16; j++) {
					board[i][j] = Integer.parseInt(input[j]);
					if (board[i][j] == 2) {
						start = new Pair(i, j);
					}
					if (board[i][j] == 3) {
						end = new Pair(i, j);
					}
				}
			}

			int reslut = bfs();
			bw.write("#" + T + " " + reslut + "\n");

		}

		bw.flush();
		bw.close();

	}

	static int bfs() {

		q.clear();
		q.add(start);
		visited[start.y][start.x] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (!isIn(ny, nx)) {
					continue;
				}
				if (visited[ny][nx] || board[ny][nx] == 1) {
					continue;
				}
				if (board[ny][nx] == 3) {
					return 1;
				}
				q.add(new Pair(ny, nx));
				visited[ny][nx] = true;
			}
		}

		return 0;
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && x >= 0 && y < 16 && x < 16;
	}

	static class Pair {
		int y, x;

		Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
