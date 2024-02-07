

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] temp;
	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로
		int r = Integer.parseInt(st.nextToken()); // 명령 개수

		map = new int[n][m];
		temp = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		int[] order = new int[r];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < order.length; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < order.length; i++) {
			switch (order[i]) {

			case 1:
				filpV();
				break;
			case 2:
				filpH();
				break;
			case 3:
				rotation90();
				break;
			case 4:
				rotation90();
				rotation90();
				rotation90();
				break;
			case 5:
				rotation4();
				break;
			case 6:
				rotation4();
				rotation4();
				rotation4();
				break;
			}
		}

		for (int[] is : map) {
			System.out.println(Arrays.toString(is).replaceAll("[,\\[\\]]", ""));
		}

	}

	// 상하반전
	static void filpV() {
		int col = map.length;
		int row = map[0].length;
		temp = new int[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				temp[col - i - 1][j] = map[i][j];
			}
		}
		map = temp;
	}

	// 좌우반전
	static void filpH() {
		int col = map.length;
		int row = map[0].length;
		temp = new int[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				temp[i][row - j - 1] = map[i][j];
			}
		}
		map = temp;
	}

	// 오른쪽으로 90도
	static void rotation90() {
		int col = map.length;
		int row = map[0].length;
		temp = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				temp[i][j] = map[col - j - 1][i];
			}
		}
		map = temp;
	}

	// 4분면으로 나눠서 돌리기
	static void rotation4() {
		int col = map.length;
		int row = map[0].length;
		temp = new int[col][row];
		// 1 -> 2
		for (int i = 0; i < col / 2; i++) {
			for (int j = 0; j < row / 2; j++) {
				temp[i][row / 2 + j] = map[i][j];
			}
		}
		// 2 -> 4
		for (int i = 0; i < col / 2; i++) {
			for (int j = row / 2; j < row; j++) {
				temp[col / 2 + i][j] = map[i][j];
			}
		}
		// 4 -> 3
		for (int i = col / 2; i < col; i++) {
			for (int j = row / 2; j < row; j++) {
				temp[i][j - row / 2] = map[i][j];
			}
		}
		// 4 -> 1
		for (int i = col / 2; i < col; i++) {
			for (int j = 0; j < row / 2; j++) {
				temp[i - col / 2][j] = map[i][j];
			}
		}
		map = temp;
	}
}
