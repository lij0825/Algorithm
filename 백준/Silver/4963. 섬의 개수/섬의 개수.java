import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 이인준
 * 
 */
public class Main {
	static int w;
	static int h;
	static int[][] land;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int cnt = 0;
			String[] str = br.readLine().split(" ");
			w = Integer.parseInt(str[0]);
			h = Integer.parseInt(str[1]);
			if (w == 0 && w == h) {
				break;
			}
			land = new int[h][w];
			visited = new boolean[h][w];
			StringTokenizer st;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					land[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j] && land[i][j] == 1) {
						visited[i][j] = true;
						loop(i, j);
						cnt += 1;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	static void loop(int y, int x) {
		int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && ny < h && nx < w && land[ny][nx] == 1 && !visited[ny][nx]) {
				visited[ny][nx] = true;
				loop(ny, nx);
			}
		}
	}
}
