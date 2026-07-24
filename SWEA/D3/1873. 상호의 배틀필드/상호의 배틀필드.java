import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 상호의 배틀 필드
public class Solution {

	static int H, W, N;
	static char[][] board;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int tankY, tankX;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] input = br.readLine().split(" ");
			H = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			board = new char[H][W];

			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					board[i][j] = line.charAt(j);

					if (isTank(board[i][j])) {
						tankY = i;
						tankX = j;
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			char[] commandList = br.readLine().toCharArray();

			for (int k = 0; k < N; k++) {
				command(commandList[k]);
			}

			bw.write("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					bw.write(board[i][j]);
				}
				bw.write("\n");
			}
		}

		br.close();
		bw.flush();
		bw.close();
	}

	static void command(char cmd) {
		int dir = -1;
		char tankShape = ' ';

		switch (cmd) {
		case 'U':
			dir = 0;
			tankShape = '^';
			break;
		case 'D':
			dir = 1;
			tankShape = 'v';
			break;
		case 'L':
			dir = 2;
			tankShape = '<';
			break;
		case 'R':
			dir = 3;
			tankShape = '>';
			break;
		case 'S':
			dir = getDir(board[tankY][tankX]);
			shoot(tankY, tankX, dir);
			return;
		}

		board[tankY][tankX] = tankShape;

		int ny = tankY + dy[dir];
		int nx = tankX + dx[dir];

		if (isIn(ny, nx) && board[ny][nx] == '.') {
			board[ny][nx] = tankShape;
			board[tankY][tankX] = '.';
			tankY = ny;
			tankX = nx;
		}
	}

	static void shoot(int y, int x, int dir) {
		while (true) {
			y += dy[dir];
			x += dx[dir];

			if (!isIn(y, x) || board[y][x] == '#') {
				break;
			}
			if (board[y][x] == '*') {
				board[y][x] = '.';
				break;
			}
		}
	}

	static boolean isTank(char c) {
		return c == '<' || c == '>' || c == '^' || c == 'v';
	}

	static int getDir(char c) {
		if (c == '^')
			return 0;
		if (c == 'v')
			return 1;
		if (c == '<')
			return 2;
		if (c == '>')
			return 3;
		return -1;
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && y < H && x >= 0 && x < W;
	}
}