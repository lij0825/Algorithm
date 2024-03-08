import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 가로
		c = Integer.parseInt(st.nextToken()); // 세로
		int starty = 0;
		int startx = 0;
		int endy = 0;
		int endx = 0;
		ArrayList<Pair> Xlist = new ArrayList<>();

		int[][] board = new int[c][r];
		int Xcnt = 1;
		for (int i = 0; i < c; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < r; j++) {
				String token = str[j];
				if (token.equals("#")) {
					board[i][j] = -1;
				}
				if (token.equals("X")) {
					board[i][j] = Xcnt++;
					Xlist.add(new Pair(i, j));
				}
				if (token.equals("S")) {
					board[i][j] = -2;
					starty = i;
					startx = j;
				}
				if (token.equals("E")) {
					board[i][j] = -3;
					endy = i;
					endx = j;
				}
			}
		}

		Xcnt--;

		int[] Xcombi = new int[Xcnt];
		for (int i = 1; i <= Xcnt; i++) {
			Xcombi[i - 1] = i;
		}

		List<int[]> permutations = generatePermutations(Xcombi);

		int min = Integer.MAX_VALUE;
		int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
		if (Xcnt == 0) {
			Queue<Pair> q = new ArrayDeque<>();
			int[][] visited = new int[c][r];
			q.add(new Pair(starty, startx));
			visited[starty][startx] = 1;
			while (!q.isEmpty()) {
				Pair p = q.poll();
				int y = p.y;
				int x = p.x;
				for (int k = 0; k < 4; k++) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					if (!isIn(ny, nx)) {
						continue;
					}
					if (board[ny][nx] == -1) {
						continue;
					}
					if (visited[ny][nx] != 0) {
						continue;
					}
					visited[ny][nx] = visited[y][x] + 1;
					q.add(new Pair(ny, nx));
					if (board[ny][nx] == -3) {
						min = Math.min(min, visited[y][x]);
						q.clear();
						break;
					}
				}
			}
		}

		for (int i = 0; i < permutations.size(); i++) {
			int[] seq = permutations.get(i);
			int sum = 0;
			int cnt = 0;

			Queue<Pair> q = new ArrayDeque<>();
			int[][] visited = new int[c][r];
			q.add(new Pair(starty, startx));
			visited[starty][startx] = 1;
			while (!q.isEmpty()) {
				Pair p = q.poll();
				int y = p.y;
				int x = p.x;
				for (int k = 0; k < 4; k++) {
					int ny = y + dy[k];
					int nx = x + dx[k];
					if (!isIn(ny, nx)) {
						continue;
					}
					if (board[ny][nx] == -1) {
						continue;
					}
					if (visited[ny][nx] != 0) {
						continue;
					}
					visited[ny][nx] = visited[y][x] + 1;
					q.add(new Pair(ny, nx));
					if (Xcnt > cnt && board[ny][nx] == seq[cnt]) {
						cnt += 1;
						sum += visited[y][x];
						visited = new int[c][r];
						visited[ny][nx] = 1;
						q.clear();
						q.add(new Pair(ny, nx));
						break;
					}
					if (Xcnt == cnt && board[ny][nx] == -3) {
						sum += visited[y][x];
						min = Math.min(min, sum);
						q.clear();
						break;
					}
					if (sum >= min) {
						q.clear();
						break;
					}
				}
			}
		}

		System.out.println(min);
	}

	static List<int[]> generatePermutations(int[] array) {
		List<int[]> result = new ArrayList<>();
		generatePermutationsHelper(array, 0, result);
		return result;
	}

	static void generatePermutationsHelper(int[] array, int index, List<int[]> result) {
		if (index == array.length - 1) {
			result.add(array.clone());
			return;
		}

		for (int i = index; i < array.length; i++) {
			swap(array, index, i);
			generatePermutationsHelper(array, index + 1, result);
			swap(array, index, i);
		}
	}

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= c || x >= r);
	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Pair [y=" + y + ", x=" + x + "]";
		}

	}
}