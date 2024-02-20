

import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int n, m;
    static int[][] cheese, copycheese;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        cheese = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken()); // 0 빈칸 1 치즈
            }
        }
        int cnt = 0;
        int ans = 0;
        while (true) {
            cnt++;
            ans = 0;
            int sum = 0;
            copycheese = new int[n][m];
            visited = new boolean[n][m];
            visited[0][0] = true;
            DFS(0, 0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cheese[i][j] == copycheese[i][j]) {
                        cheese[i][j] = 0;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cheese[i][j] == 1) {
                        sum += 1;
                    }
                    if (copycheese[i][j] == 1) {
                        ans += 1;
                    }
                }
            }
            if (sum == 0) {
                break;
            }
        }

        System.out.println(cnt + "\n" + ans);
    }

    static void DFS(int y, int x) {
        int[] dy = { 0, 1, 0, -1 };
        int[] dx = { 1, 0, -1, 0 };
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }
            if (cheese[ny][nx] == 1) {
                copycheese[ny][nx] = 1;
            }
            if (cheese[ny][nx] == 0) {
                visited[ny][nx] = true;
                DFS(ny, nx);
            }
        }
    }

}