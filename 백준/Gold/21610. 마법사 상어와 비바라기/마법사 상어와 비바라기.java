

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] water, order;
    static boolean[][] cloud, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 이동 횟수

        water = new int[N][N]; // 물의 양
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        cloud = new boolean[N][N]; // 구름 초기값
        cloud[N - 1][0] = true;
        cloud[N - 1][1] = true;
        cloud[N - 2][0] = true;
        cloud[N - 2][1] = true;
        // 명령 실행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            moving(d, s);
            cloudChk();
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[y][x]) {
                        water[y][x] += waterChk(y, x);
                    }
                }
            }
            createCloud();
        }
        System.out.println(sumWater());
    }

    // 이동명령 수행
    static void moving(int d, int s) {
        int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
        int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
        int ny = 0;
        int nx = 0;
        for (int i = 0; i < s; i++) {
            ny += dy[d];
            nx += dx[d];
        }
        boolean[][] moveCloud = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!cloud[i][j]) {
                    continue;
                }
                int moveY = (i + ny) % N;
                int moveX = (j + nx) % N;
                if (moveY < 0) {
                    moveY += N;
                }
                if (moveX < 0) {
                    moveX += N;
                }
                if (moveY >= N) {
                    moveY -= N;
                }
                if (moveX >= N) {
                    moveX -= N;
                }
                moveCloud[moveY][moveX] = true;
            }
        }
        cloud = moveCloud;
    }

    // 구름 있으면 물 +1 하고 구름 증발, 증발된 좌표 기억
    static void cloudChk() {
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cloud[i][j]) {
                    visited[i][j] = true;
                    water[i][j] += 1;
                    cloud[i][j] = false;
                }
            }
        }
    }

    // 대각선 방향 방구니 체크
    static int waterChk(int y, int x) {
        int[] dy = { 1, 1, -1, -1 };
        int[] dx = { 1, -1, 1, -1 };
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                continue;
            }
            if (water[ny][nx] == 0) {
                continue;
            }
            cnt += 1;
        }
        return cnt;
    }

    static void createCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (water[i][j] >= 2) {
                    cloud[i][j] = true;
                    water[i][j] -= 2;
                }
            }
        }
    }

    static int sumWater() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += water[i][j];
            }
        }
        return sum;
    }
}
