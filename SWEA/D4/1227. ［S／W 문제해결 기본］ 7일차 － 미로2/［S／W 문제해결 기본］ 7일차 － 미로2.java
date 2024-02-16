import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static Queue<int[]> q;
    static boolean[][] visited;
    static int[][] maze = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            q = new ArrayDeque<>();
            visited = new boolean[100][100];
            int t = Integer.parseInt(br.readLine());
            for (int i = 0; i < 100; i++) {
                String[] str = br.readLine().split("");
                for (int j = 0; j < 100; j++) {
                    maze[i][j] = Integer.parseInt(str[j]);
                }
            }
            sb.append("#" + t);
            if (bfs(1, 1)) {
                sb.append(" " + 1);
            } else {
                sb.append(" " + 0);
            }
            sb.append("\n");

        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    static boolean bfs(int y, int x) {
        q.add(new int[] { y, x });
        visited[y][x] = true;
        int[] dy = { 1, 0, -1, 0 };
        int[] dx = { 0, 1, 0, -1 };
        while (!q.isEmpty()) {
            int[] yx = q.poll();
            int r = yx[0];
            int c = yx[1];
            for (int i = 0; i < 4; i++) {
                int ny = r + dy[i];
                int nx = c + dx[i];
                if (ny < 0 || nx < 0 || ny >= 100 || nx >= 100) {
                    continue;
                }
                if (visited[ny][nx] || maze[ny][nx] == 1) {
                    continue;
                }
                if (maze[ny][nx] == 3) {
                    return true;
                }
                if (maze[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.add(new int[] { ny, nx });
                }
            }
        }
        return false;
    }
}