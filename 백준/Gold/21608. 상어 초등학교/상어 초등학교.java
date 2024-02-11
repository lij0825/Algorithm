

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int[][] room;
    static int[][] students;
    static boolean[][] visited;
    static int[][] score;
    static int[][] emptyScore;
    static ArrayList<Integer> ans = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine()); // 교실 크기
        room = new int[n][n]; // 교실
        visited = new boolean[n][n]; // 교실
        students = new int[n * n][5]; // 학생관계
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n * n; i++) {
            friendschk(i);
        }
        addStudent();
        int sum = 0;
        for (int i = 0; i < ans.size(); i++) {
            sum += Math.pow(10, (ans.get(i) - 1));
        }
        System.out.println(sum);
    }

    static void friendschk(int idx) {
        score = new int[n][n];
        int[] dy = { 0, 1, 0, -1 };
        int[] dx = { 1, 0, -1, 0 };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                        continue;
                    }
                    for (int l = 1; l < 5; l++) {
                        if (room[ny][nx] == students[idx][l]) {
                            cnt += 1;
                        }
                    }
                }
                score[i][j] = cnt;
            }
        }
        int max = findMax();
        findEmpty();
        seat(max, idx);
    }

    static int findMax() {
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, score[i][j]);
            }
        }
        return max;
    }

    static void findEmpty() {
        emptyScore = new int[n][n];
        int[] dy = { 0, 1, 0, -1 };
        int[] dx = { 1, 0, -1, 0 };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int empty = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                        continue;
                    }
                    if (visited[ny][nx]) {
                        continue;
                    }
                    if (room[ny][nx] == 0) {
                        empty += 1;
                    }
                }
                emptyScore[i][j] = empty;
            }
        }
    }

    static int emptyMax() {
        int emptymax = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                emptymax = Math.max(emptymax, emptyScore[i][j]);
            }
        }
        return emptymax;
    }

    static void seat(int max, int idx) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (visited[k][l]) {
                            continue;
                        }
                        if (score[k][l] == max - i) {
                            if (emptyScore[k][l] == emptyMax() - j) {
                                visited[k][l] = true;
                                room[k][l] = students[idx][0];
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    static void addStudent() {
        int[] dy = { 0, 1, 0, -1 };
        int[] dx = { 1, 0, -1, 0 };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                        continue;
                    }
                    for (int l = 0; l < n * n; l++) {
                        if (room[i][j] == students[l][0]) {
                            for (int x = 1; x < 5; x++) {
                                if (room[ny][nx] == students[l][x]) {
                                    cnt += 1;
                                }
                            }
                        }
                    }
                }
                ans.add(cnt);
            }
        }
    }
}
