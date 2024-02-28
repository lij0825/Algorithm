import java.io.*;
import java.util.*;

public class Main {
    static int k, r, c;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int[] jumpy = { 2, 2, 1, -1, -2, -2, 1, -1 };
    static int[] jumpx = { 1, -1, 2, 2, 1, -1, -2, -2 };
    static int[][] map;
    static int[][][] visited;
    static Queue<int[]> q = new ArrayDeque<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine()); // 점프 횟수
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[c][r];
        visited = new int[k + 1][c][r];

        for (int i = 0; i < c; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < r; j++) {
                map[i][j] = Integer.parseInt(str[j]); // 지도 0 : 길, 1 : 벽
            }
        }
        bfs(); // 탐색
        // for (int[][] is : visited) {
        // for (int[] is2 : is) {
        // System.out.println(Arrays.toString(is2));
        // }
        // System.out.println();
        // }
        int min = Integer.MAX_VALUE;
        for (int[][] is : visited) {
            if (is[c - 1][r - 1] != 0) {
                min = Math.min(min, is[c - 1][r - 1]);
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min - 1);
    }

    static void bfs() {
        visited[0][0][0] = 1; // 맨처음 1로 설정
        q.add(new int[] { 0, 0, 0 }); // 초기값 삽입
        while (!q.isEmpty()) {
            int[] yxj = q.poll();
            int j = yxj[0]; // 점프횟수
            int y = yxj[1]; // y
            int x = yxj[2]; // x
            //
            if (j < k) { // 점프 횟수가 더 적으면
                for (int i = 0; i < 8; i++) {
                    int jy = y + jumpy[i];
                    int jx = x + jumpx[i];
                    if (checkJump(jy, jx, j + 1) && map[jy][jx] != 1) {
                        if (visited[j + 1][jy][jx] == 0) {
                            visited[j + 1][jy][jx] = visited[j][y][x] + 1;
                        } else {
                            visited[j + 1][jy][jx] = Math.min(visited[j + 1][jy][jx], visited[j][y][x] + 1);
                        }
                        q.add(new int[] { j + 1, jy, jx });
                    }
                }
            }
            //
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (checkRange(ny, nx) && map[ny][nx] != 1 && checklayer(ny, nx, j)) {
                    if (visited[j][ny][nx] == 0) {
                        visited[j][ny][nx] = visited[j][y][x] + 1;
                    } else {
                        visited[j][ny][nx] = Math.min(visited[j][ny][nx], visited[j][y][x] + 1);
                    }
                    visited[j][ny][nx] = visited[j][y][x] + 1;
                    q.add(new int[] { j, ny, nx });
                }
            }
        }
    }

    // 범위 밖으로 나가는지 체크
    static boolean checkRange(int ny, int nx) {
        return nx < 0 || ny < 0 || nx >= r || ny >= c ? false : true;
    }

    // 점프 할수있는지 체크, 범위 안, 벽이 아니고, 방문한적이 없어야한다
    static boolean checkJump(int ny, int nx, int j) {
        return checkRange(ny, nx) && checklayer(ny, nx, j) ? true : false;
    }

    // 내가 지금까지 점프 하기 전의 방문체크를 해서 0이 아니면 방문 한것
    static boolean checklayer(int ny, int nx, int j) {
        for (int i = 0; i <= j; i++) {
            if (visited[i][ny][nx] != 0) {
                return false;
            }
        }
        return true;

    }
}