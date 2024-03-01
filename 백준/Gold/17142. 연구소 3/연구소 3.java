import java.util.*;
import java.io.*;

class Main {
    static class Virus {
        int x, y, time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static ArrayList<Virus> viruses = new ArrayList<>();
    static Virus[] active;
    static int min = Integer.MAX_VALUE;
    static int emptyCnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        active = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) {
                    emptyCnt++;
                } else if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }

        if (emptyCnt == 0) {
            System.out.println(0);
        } else {
            actVirus(0, 0);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }

    static void actVirus(int s, int cnt) {
        if (cnt == M) {
            bfs(emptyCnt);
            return;
        }

        for (int i = s; i < viruses.size(); i++) {
            active[cnt] = viruses.get(i);
            actVirus(i + 1, cnt + 1);
        }
    }

    static void bfs(int e) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Virus virus = active[i];
            visited[virus.x][virus.y] = true;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Virus virus = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == 1) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    e--;
                }

                if (e == 0) {
                    min = Math.min(min, virus.time + 1);
                    return;
                }

                visited[nx][ny] = true;
                q.add(new Virus(nx, ny, virus.time + 1));
            }
        }
    }
}