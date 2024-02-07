

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int cnt;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {

			n = Integer.parseInt(br.readLine()); // 배열 크기

			map = new int[n][n]; // 맵 선언

			// 맵 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ArrayList<Integer> roomCnt = new ArrayList<>(); // cnt
			ArrayList<int[]> roomIdx = new ArrayList<>(); // 좌표
			ArrayList<Integer> maxIdx = new ArrayList<>(); // 좌표값

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					visited = new boolean[n][n]; // 방문체크
					cnt = 1;
					loop(i, j);
					roomCnt.add(cnt);
					roomIdx.add(new int[] { i, j });
				}
			}
			int roomMax = Collections.max(roomCnt);
			for (int i = 0; i < roomCnt.size(); i++) {
				if (roomMax == roomCnt.get(i)) {
					maxIdx.add(map[roomIdx.get(i)[0]][roomIdx.get(i)[1]]);
				}
			}
			int minStart = Collections.min(maxIdx);
			
			sb.append("#").append(t).append(" ").append(minStart).append(" ")
					.append(roomMax).append("\n");

		}
		System.out.println(sb);
	}

	// 재귀 함수
	static void loop(int y, int x) {
		if (visited[y][x]) {
			return;
		}
		visited[y][x] = true;
		// 우,좌,하,상
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}
			if (visited[ny][nx]) {
				continue;
			}
			if (map[ny][nx] - map[y][x] != 1) {
				continue;
			}
			cnt++;
			loop(ny, nx);
		}
		return;
	}
}
