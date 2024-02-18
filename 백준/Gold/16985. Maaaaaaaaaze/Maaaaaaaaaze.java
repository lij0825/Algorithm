

import java.io.*;
import java.util.*;


public class Main {
    static int[][][] maze = new int[5][5][5], mazeCopy = new int[5][5][5]; // 원본, 복사용
    static int[] arr = new int[5], floor = new int[5];
    static boolean[] check = new boolean[5];
    static int result = Integer.MAX_VALUE;
    static int[][][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 5; l++) {
                    maze[i][j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }
        floorCheck(0); // 초기값
        System.out.println((result == Integer.MAX_VALUE) ? -1 : result); // 도착못했으면 -1 출력
    }

    static void floorCheck(int k) {
        if (k == 5) {
            backTracking(0);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (!check[i]) {
                check[i] = true;
                floor[k] = i;
                floorCheck(k + 1);
                check[i] = false;
            }
        }
    }

    // 백트레킹으로 조합
    static void backTracking(int k) {
        if (k == 5) {
            for (int i = 0; i < 5; i++) {
                rotation();
            }

            if (mazeCopy[0][0][0] == 1 && mazeCopy[4][4][4] == 1) {
                bfs();
                if (count[4][4][4] != 0) { // 0이 아니면 도착
                    result = Math.min(result, count[4][4][4]); // 도착했으면 갱신
                    if (result == 12) { // 12이면 최단 거리 더 해볼필요없음
                        System.out.println(12);
                        System.exit(0);
                    }
                }
            }
            return;
        }

        for (int i = 1; i < 5; i++) {// 5번
            arr[k] = i;
            backTracking(k + 1);
        }
    }

    static void rotation() { // 회전
        for (int i = 0; i < 5; i++) {
            int idx = floor[i];
            int rotationNum = arr[i];
            int[][] temp = new int[5][5];

            for (int j = 0; j < 5; j++) {
                for (int l = 0; l < 5; l++) {
                    if (rotationNum == 1) {
                        temp[j][l] = maze[i][j][l];
                    }
                    if (rotationNum == 2) {
                        temp[l][4 - j] = maze[i][j][l];
                    }
                    if (rotationNum == 3) {
                        temp[4 - j][4 - l] = maze[i][j][l];
                    }
                    if (rotationNum == 4) {
                        temp[4 - l][j] = maze[i][j][l];
                    }
                }
            }

            for (int j = 0; j < 5; j++) {
                for (int l = 0; l < 5; l++) {
                    mazeCopy[idx][j][l] = temp[j][l];
                }
            }
        }
    }

    static void bfs() { // 상하좌우앞뒤 6방향 탐색
        int[][] dist = {
                { -1, 0, 0 },
                { 1, 0, 0 },
                { 0, 0, -1 },
                { 0, 0, 1 },
                { 0, 1, 0 },
                { 0, -1, 0 } };
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[5][5][5]; // 방문체크
        count = new int[5][5][5];
        visit[0][0][0] = true;
        queue.add(new int[] { 0, 0, 0 });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nz = cur[0] + dist[i][0];
                int nx = cur[1] + dist[i][1];
                int ny = cur[2] + dist[i][2];

                if (nz < 0 || nz >= 5 || nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
                    continue;
                if (visit[nz][nx][ny] || mazeCopy[nz][nx][ny] != 1)
                    continue;

                count[nz][nx][ny] = count[cur[0]][cur[1]][cur[2]] + 1;

                if (nz == 4 && nx == 4 && ny == 4) {
                    return;
                }

                visit[nz][nx][ny] = true;
                queue.add(new int[] { nz, nx, ny });
            }
        }
    }
}
