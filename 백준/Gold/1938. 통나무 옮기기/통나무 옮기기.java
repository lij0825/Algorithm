import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

	static int N;
	static String[][] board;
	static boolean[][][] visited;
	static List<Pair> end = new ArrayList<>();
	static Tree tree;
	static int[] dy = { 0, 1, 0, -1, -1, 1, 1, -1 }, dx = { 1, 0, -1, 0, 1, 1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		board = new String[N][N];
		visited = new boolean[2][N][N];

		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				board[i][j] = input[j];
			}
		}
		tree = new Tree(0, 0, 0, 0);
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (flag && board[i][j].equals("B")) {
					flag = false;
					if (isIn(i, j + 1) && board[i][j + 1].equals("B")) {
						tree.y = i;
						tree.x = j + 1;
					} else {
						tree.y = i + 1;
						tree.x = j;
						tree.type = 1;
					}
				}
				if (board[i][j].equals("E")) {
					end.add(new Pair(i, j));
				}
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {

		visited[tree.type][tree.y][tree.x] = true;
		Queue<Tree> q = new ArrayDeque<>();
		q.add(tree);
		int ans = 0;
		while (!q.isEmpty()) {
			Tree t = q.poll();
			if (endChk(t)) {
				ans = t.cnt;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int ny = t.y + dy[i];
				int nx = t.x + dx[i];
				if (t.type == 1) {
					if (!isIn(ny, nx) || !isIn(ny - 1, nx) || !isIn(ny + 1, nx)) {
						continue;
					}
					if (visited[t.type][ny][nx]) {
						continue;
					}
					if (board[ny][nx].equals("1") || board[ny - 1][nx].equals("1") || board[ny + 1][nx].equals("1")) {
						continue;
					}
				} else {
					if (!isIn(ny, nx) || !isIn(ny, nx - 1) || !isIn(ny, nx + 1)) {
						continue;
					}
					if (visited[t.type][ny][nx] || board[ny][nx].equals("1")) {
						continue;
					}
					if (board[ny][nx].equals("1") || board[ny][nx - 1].equals("1") || board[ny][nx + 1].equals("1")) {
						continue;
					}
				}
				visited[t.type][ny][nx] = true;
				q.add(new Tree(ny, nx, t.type, t.cnt + 1));
			}
			if (turnChk(t.y, t.x)) {
				int newrc = t.type == 0 ? 1 : 0;
				if (visited[newrc][t.y][t.x]) {
					continue;
				}
				visited[newrc][t.y][t.x] = true;
				q.add(new Tree(t.y, t.x, newrc, t.cnt + 1));
			}
		}
		return ans;
	}

	static class Tree {
		int y, x, type, cnt;

		public Tree(int y, int x, int type, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
			this.cnt = cnt;
		}

	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= N);
	}

	static boolean endChk(Tree t) {
		int cnt = 0;
		if (t.type == 1) {
			for (Pair p : end) {
				if (p.y == t.y && p.x == t.x) {
					cnt += 1;
				}
				if (p.y == t.y - 1 && p.x == t.x) {
					cnt += 1;
				}
				if (p.y == t.y + 1 && p.x == t.x) {
					cnt += 1;
				}
			}
		} else {
			for (Pair p : end) {
				if (p.y == t.y && p.x == t.x) {
					cnt += 1;
				}
				if (p.y == t.y && p.x == t.x - 1) {
					cnt += 1;
				}
				if (p.y == t.y && p.x == t.x + 1) {
					cnt += 1;
				}
			}
		}

		return cnt == 3 ? true : false;
	}

	static boolean turnChk(int y, int x) {
		for (int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (!isIn(ny, nx) || board[ny][nx].equals("1")) {
				return false;
			}
		}
		return true;
	}

}