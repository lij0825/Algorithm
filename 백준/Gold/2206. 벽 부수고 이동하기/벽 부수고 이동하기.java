

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[][][] smash; // 경로 저장 c,y,x
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        smash = new int[2][n][m]; // 부수기 전이면 [0][][] 부셨으면[1][][]
        // 0 : 통로, 1: 벽
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, 0, 0 });
        smash[0][0][0] = 1;
        while (!q.isEmpty()) {
            int[] cyx = q.poll();
            int c = cyx[0];
            int y = cyx[1];
            int x = cyx[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }
                if (smash[c][ny][nx] != 0) {
                    continue;
                }
                if (c == 0 && map[ny][nx] != 0) {
                    smash[c + 1][ny][nx] = smash[c][y][x] + 1;
                    q.add(new int[] { 1, ny, nx });
                    continue;
                }
                if (map[ny][nx] != 0) {
                    continue;
                }
                smash[c][ny][nx] = smash[c][y][x] + 1;
                q.add(new int[] { c, ny, nx });
            }
        }
        int ans = -1;
        int before = smash[0][n - 1][m - 1];
        int after = smash[1][n - 1][m - 1];
        if (before == 0 && after == 0) {
            System.out.println(ans);
            System.exit(0);
        } else if (before == 0) {
            ans = after;
        } else if (after == 0) {
            ans = before;
        } else {
            ans = Math.min(after, before);
        }
        System.out.println(ans);
    }

}