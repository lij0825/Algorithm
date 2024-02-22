

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static boolean[][] visited;
    static int[][] cntIce, ice;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Queue<int[]> iceQ = new ArrayDeque();
        ice = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int sum = 0;
        int cnt = 0;
        while (sum < 2) {
            sum = 0;
            bfs(iceQ);
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ice[i][j] > 0 && !visited[i][j]) {
                        dfs(i, j);
                        sum += 1;
                    }
                }
            }
            boolean onlywater = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ice[i][j] > 0) {
                        onlywater = false;
                    }
                }
            }
            if (onlywater) {
                System.out.println(0);
                System.exit(0);
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    static void bfs(Queue<int[]> iceQ) {
        findIce(iceQ);
        cntIce = new int[n][m];
        while (!iceQ.isEmpty()) {
            int[] yx = iceQ.poll();
            int y = yx[0];
            int x = yx[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }
                if (ice[ny][nx] == 0) {
                    cntIce[y][x] += 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cntIce[i][j] > 0) {
                    if (ice[i][j] < cntIce[i][j]) {
                        ice[i][j] = 0;
                    } else {
                        ice[i][j] -= cntIce[i][j];
                    }
                }
            }
        }
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                continue;
            }
            if (ice[ny][nx] == 0 || visited[ny][nx]) {
                continue;
            }
            visited[ny][nx] = true;
            dfs(ny, nx);
        }
    }

    static void findIce(Queue<int[]> iceQ) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ice[i][j] > 0) {
                    iceQ.add(new int[] { i, j });
                }
            }
        }
    }
}
