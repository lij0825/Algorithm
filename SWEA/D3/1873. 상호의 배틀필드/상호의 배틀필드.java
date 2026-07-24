import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	static int H, W, N;
	static char[] commandList;
	static String[][] board;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] input = br.readLine().split(" ");

			H = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			board = new String[H][W];

			for (int i = 0; i < H; i++) {
				input = br.readLine().split("");
				for (int j = 0; j < W; j++) {
					board[i][j] = input[j];
				}
			}

			int N = Integer.parseInt(br.readLine());
			commandList = br.readLine().toCharArray();

			for (int k = 0; k < N; k++) {
				command(k);
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

	// <>^v
	static void command(int index) {
		if (commandList[index] == 'S') {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (isTank(i, j)) {
						if (board[i][j].equals("^"))
							shoot(i, j, 0);
						else if (board[i][j].equals("v"))
							shoot(i, j, 1);
						else if (board[i][j].equals("<"))
							shoot(i, j, 2);
						else if (board[i][j].equals(">"))
							shoot(i, j, 3);
						return;
					}
				}
			}
		} else if (commandList[index] == 'U') {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (isTank(i, j)) {
						if (isIn(i - 1, j) && isFlat(i - 1, j)) {
							board[i][j] = ".";
							board[i - 1][j] = "^";
						} else {
							board[i][j] = "^";
						}
						return;
					}
				}
			}
		} else if (commandList[index] == 'D') {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (isTank(i, j)) {
						if (isIn(i + 1, j) && isFlat(i + 1, j)) {
							board[i][j] = ".";
							board[i + 1][j] = "v";
						} else {
							board[i][j] = "v";
						}
						return;
					}
				}
			}
		} else if (commandList[index] == 'L') {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (isTank(i, j)) {
						if (isIn(i, j - 1) && isFlat(i, j - 1)) {
							board[i][j] = ".";
							board[i][j - 1] = "<";
						} else {
							board[i][j] = "<";
						}
						return;
					}
				}
			}
		} else if (commandList[index] == 'R') {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (isTank(i, j)) {
						if (isIn(i, j + 1) && isFlat(i, j + 1)) {
							board[i][j] = ".";
							board[i][j + 1] = ">";
						} else {
							board[i][j] = ">";
						}
						return;
					}
				}
			}
		}
	}

	static void shoot(int y, int x, int dir) {
		while (isIn(y, x)) {
			y += dy[dir];
			x += dx[dir];
			if (!isIn(y, x)) {
				break;
			}
			if (board[y][x].equals("#")) {
				break;
			}
			if (board[y][x].equals("*")) {
				board[y][x] = ".";
				break;
			}
		}
	}

	static boolean isTank(int y, int x) {
		return board[y][x].equals("<") || board[y][x].equals(">") || board[y][x].equals("^") || board[y][x].equals("v");
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && y < H && x >= 0 && x < W;
	}

	static boolean isFlat(int y, int x) {
		return board[y][x].equals(".");
	}
}
