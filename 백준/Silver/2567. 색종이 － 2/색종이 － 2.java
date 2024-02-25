import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int ans = 0;
    static Queue<int[]> q = new ArrayDeque<>();
    static boolean[][] arr = new boolean[101][101], visited = new boolean[101][101];
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());// 색종이수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int j = y; j < y + 10; j++) {
                for (int k = x; k < x + 10; k++) {
                    arr[j][k] = true;
                }
            }
        }

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (!arr[i][j] && !visited[i][j]) {
                    dfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= 101 || nx >= 101) {
                continue;
            }
            if (!visited[ny][nx]) {
                if (arr[ny][nx]) {
                    ans += 1;
                } else {
                    visited[ny][nx] = true;
                    dfs(ny, nx);
                }
            }
        }
    }

}
