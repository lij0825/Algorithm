import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[9][9];

		// 입력
		for (int i = 0; i < 9; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(str[j]);
			}
		}
		solution();
	}

	static void solution() {

		boolean flag = true;

		int y = -1, x = -1;

		boolean meetZero = false;
		for (int i = 0; i < 9; i++) {
			if (meetZero) {
				break;
			}
			for (int j = 0; j < 9; j++) {
				if (meetZero) {
					break;
				}
				if (board[i][j] == 0) {
					meetZero = true;
					flag = false;
					y = i;
					x = j;
				}
			}
		}

		if (flag) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
			}
			sb.deleteCharAt(sb.length() - 1);
			System.out.print(sb);
			System.exit(0);
		}

		for (int i = 1; i <= 9; i++) {
			if (numChk(y, x, i)) {
				board[y][x] = i;
				solution();
				board[y][x] = 0;
			}
		}
	}

	static boolean numChk(int y, int x, int num) {
		for (int i = 0; i < 9; i++) {
			if (board[y][i] == num || board[i][x] == num)
				return false;
		}
		int by = (y / 3) * 3;
		int bx = (x / 3) * 3;
		for (int i = by; i < by + 3; i++) {
			for (int j = bx; j < bx + 3; j++) {
				if (board[i][j] == num)
					return false;
			}
		}
		return true;
	}
}