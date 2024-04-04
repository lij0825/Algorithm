import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(input[j]);
				}
			}
			sb.append(bfs()).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs() {
		int res = 0;
		PriorityQueue<Road> pq = new PriorityQueue<>((a, b) -> a.sum - b.sum);
		pq.add(new Road(0, 0, board[0][0], board[0][0]));
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			Road road = pq.poll();
			int y = road.y;
			int x = road.x;
			res = road.sum;
			int sum = road.sum;
			if (y == N - 1 && x == N - 1) {
				break;
			}
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (!isIn(ny, nx) || visited[ny][nx]) {
					continue;
				}
				pq.add(new Road(ny, nx, board[ny][nx], sum + board[ny][nx]));
				visited[ny][nx] = true;
			}
		}
		return res;
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= N);
	}

	static class Road {
		int y, x, cost, sum;

		public Road(int y, int x, int cost, int sum) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
			this.sum = sum;
		}

		@Override
		public String toString() {
			return "Road [y=" + y + ", x=" + x + ", cost=" + cost + ", sum=" + sum + "]";
		}

	}
}