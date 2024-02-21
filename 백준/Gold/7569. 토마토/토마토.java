import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][][] visited;
    static int[][][] map;
    static int[][][] day;
    static int n, m, h;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로
        h = Integer.parseInt(st.nextToken()); // 높이
        map = new int[h][m][n]; // 토마토
        day = new int[h][m][n]; // 날짜
        visited = new boolean[h][m][n]; // 방문체크
        Queue<int[]> q = new ArrayDeque<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[k][i][j] == 1) {
                        visited[k][i][j] = true;
                        q.add(new int[] { k, i, j });
                    }
                }
            }
        }
        if (!q.isEmpty()) {
            bfs(q);
        }
        int finalday = 0;
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[k][i][j] == 0) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                    finalday = Math.max(finalday, day[k][i][j]);
                }
            }
        }
        System.out.println(finalday);
    }

    static void bfs(Queue<int[]> q) {
        int[] zyx = q.poll();
        int z = zyx[0];
        int y = zyx[1];
        int x = zyx[2];
        int[] dz = { 1, -1, 0, 0, 0, 0 };
        int[] dy = { 0, 0, 1, -1, 0, 0 };
        int[] dx = { 0, 0, 0, 0, 1, -1 };
        q.add(new int[] { z, y, x });
        while (!q.isEmpty()) {
            zyx = q.poll();
            for (int i = 0; i < 6; i++) {
                int nz = zyx[0] + dz[i];
                int ny = zyx[1] + dy[i];
                int nx = zyx[2] + dx[i];
                if (nz < 0 || nx < 0 || ny < 0 || nx >= n || ny >= m || nz >= h) {
                    continue;
                }
                if (visited[nz][ny][nx] || map[nz][ny][nx] != 0) {
                    continue;
                }
                visited[nz][ny][nx] = true;
                day[nz][ny][nx] = day[zyx[0]][zyx[1]][zyx[2]] + 1;
                map[nz][ny][nx] = 1;
                q.add(new int[] { nz, ny, nx });
            }
        }
    }
}
