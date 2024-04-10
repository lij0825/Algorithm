import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static String[][] board;
	static Pair player;
	static List<Pair> goals;
	// 오 아 왼 위
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int gamecnt = 1;
		while (true) {

			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 가로
			C = Integer.parseInt(st.nextToken()); // 세로

			if (R == 0 && C == 0) {
				break;
			}
			goals = new ArrayList<>();
			sb.append("Game " + gamecnt++ + ": ");

			board = new String[R][C];
			for (int i = 0; i < R; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0; j < C; j++) {
					board[i][j] = input[j];
					if (board[i][j].equals("W")) {
						player = new Pair(i, j);
						goals.add(new Pair(i, j));
						board[i][j] = ".";
					}
					if (board[i][j].equals("w")) {
						player = new Pair(i, j);
						board[i][j] = ".";
					}
					if (board[i][j].equals("+")) {
						goals.add(new Pair(i, j));
					}
					if (board[i][j].equals("B")) {
						goals.add(new Pair(i, j));
					}
				}

			}

			String[] move = br.readLine().split("");
			for (int i = 0; i < move.length; i++) {
				String order = move[i];
				move(order);
				if (chkend()) {
					break;
				}
			}
			if (chkend()) {
				sb.append("complete").append("\n");
			} else {
				sb.append("incomplete").append("\n");
			}
			int py = player.y;
			int px = player.x;
			if (board[py][px].equals("+")) {
				board[py][px] = "W";
			} else {
				board[py][px] = "w";
			}

			for (String[] ss : board) {
				sb.append(Arrays.toString(ss).replaceAll("[\\[\\], ]", "")).append("\n");
			}
		}

		System.out.println(sb);
	}

	static class Pair {
		int y, x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	static void move(String order) {
		switch (order) {
		case "R": {
			int ny = player.y + dy[0];
			int nx = player.x + dx[0];
			pushbox(ny, nx, dy[0], dx[0]);
			break;
		}
		case "D": {
			int ny = player.y + dy[1];
			int nx = player.x + dx[1];
			pushbox(ny, nx, dy[1], dx[1]);
			break;
		}
		case "L": {
			int ny = player.y + dy[2];
			int nx = player.x + dx[2];
			pushbox(ny, nx, dy[2], dx[2]);
			break;
		}
		case "U": {
			int ny = player.y + dy[3];
			int nx = player.x + dx[3];
			pushbox(ny, nx, dy[3], dx[3]);
			break;
		}
		}
		chkgoals();
	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= R || x >= C);
	}

	static void pushbox(int ny, int nx, int dy, int dx) {
		if (!isIn(ny, nx) || board[ny][nx].equals("#")) {
			return;
		}
		// 박스 일때
		if (board[ny][nx].equals("b") || board[ny][nx].equals("B")) {
			String box = board[ny][nx];
			if (board[ny + dy][nx + dx].equals(".")) {
				board[ny][nx] = ".";
				board[ny + dy][nx + dx] = "b";
				player.y = ny;
				player.x = nx;
			}
			if (board[ny + dy][nx + dx].equals("+")) {
				board[ny][nx] = ".";
				board[ny + dy][nx + dx] = "B";
				player.y = ny;
				player.x = nx;
			}
		} else {
			player.y = ny;
			player.x = nx;
		}

	}

	static void chkgoals() {
		for (Pair pair : goals) {
			int y = pair.y;
			int x = pair.x;
			if (board[y][x].equals(".")) {
				board[y][x] = "+";
			}
		}
	}

	static boolean chkend() {
		for (Pair pair : goals) {
			int y = pair.y;
			int x = pair.x;
			if (!board[y][x].equals("B")) {
				return false;
			}
		}
		return true;
	}

}