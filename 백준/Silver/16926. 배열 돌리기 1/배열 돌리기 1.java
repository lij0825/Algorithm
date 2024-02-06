import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] roll; // 기본 배열
	static int n; // 세로크기
	static int m; // 가로크기
	static int r; // 회전횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		roll = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				roll[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// (-1,0)(0,-1)(1,0)(0,1) 대충 이런순서
		for (int i = 0; i < Math.min(n, m) / 2; i++) {
			rolling(i, i, r);
		}
		// 출력
		for (int[] is : roll) {
			System.out.println(Arrays.toString(is).replaceAll("[,\\[\\]]", ""));
		}
	}

	static void rolling(int y, int x, int cnt) {

		if (cnt == 0) {
			return;
		}
		int save = roll[y][x];

		for (int i = x; i < m - x - 1; i++) {
			roll[y][i] = roll[y][i + 1];
		}

		for (int i = y; i < n - y - 1; i++) {
			roll[i][m - x - 1] = roll[i + 1][m - x - 1];
		}

		for (int i = m - x - 2; i >= x; i--) {
			roll[n - y - 1][i + 1] = roll[n - y - 1][i];
		}

		for (int i = n - x - 2; i >= y; i--) {
			roll[i + 1][x] = roll[i][x];
		}
		roll[y + 1][x] = save;
		cnt -= 1;
		rolling(y, x, cnt);
	}
}
