

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int m;
    static int k;
    static int[][] ground;
    static int[][] winterA;
    static ArrayList<Integer>[][] wood;
    static ArrayList<Integer>[][] dieWood;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 땅크기
        m = Integer.parseInt(st.nextToken()); // 나무 개수
        k = Integer.parseInt(st.nextToken()); // 년수

        ground = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] = 5;
            }
        }

        winterA = new int[n][n]; // 겨율에 추가 되는 양분
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                winterA[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wood = new ArrayList[n][n]; // 나무 나이
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                wood[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            wood[y][x].add(age);
        }

        for (int i = 0; i < k; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(cntWood());
    }

    static void spring() {
        dieWood = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dieWood[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (wood[i][j].isEmpty()) {
                    continue;
                }
                Collections.sort(wood[i][j]);
                for (int k = 0; k < wood[i][j].size(); k++) {
                    if (ground[i][j] >= wood[i][j].get(k)) {
                        ground[i][j] -= wood[i][j].get(k);
                        wood[i][j].set(k, wood[i][j].get(k) + 1);
                    } else {
                        dieWood[i][j].add(wood[i][j].get(k));
                        wood[i][j].remove(k--);
                    }
                }
            }
        }
    }

    static void summer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dieWood[i][j].isEmpty()) {
                    continue;
                }
                for (int k = 0; k < dieWood[i][j].size(); k++) {
                    ground[i][j] += (dieWood[i][j].get(k) / 2);
                }
            }
        }
    }

    static void fall() {
        int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };
        int[] dx = { 1, 0, -1, 0, 1, -1, -1, 1 };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (wood[i][j].isEmpty()) {
                    continue;
                }
                for (int k = 0; k < wood[i][j].size(); k++) {
                    if (wood[i][j].get(k) != 0 && wood[i][j].get(k) % 5 == 0) {
                        for (int l = 0; l < 8; l++) {
                            int ny = i + dy[l];
                            int nx = j + dx[l];
                            if (ny < 0 || nx < 0 || nx >= n || ny >= n) {
                                continue;
                            }
                            wood[ny][nx].add(1);
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] += winterA[i][j];
            }
        }
    }

    static int cntWood() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += wood[i][j].size();
            }
        }
        return cnt;
    }
}