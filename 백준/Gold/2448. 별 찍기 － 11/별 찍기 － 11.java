

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static char[][] star;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		star = new char[n][2 * n - 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				star[i][j] = ' ';
			}
		}

		loop(n, 0, n - 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				sb.append(star[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void loop(int cnt, int y, int x) {
		if (cnt == 3) {
			star[y][x] = '*';
			star[y + 1][x - 1] = star[y + 1][x + 1] = '*';
			star[y + 2][x - 2] = star[y + 2][x - 1] = star[y + 2][x] = star[y + 2][x + 1] = star[y + 2][x + 2] = '*';
			return;
		}
		int size = cnt / 2;
		loop(size, y, x);
		loop(size, y + size, x + size);
		loop(size, y + size, x - size);
	}
}
