import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	static int N;
	static char[][] board;
	static boolean flag;
	static int[] dy = { 0, 1, 1, -1 };
	static int[] dx = { 1, 0, 1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int TC = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine());
			board = new char[N][N];
			flag = false;

			for (int i = 0; i < N; i++) {
				board[i] = br.readLine().toCharArray();
			}

			lineCheck();

			if (flag) {
				bw.write("#" + tc + " YES\n");
			} else {
				bw.write("#" + tc + " NO\n");
			}
		}

		bw.flush();
		bw.close();

	}

	static void lineCheck() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (board[i][j] == 'o') { // 돌있으면 체크
					for (int k = 0; k < 4; k++) {
						int cnt = 1;
						for (int l = 1; l <= 5; l++) {
							int ny = i + dy[k] * l;
							int nx = j + dx[k] * l;
							if (!isIn(ny, nx)) {
								break;
							}
							if (board[ny][nx] == 'o') {
								cnt++;
							} else {
								break;
							}
						}
						if (cnt >= 5) {
							flag = true;
							return;
						}
					}
				}
			}
		}
	}

	static boolean isIn(int y, int x) {
		return y >= 0 && x >= 0 && y < N && x < N;
	}

}
