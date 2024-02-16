
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int[][] map;
    static int[][] day;
    static int n;
    static int m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로
        map = new int[m][n]; // 토마토
        day = new int[m][n]; // 날짜
        visited = new boolean[m][n]; // 방문체크
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    visited[i][j] = true;
                    q.add(new int[] { i, j });
                }
            }
        }
        if (!q.isEmpty()) {
            bfs(q);
        }
        int finalday = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
                finalday = Math.max(finalday, day[i][j]);
            }
        }

        System.out.println(finalday);

    }

    static void bfs(Queue<int[]> q) {
        int[] yx = q.poll();
        int y = yx[0];
        int x = yx[1];
        int[] dy = { 1, 0, -1, 0 };
        int[] dx = { 0, 1, 0, -1 };
        q.add(new int[] { y, x });
        while (!q.isEmpty()) {
            yx = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = yx[0] + dy[i];
                int nx = yx[1] + dx[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                if (visited[ny][nx] || map[ny][nx] != 0) {
                    continue;
                }
                visited[ny][nx] = true;
                day[ny][nx] = day[yx[0]][yx[1]] + 1;
                map[ny][nx] = 1;
                q.add(new int[] { ny, nx });
            }
        }
    }
}
