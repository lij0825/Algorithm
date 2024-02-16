import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int[][] mineMap;
    static boolean[][] visited;
    static int N;
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스크 케이스

        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            N = Integer.parseInt(br.readLine()); // 맵 크기
            mineMap = new int[N][N]; // 지뢰 위치
            visited = new boolean[N][N]; // 방문 체크

            for (int i = 0; i < N; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    mineMap[i][j] = (chars[j] == '*') ? -1 : 0; // 0은 빈속 -1은 지뢰
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 주변에 지뢰가 없고 폭탄이 아니면서 방문을 안했을때 bfs
                    if (mineCnt(i, j) == 0 && mineMap[i][j] != -1 && !visited[i][j]) {
                        ans += 1;
                        bfs(i, j);
                    }
                }
            }

            // 전부 돌면서 안눌린거 누름
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineMap[i][j] == 0 && !visited[i][j]) {
                        ans += 1;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    static public void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[y][x] = true;
        queue.add(new int[] { y, x });

        while (!queue.isEmpty()) {
            int[] yx = queue.poll();
            int ny = yx[0];
            int nx = yx[1];
            int cnt = mineCnt(ny, nx);
            mineMap[ny][nx] = cnt;
            if (cnt > 0) { // 주변에 지뢰가있으면 일단 안누름
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int ni = ny + dy[i];
                int nj = nx + dx[i];
                if (ni >= 0 && ni < N && nj >= 0 && nj < N && !visited[ni][nj] && mineMap[ni][nj] != -1) {
                    visited[ni][nj] = true;
                    queue.add(new int[] { ni, nj });
                }
            }
        }
    }

    static public int mineCnt(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int ny = r + dy[i];
            int nx = c + dx[i];
            if (ny >= 0 && ny < N && nx >= 0 && nx < N && mineMap[ny][nx] == -1) {
                cnt += 1;
            }
        }
        return cnt;
    }
}
