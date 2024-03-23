import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		char[][] board = new char[n][n];

		for (int i = 0; i < board.length; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < board.length; j++) {
				board[i][j] = str[j].charAt(0);
			}
		}

		char[][] map = new char[n][n];

		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }, dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] != '.') {
					map[i][j] = '*';
				} else {
					int sum = 0;
					for (int k = 0; k < 8; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if (ny < 0 || nx < 0 || nx >= n || ny >= n) {
							continue;
						}
						if (board[ny][nx] == '.') {
							continue;
						}
						sum += board[ny][nx] - '0';
					}

					if (sum > 9) {
						map[i][j] = 'M';
					} else {
						map[i][j] = (char) (sum + '0');
					}
				}
			}
		}

		for (char[] str : map) {
			sb.append(Arrays.toString(str).replaceAll("[' '\\[,\\]]", "")).append("\n");
		}

		System.out.println(sb);
	}
}