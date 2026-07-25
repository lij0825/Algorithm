import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

// 혁진이의 프로그램 검증
public class Solution {

	static char[][] board;
	static boolean[][][][] visited;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 }; // 0상,1하,2좌,3우
	static int R, C;
	static boolean flag;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			flag = false;
			Node node = new Node(0, 0, 3, 0);

			String[] input = br.readLine().split(" ");
			R = Integer.parseInt(input[0]);
			C = Integer.parseInt(input[1]);

			board = new char[R][C];
			boolean hasEnd = false;
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				board[i] = line.toCharArray();

				if (line.contains("@")) {
					hasEnd = true;
				}
			}

			if (!hasEnd) {
				bw.write("#" + t + " NO" + "\n");
				continue;
			}

			visited = new boolean[R][C][4][16];

			bfs();

			bw.write("#" + t + " " + (flag ? "YES" : "NO") + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

	static void bfs() {

		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, 3, 0));

		while (!queue.isEmpty()) {
			Node cur = queue.poll();

			if (visited[cur.y][cur.x][cur.dir][cur.memory]) {
				continue;
			}
			visited[cur.y][cur.x][cur.dir][cur.memory] = true;

			char cmd = board[cur.y][cur.x];
			int nextDir = cur.dir;
			int nextMem = cur.memory;

			if (cmd == '@') {
				flag = true;
				break;
			}

			switch (cmd) {
			case '<':
				nextDir = 2;
				break;
			case '>':
				nextDir = 3;
				break;
			case '^':
				nextDir = 0;
				break;
			case 'v':
				nextDir = 1;
				break;
			case '_':
				nextDir = (nextMem == 0) ? 3 : 2;
				break;
			case '|':
				nextDir = (nextMem == 0) ? 1 : 0;
				break;
			case '+':
				nextMem = (nextMem == 15) ? 0 : nextMem + 1;
				break;
			case '-':
				nextMem = (nextMem == 0) ? 15 : nextMem - 1;
				break;
			default:
				if (cmd >= '0' && cmd <= '9') {
					nextMem = cmd - '0';
				}
				break;
			}

			if (cmd == '?') {
				for (int d = 0; d < 4; d++) {
					int ny = (cur.y + dy[d] + R) % R;
					int nx = (cur.x + dx[d] + C) % C;
					queue.offer(new Node(ny, nx, d, nextMem));
				}
			} else {
				int ny = (cur.y + dy[nextDir] + R) % R;
				int nx = (cur.x + dx[nextDir] + C) % C;
				queue.offer(new Node(ny, nx, nextDir, nextMem));
			}
		}
	}

	static class Node {
		int y, x;
		int dir; // 현재 이동 방향
		int memory; // 현재 메모리 값

		public Node(int y, int x, int dir, int memory) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.memory = memory;
		}
	}

}
