import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int n, eat, ans;
    static int[][] sea, visited;
    static Queue<int[]> q = new ArrayDeque<>();
    static ArrayList<int[]> fishInfo = new ArrayList<>();
    static ArrayList<int[]> fishlen = new ArrayList<>();
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, -1, 0, 1 };
    static int[] bs = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());// 가로세로 크기
        sea = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    sea[i][j] = 0;
                    bs[0] = i;
                    bs[1] = j;
                    bs[2] = 2;
                }
            }
        }
        eat = 0;
        boolean flag = canEat();
        while (flag) {
            bfs(bs[0], bs[1]); // 먹을수있는 물고기 탐색
            if (fishInfo.isEmpty()) {
                break;
            }
            eating();
            flag = canEat();
        }
        System.out.println(ans);
    }

    static void eating() {
        int minLen = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;

        Collections.sort(fishInfo, (a, b) -> Integer.compare(a[2], b[2]));
        for (int[] i : fishInfo) {
            int y = i[0];
            int x = i[1];
            int len = i[2];

            if (len < minLen) {
                minLen = len;
                minY = y;
                minX = x;
            } else if (len == minLen) {
                if (y < minY || (y == minY && x < minX)) {
                    minY = y;
                    minX = x;
                }
            }
        }
        eat += 1;
        if (eat == bs[2]) {
            eat = 0;
            if (bs[2] < 7) {
                bs[2] += 1;
            }
        }

        sea[minY][minX] = 0;
        bs[0] = minY;
        bs[1] = minX;
        ans += minLen - 1;
        fishInfo.clear();
    }

    static void bfs(int yy, int xx) {
        visited = new int[n][n];
        visited[yy][xx] = 1;
        q.add(new int[] { yy, xx });
        while (!q.isEmpty()) {
            int[] yx = q.poll();
            int y = yx[0];
            int x = yx[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                if (visited[ny][nx] != 0 || sea[ny][nx] > bs[2]) {
                    continue;
                }
                visited[ny][nx] = visited[y][x] + 1;
                q.add(new int[] { ny, nx });
                if (0 < sea[ny][nx] && sea[ny][nx] < bs[2]) {
                    fishInfo.add(new int[] { ny, nx, visited[ny][nx] });
                }
            }
        }
    }

    static boolean canEat() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (0 < sea[i][j] && sea[i][j] < bs[2]) {
                    return true;
                }
            }
        }
        return false;
    }
}