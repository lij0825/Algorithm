import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[][][] smash; // 경로 저장 c,y,x
    static boolean[][][] visited;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        int k = Integer.parseInt(st.nextToken()) + 1; // 부실수있는 벽 개수
        smash = new int[k][n][m]; // 부수기 전이면 [0][][] 부셨으면[1][][]
        visited = new boolean[k][n][m];
        // 0 : 통로, 1: 벽
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        br.close();
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, 0, 0 });
        smash[0][0][0] = 1;
        visited[0][0][0] = true;
        while (!q.isEmpty()) {
            int[] cyx = q.poll();
            int c = cyx[0];
            int y = cyx[1];
            int x = cyx[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && nx >= 0 && ny < n && nx < m) {
                    if (!visited[c][ny][nx]) {
                        visited[c][ny][nx] = true;

                        if (map[ny][nx] == 0) {
                            smash[c][ny][nx] = smash[c][y][x] + 1;
                            q.add(new int[] { c, ny, nx });
                        } else if (c + 1 < k) {
                            smash[c + 1][ny][nx] = smash[c][y][x] + 1;
                            q.add(new int[] { c + 1, ny, nx });
                        }
                    }
                }
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            if ((smash[i][n - 1][m - 1]) == 0) {
                res[i] = -1;
            } else {
                res[i] = smash[i][n - 1][m - 1];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (res[i] <= 0) {
                continue;
            }
            ans = Math.min(ans, res[i]);
        }
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}