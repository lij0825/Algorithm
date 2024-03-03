
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int[][] brick, copyBrick;
	static int h, w, n, ans;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
	static int[] drop;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 구슬 놓을수 있는 횟수
			w = Integer.parseInt(st.nextToken()); // 높이
			h = Integer.parseInt(st.nextToken()); // 너비
			drop = new int[n];
			brick = new int[h][w]; // 원본
			copyBrick = new int[h][w]; // 복사용
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					brick[i][j] = Integer.parseInt(st.nextToken());
					copyBrick[i][j] = brick[i][j];
				}
			}
			drop(0);
			sb.append("#" + t + " " + ans + "\n");
		} // testcase
		System.out.println(sb);
	}// main

	static void drop(int idx) {
		if (idx == n) {

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < h; j++) {
					if (copyBrick[j][drop[i]] > 0) {
						brickBoom(copyBrick[j][drop[i]], j, drop[i]);
						downBrick();
						break;
					}
				}
			}

			int bcnt = brickCnt();

			if (ans == 0) {
				return;
			}
			if (bcnt == 0) {
				ans = 0;
				return;
			}

			ans = Math.min(ans, bcnt);

			for (int i = 0; i < h; i++) {
				copyBrick[i] = brick[i].clone();
			}

			return;
		}
		for (int i = 0; i < w; i++) {
			drop[idx] = i;
			drop(idx + 1);
		}
	}

	// 남은 브릭 체크
	static int brickCnt() {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (copyBrick[i][j] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 연쇄 폭발
	static void brickBoom(int power, int y, int x) {
		copyBrick[y][x] = 0;
		if (power == 1) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < power; j++) {
				int ny = y + dy[i] * j;
				int nx = x + dx[i] * j;
				if (!isIn(ny, nx)) {
					continue;
				}
				if (copyBrick[ny][nx] > 0) {
					brickBoom(copyBrick[ny][nx], ny, nx);
				}
			}
		}
	}

	// 폭발 후 벽돌 내리기
	static void downBrick() {
		Stack<Integer> s;
		for (int i = 0; i < w; i++) {
			s = new Stack<>();
			for (int j = 0; j < h; j++) {
				if (copyBrick[j][i] != 0) {
					s.push(copyBrick[j][i]);
					copyBrick[j][i] = 0;
				}
			}
			int low = h - 1;
			while (!s.isEmpty()) {
				copyBrick[low--][i] = s.pop();
			}
		}
	}

	// range check
	static boolean isIn(int y, int x) {
		return y < 0 || x < 0 || y >= h || x >= w ? false : true;
	}
}
