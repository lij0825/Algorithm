
import java.io.*;

class Main {
    static boolean[][] visited;
    static int[][] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        // ----------------------------------------------------------------
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                String token = str[j];
                if (token.equals("R")) {
                    arr[i][j] = 2;
                }
                if (token.equals("G")) {
                    arr[i][j] = 1;
                }
                if (token.equals("B")) {
                    arr[i][j] = 0;
                }
            }
        }
        // ----------------------------------------------------------------
        int cnt = 0;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                DFS(i, j);
                cnt++;
            }
        }
        int rgcnt = 0;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                RGDFS(i, j);
                rgcnt++;
            }
        }
        System.out.println(cnt + " " + rgcnt);
    }

    static void DFS(int y, int x) {
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
            if (arr[ny][nx] != arr[y][x]) {
                continue;
            }
            visited[ny][nx] = true;
            DFS(ny, nx);
        }
    }

    static void RGDFS(int y, int x) {
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
            if (arr[y][x] > 0 && arr[ny][nx] > 0) {
                visited[ny][nx] = true;
                RGDFS(ny, nx);
            } else if (arr[y][x] == 0 && arr[ny][nx] == 0) {
                visited[ny][nx] = true;
                RGDFS(ny, nx);
            }
        }
    }
}
