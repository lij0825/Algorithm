import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static String[][] board;
	static List<int[]> teachers = new ArrayList<int[]>();
	static List<int[]> empty = new ArrayList<int[]>();
	static boolean flag = false;

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new String[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = st.nextToken();
				if (board[i][j].equals("T")) {
					teachers.add(new int[] { i, j });
				} else if (board[i][j].equals("X")) {
					empty.add(new int[] { i, j });
				}
			}
		}

		solution(0, 0);

		if (flag) {
			System.out.print("YES");
		} else {
			System.out.print("NO");
		}

	}

	static void solution(int start, int count) {

		if (flag) {
			return;
		}

		if (count == 3) {

			if (checkSafe()) {
				flag = true;
			}
			return;
		}

		for (int i = start; i < empty.size(); i++) {
			int[] pos = empty.get(i);
			board[pos[0]][pos[1]] = "O";
			solution(i + 1, count + 1);
			board[pos[0]][pos[1]] = "X";
		}

	}

	static boolean checkSafe() {

		for (int[] teacher : teachers) {
			int ty = teacher[0];
			int tx = teacher[1];

			for (int i = 0; i < 4; i++) {
				int ny = ty + dy[i];
				int nx = tx + dx[i];

				while (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (board[ny][nx].equals("S")) {
						return false;
					}
					if (board[ny][nx].equals("O")) {
						break;
					}
					ny += dy[i];
					nx += dx[i];
				}
			}
		}

		return true;
	}

}
